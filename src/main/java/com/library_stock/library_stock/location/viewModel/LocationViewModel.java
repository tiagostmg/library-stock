package com.library_stock.library_stock.location.viewModel;

import com.library_stock.library_stock.location.Location;
import lombok.Data;

@Data
public class LocationViewModel {
    private int id;

    private String sector;

    private String aisle;

    private String shelf;

    private String shelfLevel;

    private String classificationCode;

    public static LocationViewModel fromLocation(Location loc) {
        if (loc == null) return null;
        LocationViewModel vm = new LocationViewModel();
        vm.setId(loc.getId());
        vm.setSector(loc.getSector());
        vm.setAisle(loc.getAisle());
        vm.setShelf(loc.getShelf());
        vm.setShelfLevel(loc.getShelfLevel());
        vm.setClassificationCode(loc.getClassificationCode());
        return vm;
    }
}
