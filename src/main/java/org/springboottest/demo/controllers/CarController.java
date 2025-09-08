package org.springboottest.demo.controllers;

import jakarta.validation.Valid;
import org.springboottest.demo.dtos.CreateCarDTO;
import org.springboottest.demo.dtos.ResponseCarDTO;
import org.springboottest.demo.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<ResponseCarDTO> createCar(@Valid @RequestBody CreateCarDTO dto) {
        return ResponseEntity.ok(carService.createCar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> updateCar(@PathVariable Long id,
                                                    @Valid @RequestBody CreateCarDTO dto) {
        return ResponseEntity.ok(carService.updateCar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}