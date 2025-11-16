package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.user.User;
import com.library_stock.library_stock.loan.types.LoanStatus;
import com.library_stock.library_stock.reader.Reader;
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
public class Loan {
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
    private LoanStatus status;

    @Column()
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_instance_id", nullable = false)
    private BookInstance bookInstance;


}
