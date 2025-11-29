package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.location.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_instance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false, unique = true)
    private String internalCode;

    @Column(nullable = false)
    private LocalDateTime acquisitionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreservationState preservationState;

    @Enumerated(EnumType.STRING)
    @Column()
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToOne
    @JoinColumn(name = "location_id", unique = true)
    private Location location;

}
