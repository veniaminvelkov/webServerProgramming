package org.springboottest.demo.controllers;

import jakarta.validation.Valid;
import org.springboottest.demo.dtos.CreateGarageDTO;
import org.springboottest.demo.dtos.DeleteGarageDTO;
import org.springboottest.demo.dtos.ResponseGarageDTO;
import org.springboottest.demo.services.GarageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {

    private GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseGarageDTO>> getGarage() {
        return  ResponseEntity.ok(garageService.getAllGarages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> getGarageById(@PathVariable Long id) {
        return ResponseEntity.ok(garageService.getGarageById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseGarageDTO> createGarage(@Valid @RequestBody CreateGarageDTO dto) {
        return ResponseEntity.ok(garageService.createGarage(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteGarageDTO> deleteGarageById(@PathVariable String id) {
        garageService.deleteGarageById(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}
