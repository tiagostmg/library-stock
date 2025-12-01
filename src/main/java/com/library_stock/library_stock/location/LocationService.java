package com.library_stock.library_stock.location;

import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<LocationViewModel> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToLocationViewModel)
                .toList();
    }

    public LocationViewModel findById(int id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        return mapToLocationViewModel(location);
    }

    public List<LocationViewModel> findAvailableLocations() {
        return repository.findAvailableLocations()
                .stream()
                .map(this::mapToLocationViewModel)
                .toList();
    }

    private LocationViewModel mapToLocationViewModel(Location location) {
        LocationViewModel vm = new LocationViewModel();

        vm.setId(location.getId());
        vm.setSector(location.getSector());
        vm.setAisle(location.getAisle());
        vm.setShelf(location.getShelf());
        vm.setShelfLevel(location.getShelfLevel());
        vm.setPosition(location.getPosition());
        vm.setClassificationCode(location.getClassificationCode());

        return vm;
    }
}
