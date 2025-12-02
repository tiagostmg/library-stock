package com.library_stock.library_stock.location.mapper;

import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

  public LocationViewModel toViewModel(Location location) {
    if (location == null) {
      return null;
    }
    LocationViewModel vm = new LocationViewModel();
    vm.setId(location.getId());
    vm.setSector(location.getSector());
    vm.setAisle(location.getAisle());
    vm.setShelf(location.getShelf());
    vm.setShelfLevel(location.getShelfLevel());
    vm.setPosition(location.getPosition());
    return vm;
  }
}
