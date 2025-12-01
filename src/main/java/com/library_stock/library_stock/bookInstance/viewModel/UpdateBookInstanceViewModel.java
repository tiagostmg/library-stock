package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.viewModel.LocationViewModel;

public class UpdateBookInstanceViewModel {

    public PreservationState preservationState;

    public BookStatus status;

    public int locationId;
}
