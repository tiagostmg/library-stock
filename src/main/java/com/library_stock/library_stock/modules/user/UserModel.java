package com.library_stock.library_stock.modules.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "librarian")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Column(length = 100, unique = true)
    private String email;
}
