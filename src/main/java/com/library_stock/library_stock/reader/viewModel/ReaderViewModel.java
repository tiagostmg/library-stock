package com.library_stock.library_stock.reader.viewModel;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class ReaderViewModel {
    private int id;

    private String name;

    private String cpf;

    private String email;

    private String address;

    private String phone;
}
