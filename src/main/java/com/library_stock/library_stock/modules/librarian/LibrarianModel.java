package com.library_stock.library_stock.modules.librarian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "librarian")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String username;

    @Column()
    private String passwordHash;

    @Column(length = 100)
    private String fullName;

    @Column(length = 100)
    private String email;
}
