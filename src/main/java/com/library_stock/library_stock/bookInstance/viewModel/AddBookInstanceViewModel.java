package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddBookInstanceViewModel {

    public PreservationState preservationState;

    public BookStatus status;

}
