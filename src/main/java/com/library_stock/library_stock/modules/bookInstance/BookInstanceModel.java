package com.library_stock.library_stock.modules.bookInstance;

import com.library_stock.library_stock.modules.book.BookModel;
import com.library_stock.library_stock.modules.bookInstance.types.BookStatus;
import com.library_stock.library_stock.modules.location.LocationModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="book_instance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInstanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false, unique = true)
    private String internalCode;

    @Column(nullable = false)
    private LocalDateTime acquisitionDate;

    @Column(length = 50)
    private String preservationState;

    @Enumerated(EnumType.STRING)
    @Column()
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel book;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationModel location;

}
