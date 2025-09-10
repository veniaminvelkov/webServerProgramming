package org.springboottest.demo.controllers;

import jakarta.validation.Valid;
import org.springboottest.demo.dtos.maintenance.ResponseMaintenanceDTO;
import org.springboottest.demo.dtos.maintenance.UpdateMaintenanceDTO;
import org.springboottest.demo.services.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    private MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseMaintenanceDTO>> getAllMaintenances(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances(carId, garageId, startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<ResponseMaintenanceDTO> createMaintenance(
            @Valid @RequestBody org.springboottest.demo.dtos.maintenance.CreateMaintenanceDTO dto) {
        return ResponseEntity.ok(maintenanceService.createMaintenance(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.ok(new java.util.HashMap<>());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> updateMaintenanceById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMaintenanceDTO dto) {
        return ResponseEntity.ok(maintenanceService.updateMaintenance(id, dto));
    }
}
