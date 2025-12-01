package com.library_stock.library_stock.location;

import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import com.library_stock.library_stock.location.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private LocationMapper locationMapper;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<LocationViewModel> findAll() {
        return repository.findAll()
                .stream()
                .map(locationMapper::toViewModel)
                .toList();
    }

    public LocationViewModel findById(int id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        return locationMapper.toViewModel(location);
    }

    public List<LocationViewModel> findAvailableLocations() {
        return repository.findAvailableLocations()
                .stream()
                .map(locationMapper::toViewModel)
                .toList();
    }

}
