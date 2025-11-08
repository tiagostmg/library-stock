package com.library_stock.library_stock.modules.bookInstance;

import com.library_stock.library_stock.modules.bookInstance.types.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInstanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String internalCode;

    @Column()
    private LocalDateTime acquisitionDate;

    @Column(length = 50)
    private String preservationState;

    @Column()
    private Enum<BookStatus> status;

    private int bookId;

    private int locationId;
}
