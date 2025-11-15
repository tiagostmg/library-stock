package com.library_stock.library_stock.modules.loan.viewModel;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Data
public class LocationViewModel {

    private int id;

    private String sector;

    private String aisle;

    private String shelf;

    private String shelfLevel;

    private String position;

    private String classificationCode;
}
