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
            }

            Reader reader = readerRepository.findAll().get(0);

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

            for (int sector = 1; sector <= 4; sector++) {
                for (int aisle = 1; aisle <= 4; aisle++) {
                    for (int shelf = 1; shelf <= 4; shelf++) {
                        for (int level = 1; level <= 3; level++) {
                            for (int position = 1; position <= 5; position++) {

                                final int fSector = sector;
                                final int fAisle = aisle;
                                final int fShelf = shelf;
                                final int fLevel = level;
                                final int fPosition = position;

                                final String code =
                                        "SE" + fSector +
                                                "-A" + fAisle +
                                                "-SH" + fShelf +
                                                "-L" + fLevel +
                                                "-P" + fPosition;

                                locationRepository.findByClassificationCode(code)
                                        .orElseGet(() -> locationRepository.save(
                                                new Location(
                                                        0,
                                                        "Sector " + fSector,
                                                        "Aisle " + fAisle,
                                                        "Shelf " + fShelf,
                                                        "Level " + fLevel,
                                                        "Pos " + fPosition,
                                                        code
                                                )
                                        ));
                            }
                        }
                    }
                }
            }

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

                int sector = (i % 4) + 1;
                int aisle = (i % 4) + 1;
                int shelf = (i % 4) + 1;
                int level = (i % 3) + 1;
                int position = (i % 5) + 1;

                String code = "SE" + sector + "-A" + aisle + "-SH" + shelf + "-L" + level + "-P" + position;

                Location loc = locationRepository.findByClassificationCode(code)
                        .orElseThrow(() -> new RuntimeException("Location not found: " + code));

                int copies = 2 + (i % 4);

                for (int c = 1; c <= copies; c++) {

                    String internalCode = "INT-" + String.format("%04d", i + 1) + "-" + c;

                    if (bookInstanceRepository.findByInternalCode(internalCode).isEmpty()) {

                        bookInstanceRepository.save(
                                new BookInstance(
                                        0,
                                        internalCode,
                                        LocalDateTime.now().minusDays(c),
                                        PreservationState.values()[c % PreservationState.values().length],
                                        BookStatus.AVAILABLE,
                                        book,
                                        loc
                                )
                        );
                    }
                }
            }

            if (loanRepository.count() == 0) {

                BookInstance bi1 = bookInstanceRepository.findByInternalCode("INT-0001-1")
                        .orElseThrow(() -> new IllegalStateException("Missing instance"));
                BookInstance bi2 = bookInstanceRepository.findByInternalCode("INT-0002-1")
                        .orElseThrow(() -> new IllegalStateException("Missing instance"));
                BookInstance bi3 = bookInstanceRepository.findByInternalCode("INT-0003-1")
                        .orElseThrow(() -> new IllegalStateException("Missing instance"));
                BookInstance bi4 = bookInstanceRepository.findByInternalCode("INT-0004-1")
                        .orElseThrow(() -> new IllegalStateException("Missing instance"));

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
            }
        };
    }
}
