package com.library_stock.library_stock.modules.book.viewModel;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookViewModel { // TODO: Verificar as validação

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
    private String title;

    @NotBlank(message = "O autor é obrigatório.")
    @Size(max = 150, message = "O autor deve ter no máximo 150 caracteres.")
    private String author;

    @NotBlank(message = "A editora é obrigatória.")
    @Size(max = 100, message = "A editora deve ter no máximo 100 caracteres.")
    private String publisher;

    @NotBlank(message = "O ISBN é obrigatório.")
    @Size(min = 10, max = 17, message = "O ISBN deve ter entre 10 e 17 caracteres.")
    private String isbn;

    @NotBlank(message = "A categoria é obrigatória.")
    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres.")
    private String category;

    @Size(max = 200, message = "As observações devem ter no máximo 200 caracteres.")
    private String notes;
}