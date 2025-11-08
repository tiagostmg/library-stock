package com.library_stock.library_stock.modules.loan;

import com.library_stock.library_stock.modules.loan.types.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column()
    private LocalDateTime loanDate;

    @Column()
    private LocalDateTime expectedReturnDate;

    @Column()
    private LocalDateTime actualReturnDate;

    @Column()
    private Enum<LoanStatus> status;

    @Column()
    private String notes;

    @Column()
    private String libraryId;

    @Column()
    private String readerId;

    @Column()
    private String bookInstanceId;

}
