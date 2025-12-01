package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.BookRepository;
import com.library_stock.library_stock.location.LocationRepository;
import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.UpdateBookInstanceViewModel;
import com.library_stock.library_stock.location.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookInstanceService extends BaseService<BookInstance, Integer, BookInstanceRepository> {

        private final BookRepository bookRepository;
        private final LocationRepository locationRepository;
        private final com.library_stock.library_stock.bookInstance.mapper.BookInstanceMapper bookInstanceMapper;

        public BookInstanceService(BookInstanceRepository repository, BookRepository bookRepository,
                        LocationRepository locationRepository,
                        com.library_stock.library_stock.bookInstance.mapper.BookInstanceMapper bookInstanceMapper) {
                super(repository);
                this.bookRepository = bookRepository;
                this.locationRepository = locationRepository;
                this.bookInstanceMapper = bookInstanceMapper;
        }

        public List<BookInstanceViewModel> findByBookId(int bookId) {
                List<BookInstance> listByBookId = repository
                                .findByBookId(bookId);

                return listByBookId.stream()
                                .map(bookInstanceMapper::toViewModel)
                                .toList();
        }

        public BookInstanceViewModel findByBookInstanceId(int id) {
                Optional<BookInstance> bookInstanceById = repository
                                .findById(id);

                return bookInstanceById
                                .map(bookInstanceMapper::toViewModel)
                                .orElseThrow(() -> new RuntimeException("BookInstance não encontrado"));
        }

        public BookInstanceViewModel findByInternalCode(String internalCode) {

                Optional<BookInstance> internalCodeBookInstance = repository
                                .findByInternalCode(internalCode);

                return internalCodeBookInstance
                                .map(bookInstanceMapper::toViewModel)
                                .orElseThrow(() -> new RuntimeException("Instancia do livro não encontrado"));
        }

        public BookInstanceViewModel createBookInstance(AddBookInstanceViewModel bookInstanceVM) {
                BookInstance bookInstance = new BookInstance();
                bookInstance.setInternalCode(bookInstanceVM.internalCode);
                bookInstance.setAcquisitionDate(bookInstanceVM.acquisitionDate);
                bookInstance.setStatus(bookInstanceVM.status);
                bookInstance.setPreservationState(bookInstanceVM.preservationState);
                Location location = locationRepository.findById(bookInstanceVM.locationId)
                                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
                bookInstance.setLocation(location);

                Book book = bookRepository.findById(bookInstanceVM.bookId)
                                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

                bookInstance.setBook(book);

                BookInstance saved = repository.save(bookInstance);

                return bookInstanceMapper.toViewModel(saved);
        }

        public BookInstanceViewModel updateBookInstance(int id, UpdateBookInstanceViewModel bookInstanceVM) {

                BookInstance bookInstance = repository.findById(id)
                                .orElseThrow(() -> new RuntimeException("BookInstance not found with id: " + id));

                bookInstance.setPreservationState(bookInstanceVM.preservationState);
                bookInstance.setStatus(bookInstanceVM.status);
                Location location = locationRepository.findById(bookInstanceVM.locationId)
                                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
                bookInstance.setLocation(location);

                BookInstance updated = repository.save(bookInstance);

                return bookInstanceMapper.toViewModel(updated);
        }

        public List<BookInstanceViewModel> findByPreservationStateBad() {
                List<BookInstance> badInstances = repository
                                .findByPreservationState(PreservationState.BAD);

                return badInstances.stream()
                                .map(bookInstanceMapper::toViewModel)
                                .toList();
        }

        public List<BookInstanceViewModel> findAllViewModels() {
                return repository.findAll().stream()
                                .map(bookInstanceMapper::toViewModel)
                                .toList();
        }

}
