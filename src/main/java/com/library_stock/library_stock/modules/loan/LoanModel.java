package com.library_stock.library_stock.modules.loan;

import com.library_stock.library_stock.modules.bookInstance.BookInstanceModel;
import com.library_stock.library_stock.modules.librarian.LibrarianModel;
import com.library_stock.library_stock.modules.loan.types.LoanStatus;
import com.library_stock.library_stock.modules.reader.ReaderModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate expectedReturnDate;

    @Column()
    private LocalDateTime actualReturnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Enum<LoanStatus> status;

    @Column()
    private String notes;

    @ManyToOne
    @JoinColumn(name = "librarian_id", nullable = false)
    private LibrarianModel librarian;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private ReaderModel reader;

    @ManyToOne
    @JoinColumn(name = "book_instance_id", nullable = false)
    private BookInstanceModel bookInstance;


}
