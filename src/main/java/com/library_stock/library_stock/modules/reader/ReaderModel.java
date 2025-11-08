package com.library_stock.library_stock.modules.reader;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reader")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    private String name;

    @Column()
    private String cpf;

    @Column()
    private String email;

    @Column()
    private String address;

    @Column()
    private String phone;

}
