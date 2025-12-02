package com.library_stock.library_stock.config;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.BookRepository;

import com.library_stock.library_stock.book.types.Category;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.bookInstance.BookInstanceRepository;
import com.library_stock.library_stock.bookInstance.BookInstanceService;
import com.library_stock.library_stock.bookInstance.mapper.BookInstanceMapper;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.loan.Loan;
import com.library_stock.library_stock.loan.LoanRepository;
import com.library_stock.library_stock.loan.types.LoanStatus;
import com.library_stock.library_stock.reader.Reader;
import com.library_stock.library_stock.reader.ReaderRepository;
import com.library_stock.library_stock.user.User;
import com.library_stock.library_stock.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DatabaseSeeder {

        @Bean
        CommandLineRunner initDatabase(
                        BookInstanceService bookInstanceService,
                        BookRepository bookRepository,
                        BookInstanceRepository bookInstanceRepository,
                        BookInstanceMapper bookInstanceMapper,
                        UserRepository userRepository,
                        ReaderRepository readerRepository,
                        LoanRepository loanRepository,
                        PasswordEncoder passwordEncoder) {
                return args -> {
                        if (bookRepository.count() > 0) {
                                System.out.println("--- SEED IGNORADO: Dados já presentes no banco. ---");
                                return;
                        }

                        System.out.println("--- INICIANDO SEED DO BANCO DE DADOS (VERSÃO COMPLETA) ---");

                        User adminUser = userRepository.findByCpf("01234567890")
                                        .orElseGet(() -> userRepository.save(
                                                        new User(
                                                                        0,
                                                                        "01234567890",
                                                                        passwordEncoder.encode("12345678"),
                                                                        "Bruno Lopes",
                                                                        "bruno@library.com")));

                        List<Reader> readers = new ArrayList<>();
                        readers.add(readerRepository
                                        .save(new Reader(0, "Gabriel Medeiros", "11111111111", "gabriel@email.com",
                                                        "Rua A, 123", "85999990001")));
                        readers.add(readerRepository.save(
                                        new Reader(0, "Mariana Costa", "22222222222", "mariana@email.com", "Rua B, 456",
                                                        "85999990002")));
                        readers.add(readerRepository.save(
                                        new Reader(0, "Carlos Drumond", "33333333333", "carlos@email.com", "Rua C, 789",
                                                        "85999990003")));
                        readers.add(readerRepository
                                        .save(new Reader(0, "Ana Beatriz", "44444444444", "ana@email.com", "Rua D, 101",
                                                        "85999990004")));
                        readers.add(readerRepository
                                        .save(new Reader(0, "João Silva", "55555555555", "joao@email.com", "Rua E, 202",
                                                        "85999990005")));

                        List<BookData> rawBooks = List.of(
                                        new BookData("Clean Code", "Robert C. Martin", "Prentice Hall", "9780132350884",
                                                        Category.TECHNOLOGY),
                                        new BookData("The Pragmatic Programmer", "Andrew Hunt", "Addison-Wesley",
                                                        "9780201616224",
                                                        Category.TECHNOLOGY),
                                        new BookData("Domain-Driven Design", "Eric Evans", "Addison-Wesley",
                                                        "9780321125217",
                                                        Category.TECHNOLOGY),
                                        new BookData("Design Patterns", "Erich Gamma", "Addison-Wesley",
                                                        "9780201633610",
                                                        Category.TECHNOLOGY),
                                        new BookData("Refactoring", "Martin Fowler", "Addison-Wesley", "9780201485677",
                                                        Category.TECHNOLOGY),
                                        new BookData("Head First Java", "Kathy Sierra", "O'Reilly", "9780596009205",
                                                        Category.TECHNOLOGY),
                                        new BookData("Effective Java", "Joshua Bloch", "Addison-Wesley",
                                                        "9780134685991",
                                                        Category.TECHNOLOGY),
                                        new BookData("Clean Architecture", "Robert C. Martin", "Prentice Hall",
                                                        "9780134494166",
                                                        Category.TECHNOLOGY),
                                        new BookData("The Mythical Man-Month", "Fred Brooks", "Addison-Wesley",
                                                        "9780201835953",
                                                        Category.TECHNOLOGY),
                                        new BookData("Code Complete", "Steve McConnell", "Microsoft Press",
                                                        "9780735619678",
                                                        Category.TECHNOLOGY),
                                        new BookData("Harry Potter and the Sorcerer's Stone", "J.K. Rowling",
                                                        "Scholastic", "9780590353427",
                                                        Category.FANTASY),
                                        new BookData("Harry Potter and the Chamber of Secrets", "J.K. Rowling",
                                                        "Scholastic",
                                                        "9780439064873", Category.FANTASY),
                                        new BookData("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling",
                                                        "Scholastic",
                                                        "9780439136365", Category.FANTASY),
                                        new BookData("The Lord of the Rings", "J.R.R. Tolkien", "Houghton Mifflin",
                                                        "9780544003415",
                                                        Category.FANTASY),
                                        new BookData("The Hobbit", "J.R.R. Tolkien", "Houghton Mifflin",
                                                        "9780547928227", Category.FANTASY),
                                        new BookData("Dune", "Frank Herbert", "Chilton Books", "9780441172719",
                                                        Category.FICTION),
                                        new BookData("Neuromancer", "William Gibson", "Ace", "9780441569595",
                                                        Category.FICTION),
                                        new BookData("Foundation", "Isaac Asimov", "Gnome Press", "9780553293357",
                                                        Category.FICTION),
                                        new BookData("Snow Crash", "Neal Stephenson", "Bantam Books", "9780553380958",
                                                        Category.FICTION),
                                        new BookData("The Hitchhiker's Guide to the Galaxy", "Douglas Adams",
                                                        "Pan Books", "9780345391803",
                                                        Category.FICTION),
                                        new BookData("1984", "George Orwell", "Secker & Warburg", "9780451524935",
                                                        Category.FICTION),
                                        new BookData("Brave New World", "Aldous Huxley", "Chatto & Windus",
                                                        "9780060850524",
                                                        Category.FICTION),
                                        new BookData("Fahrenheit 451", "Ray Bradbury", "Ballantine Books",
                                                        "9781451673319",
                                                        Category.FICTION),
                                        new BookData("The Catcher in the Rye", "J.D. Salinger", "Little, Brown",
                                                        "9780316769488",
                                                        Category.FICTION),
                                        new BookData("To Kill a Mockingbird", "Harper Lee", "Lippincott",
                                                        "9780061120084",
                                                        Category.FICTION),
                                        new BookData("The Great Gatsby", "F. Scott Fitzgerald", "Scribner",
                                                        "9780743273565",
                                                        Category.FICTION),
                                        new BookData("Moby Dick", "Herman Melville", "Harper & Brothers",
                                                        "9781503280786",
                                                        Category.FICTION),
                                        new BookData("War and Peace", "Leo Tolstoy", "The Russian Messenger",
                                                        "9781400079988",
                                                        Category.HISTORY),
                                        new BookData("Pride and Prejudice", "Jane Austen", "T. Egerton",
                                                        "9780141439518", Category.ROMANCE),
                                        new BookData("O Alquimista", "Paulo Coelho", "HarperTorch", "9780062315007",
                                                        Category.FICTION));

                        List<Book> savedBooks = new ArrayList<>();
                        for (BookData b : rawBooks) {
                                Book book = bookRepository.save(
                                                new Book(0, b.title, b.author, b.publisher, b.isbn, b.category,
                                                                "Description for " + b.title));
                                savedBooks.add(book);
                        }

                        List<BookInstance> allInstances = new ArrayList<>();
                        Random random = new Random();

                        for (int i = 0; i < savedBooks.size(); i++) {
                                Book book = savedBooks.get(i);

                                int copies = 2 + random.nextInt(3);

                                for (int c = 1; c <= copies; c++) {

                                        BookInstanceViewModel newInstance = bookInstanceService.createBookInstance(
                                                        book.getId(),
                                                        new AddBookInstanceViewModel(
                                                                        PreservationState.values()[random
                                                                                        .nextInt(PreservationState
                                                                                                        .values().length)],
                                                                        BookStatus.AVAILABLE));

                                        BookInstance instanceEntity = bookInstanceRepository
                                                        .findById(newInstance.getId())
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "Erro ao recuperar instância criada no seed."));

                                        allInstances.add(instanceEntity);
                                }
                        }

                        if (!allInstances.isEmpty() && !readers.isEmpty()) {

                                for (BookInstance instance : allInstances) {
                                        int chance = random.nextInt(100);
                                        Reader randomReader = readers.get(random.nextInt(readers.size()));

                                        if (chance < 15) {
                                                createLoan(loanRepository, bookInstanceRepository, adminUser,
                                                                randomReader, instance,
                                                                LocalDate.now().minusDays(random.nextInt(20) + 20),
                                                                LocalDate.now().minusDays(random.nextInt(10) + 1),
                                                                null,
                                                                LoanStatus.OVERDUE,
                                                                "Atrasado. Contatar leitor.");

                                        } else if (chance < 40) {
                                                createLoan(loanRepository, bookInstanceRepository, adminUser,
                                                                randomReader, instance,
                                                                LocalDate.now().minusDays(random.nextInt(10) + 1),
                                                                LocalDate.now().plusDays(random.nextInt(10) + 5),
                                                                null,
                                                                LoanStatus.IN_PROGRESS,
                                                                "Empréstimo regular.");
                                        }
                                }
                        }

                        System.out.println("--- SEED COMPLETO: " + savedBooks.size() + " livros, " + allInstances.size()
                                        + " instâncias criadas. ---");
                };
        }

        private void createLoan(LoanRepository loanRepo, BookInstanceRepository biRepo,
                        User user, Reader reader, BookInstance instance,
                        LocalDate loanDate, LocalDate expectedDate, LocalDateTime actualReturnDate,
                        LoanStatus status, String notes) {

                loanRepo.save(new Loan(
                                0, loanDate, expectedDate, actualReturnDate, status, notes, user, reader, instance));

                if (status == LoanStatus.IN_PROGRESS || status == LoanStatus.OVERDUE) {
                        instance.setStatus(BookStatus.CHECKED_OUT);
                        biRepo.save(instance);
                }
        }

        record BookData(String title, String author, String publisher, String isbn, Category category) {
        }
}