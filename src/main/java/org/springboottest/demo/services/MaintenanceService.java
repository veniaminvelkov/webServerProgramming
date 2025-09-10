package org.springboottest.demo.services;

import jakarta.persistence.EntityNotFoundException;
import org.springboottest.demo.dtos.maintenance.ResponseMaintenanceDTO;
import org.springboottest.demo.dtos.maintenance.UpdateMaintenanceDTO;
import org.springboottest.demo.entities.Car;
import org.springboottest.demo.entities.Garage;
import org.springboottest.demo.entities.Maintenance;
import org.springboottest.demo.repos.CarRepository;
import org.springboottest.demo.repos.GarageRepository;
import org.springboottest.demo.repos.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class MaintenanceService {

    private GarageRepository garageRepository;
    private CarRepository carRepository;
    private MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository,
                              CarRepository carRepository,
                              GarageRepository garageRepository) {

        this.maintenanceRepository = maintenanceRepository;
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
    }

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ResponseMaintenanceDTO getMaintenanceById(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Maintenance not found"));
        return toResponse(maintenance);
    }

    public List<ResponseMaintenanceDTO> getAllMaintenances(Long carId, Long garageId, String startDate, String endDate) {
        LocalDate start = parse(startDate);
        LocalDate end = parse(endDate);

        return maintenanceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    public ResponseMaintenanceDTO createMaintenance(org.springboottest.demo.dtos.maintenance.CreateMaintenanceDTO dto) {
        Garage g = garageRepository.findById(dto.getGarageId())
                .orElseThrow(() -> new EntityNotFoundException("Garage not found: " + dto.getGarageId()));
        Car c = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found: " + dto.getCarId()));

        LocalDate date = parse(dto.getScheduledDate());
        ensureCapacity(g, date);

        Maintenance m = new Maintenance();
        m.setGarage(g);
        m.setCar(c);
        m.setServiceType(dto.getServiceType());
        m.setScheduledDate(dto.getScheduledDate());

        return toResponse(maintenanceRepository.save(m));
    }

    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }

    public ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO dto) {
        Maintenance existing = maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance not found: " + id));

        Garage updatedGarage = dto.getGarageId() != null
                ? garageRepository.findById(dto.getGarageId())
                .orElseThrow(() -> new EntityNotFoundException("Garage not found: " + dto.getGarageId()))
                : existing.getGarage();

        Car newCar = dto.getCarId() != null
                ? carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found: " + dto.getCarId()))
                : existing.getCar();

        String newServiceType = dto.getServiceType() != null ? dto.getServiceType() : existing.getServiceType();
        String newDate = dto.getScheduledDate() != null ? dto.getScheduledDate() : existing.getScheduledDate();

        existing.setGarage(updatedGarage);
        existing.setCar(newCar);
        existing.setServiceType(newServiceType);
        existing.setScheduledDate(newDate);

        return toResponse(maintenanceRepository.save(existing));
    }

    private void ensureCapacity(Garage g, LocalDate date) {
        long booked = maintenanceRepository.countByGarage_IdAndScheduledDate(g.getId(), date.format(dateTimeFormatter));
        if (booked >= g.getCapacity()) {
            throw new IllegalArgumentException("No available capacity in the garage on this date");
        }
    }

    private ResponseMaintenanceDTO toResponse(Maintenance maintenance) {
        ResponseMaintenanceDTO dto = new ResponseMaintenanceDTO();
        dto.setId(maintenance.getId());
        dto.setCarId(maintenance.getCar().getId());
        String make = maintenance.getCar().getMake() == null ? "" : maintenance.getCar().getMake();
        String model = maintenance.getCar().getModel() == null ? "" : maintenance.getCar().getModel();
        dto.setCarName((make + " " + model).trim());
        dto.setServiceType(maintenance.getServiceType());
        dto.setScheduledDate(maintenance.getScheduledDate());
        dto.setGarageId(maintenance.getGarage().getId());
        dto.setGarageName(maintenance.getGarage().getName());
        return dto;
    }

    private LocalDate parse(String s) {
        if (s == null || s.isBlank()) return null;
        try { return LocalDate.parse(s, dateTimeFormatter); }
        catch (DateTimeParseException e) { throw new IllegalArgumentException("scheduledDate must be yyyy-mm-dd"); }
    }
}