package com.library_stock.library_stock.location;

import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<LocationViewModel> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LocationViewModel getById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/available")
    public List<LocationViewModel> getAvailableLocations() {
        return service.findAvailableLocations();
    }
}
