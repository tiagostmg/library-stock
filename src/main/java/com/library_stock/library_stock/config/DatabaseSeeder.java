package com.library_stock.library_stock.config;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.BookRepository;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.bookInstance.BookInstanceRepository;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.LocationRepository;
import com.library_stock.library_stock.user.User;
import com.library_stock.library_stock.user.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(
            BookRepository bookRepository,
            BookInstanceRepository bookInstanceRepository,
            LocationRepository locationRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // ===========================================
            // USER - BRUNO LOPES
            // ===========================================
            userRepository.findByCpf("01234567890")
                    .orElseGet(() -> {
                        User bruno = new User(
                                0,
                                "01234567890",
                                passwordEncoder.encode("12345678"),
                                "Bruno Lopes",
                                "bruno@example.com"
                        );
                        return userRepository.save(bruno);
                    });

            // ===========================================
            // LOCATIONS
            // ===========================================
            Location loc1 = locationRepository
                    .findByClassificationCode("A1-S2-L1-P4")
                    .orElseGet(() -> locationRepository.save(
                            new Location(
                                    0,
                                    "Sector A",
                                    "Aisle 1",
                                    "Shelf 2",
                                    "Level 1",
                                    "Pos 4",
                                    "A1-S2-L1-P4"
                            )
                    ));

            Location loc2 = locationRepository
                    .findByClassificationCode("B3-S1-L2-P2")
                    .orElseGet(() -> locationRepository.save(
                            new Location(
                                    0,
                                    "Sector B",
                                    "Aisle 3",
                                    "Shelf 1",
                                    "Level 2",
                                    "Pos 2",
                                    "B3-S1-L2-P2"
                            )
                    ));

            // ===========================================
            // BOOKS
            // ===========================================
            Book book1 = bookRepository
                    .findByIsbn("9780544003415")
                    .orElseGet(() -> bookRepository.save(
                            new Book(
                                    0,
                                    "The Lord of the Rings",
                                    "J.R.R. Tolkien",
                                    "HarperCollins",
                                    "9780544003415",
                                    "Fantasy",
                                    "Classic epic fantasy novel."
                            )
                    ));

            Book book2 = bookRepository
                    .findByIsbn("9780451524935")
                    .orElseGet(() -> bookRepository.save(
                            new Book(
                                    0,
                                    "1984",
                                    "George Orwell",
                                    "Signet Classics",
                                    "9780451524935",
                                    "Dystopia",
                                    "Dystopian novel about totalitarianism."
                            )
                    ));

            // ===========================================
            // BOOK INSTANCES
            // ===========================================

            if (bookInstanceRepository.findByInternalCode("INT-0001").isEmpty()) {
                bookInstanceRepository.save(
                        new BookInstance(
                                0,
                                "INT-0001",
                                LocalDateTime.now(),
                                "Good",
                                BookStatus.AVAILABLE,
                                book1,
                                loc1
                        )
                );
            }

            if (bookInstanceRepository.findByInternalCode("INT-0002").isEmpty()) {
                bookInstanceRepository.save(
                        new BookInstance(
                                0,
                                "INT-0002",
                                LocalDateTime.now(),
                                "Excellent",
                                BookStatus.AVAILABLE,
                                book2,
                                loc2
                        )
                );
            }

            System.out.println("==== DATABASE SEEDED SUCCESSFULLY ====");
        };
    }
}
