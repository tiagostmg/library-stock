package com.library_stock.library_stock.config;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.BookRepository;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.bookInstance.BookInstanceRepository;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.LocationRepository;
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
import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(
            BookRepository bookRepository,
            BookInstanceRepository bookInstanceRepository,
            LocationRepository locationRepository,
            UserRepository userRepository,
            ReaderRepository readerRepository,
            LoanRepository loanRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // -----------------------
            // USER
            // -----------------------
            User user = userRepository.findByCpf("01234567890")
                    .orElseGet(() -> userRepository.save(
                            new User(
                                    0,
                                    "01234567890",
                                    passwordEncoder.encode("12345678"),
                                    "Bruno Lopes",
                                    "bruno@example.com"
                            )
                    ));

            // -----------------------
            // READERS (2 leitores)
            // -----------------------
            if (readerRepository.count() == 0) {
                readerRepository.save(new Reader(
                        0,
                        "Gabriel Medeiros",
                        "11111111111",
                        "gabriel@example.com",
                        "Rua A, 123",
                        "85999990001"
                ));

                readerRepository.save(new Reader(
                        0,
                        "Mariana Costa",
                        "22222222222",
                        "mariana@example.com",
                        "Rua B, 456",
                        "85999990002"
                ));

                System.out.println("==== 2 READERS CREATED ====");
            }

            // pega o primeiro reader (já garantimos que existe pelo bloco acima)
            Reader reader = readerRepository.findAll().get(0);

            // -----------------------
            // BOOKS + INSTANCES (50 livros)
            // -----------------------
            String[] titles = {
                    "The Silent River", "Echoes of Eternity", "Broken Skies", "The Last Kingdom",
                    "Dreams of Tomorrow", "Crimson Shadows", "Emerald Dawn", "Lost Horizons",
                    "Burning Fate", "The Forgotten Path", "Winds of Winter", "Voices in the Dark",
                    "Shattered Realms", "Beyond the Stars", "The Iron Throne", "A Whisper of Hope",
                    "Fading Memories", "The Crystal Empire", "Ashes of War", "Hidden Truths",
                    "Rise of Legends", "Stormborn", "Threads of Destiny", "The Final Voyage",
                    "Moonlit Promise", "The Blood Oath", "Frostbound", "Chasing Infinity",
                    "Shadowfall", "The Lost Archive", "Requiem of Souls", "Golden Horizon",
                    "The Witch’s Secret", "Blades of Honor", "Fragments of Light", "The Eternal Maze",
                    "Frozen Echoes", "The Black Sun", "Sands of Time", "The Lone Wolf",
                    "Children of Fire", "The Hidden Fortress", "The Fallen Star", "Rising Sun",
                    "Tides of Magic", "The Last Oracle", "Broken Destiny", "A World Apart",
                    "The Iron Moon", "Secrets of the Deep"
            };

            String[] authors = {
                    "John Smith", "Anna Gray", "Lucas Turner", "Amelia Clark",
                    "Robert Hill", "Sophie Adams", "Daniel Carter", "Emily Stone"
            };

            String[] publishers = {
                    "HarperCollins", "Penguin Books", "Random House", "Simon & Schuster"
            };

            for (int i = 0; i < 50; i++) {
                String isbn = "978000000" + String.format("%04d", i + 1);
                int index = i;

                Book book = bookRepository.findByIsbn(isbn)
                        .orElseGet(() -> bookRepository.save(
                                new Book(
                                        0,
                                        titles[index],
                                        authors[index % authors.length],
                                        publishers[index % publishers.length],
                                        isbn,
                                        "Fiction",
                                        "Autogenerated description for " + titles[index]
                                )
                        ));

                String code = "S" + (i % 10 + 1) + "-A" + (i % 5 + 1) + "-L" + (i % 3 + 1) + "-P" + (i % 4 + 1);

                Location loc = locationRepository.findByClassificationCode(code)
                        .orElseGet(() -> locationRepository.save(
                                new Location(
                                        0,
                                        "Sector " + (index % 10 + 1),
                                        "Aisle " + (index % 5 + 1),
                                        "Shelf " + (index % 3 + 1),
                                        "Level " + (index % 4 + 1),
                                        "Pos " + (index % 8 + 1),
                                        code
                                )
                        ));

                String internalCode = "INT-" + String.format("%04d", i + 1);

                if (bookInstanceRepository.findByInternalCode(internalCode).isEmpty()) {
                    bookInstanceRepository.save(
                            new BookInstance(
                                    0,
                                    internalCode,
                                    LocalDateTime.now(),
                                    PreservationState.GOOD,
                                    BookStatus.AVAILABLE,
                                    book,
                                    loc
                            )
                    );
                }
            }

            System.out.println("==== 50 BOOKS + INSTANCES CREATED ====");

            // -----------------------
            // LOANS — criar somente se não existir nenhum (evita duplicação)
            // -----------------------
            if (loanRepository.count() == 0) {
                // garanta que as instances existem
                BookInstance bi1 = bookInstanceRepository.findByInternalCode("INT-0001")
                        .orElseThrow(() -> new IllegalStateException("BookInstance INT-0001 missing"));
                BookInstance bi2 = bookInstanceRepository.findByInternalCode("INT-0002")
                        .orElseThrow(() -> new IllegalStateException("BookInstance INT-0002 missing"));
                BookInstance bi3 = bookInstanceRepository.findByInternalCode("INT-0003")
                        .orElseThrow(() -> new IllegalStateException("BookInstance INT-0003 missing"));
                BookInstance bi4 = bookInstanceRepository.findByInternalCode("INT-0004")
                        .orElseThrow(() -> new IllegalStateException("BookInstance INT-0004 missing"));

                // 2 atrasados
                loanRepository.save(new Loan(
                        0,
                        LocalDate.now().minusDays(12),
                        LocalDate.now().minusDays(5),
                        null,
                        LoanStatus.OVERDUE,
                        "Overdue test 1",
                        user,
                        reader,
                        bi1
                ));

                loanRepository.save(new Loan(
                        0,
                        LocalDate.now().minusDays(20),
                        LocalDate.now().minusDays(10),
                        null,
                        LoanStatus.OVERDUE,
                        "Overdue test 2",
                        user,
                        reader,
                        bi2
                ));

                // 2 dentro do prazo
                loanRepository.save(new Loan(
                        0,
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(6),
                        null,
                        LoanStatus.IN_PROGRESS,
                        "In progress test 1",
                        user,
                        reader,
                        bi3
                ));

                loanRepository.save(new Loan(
                        0,
                        LocalDate.now().minusDays(2),
                        LocalDate.now().plusDays(3),
                        null,
                        LoanStatus.IN_PROGRESS,
                        "In progress test 2",
                        user,
                        reader,
                        bi4
                ));

                System.out.println("==== 4 LOANS CREATED (2 overdue + 2 in progress) ====");
            } else {
                System.out.println("Loans already exist — skipping loan seeding.");
            }
        };
    }
}
