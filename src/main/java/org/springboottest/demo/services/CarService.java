package org.springboottest.demo.services;

import org.springboottest.demo.dtos.car.CreateCarDTO;
import org.springboottest.demo.dtos.car.ResponseCarDTO;

import org.springboottest.demo.dtos.garage.ResponseGarageDTO;
import org.springboottest.demo.entities.Car;
import org.springboottest.demo.entities.Garage;
import org.springboottest.demo.repos.CarRepository;

import org.springboottest.demo.repos.GarageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    CarRepository carRepository;
    GarageService garageService;
    GarageRepository garageRepository;

    public CarService(CarRepository carRepository, GarageRepository garageRepository) {
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
    }

    public ResponseCarDTO createCar(CreateCarDTO dto) {
        Car car = new Car();
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setProductionYear(dto.getProductionYear());
        car.setLicensePlate(dto.getLicensePlate());


        //TODO:DeUglify cause it's uglyyy
        if (dto.getGarageIds() != null) {
            List<Garage> garages = garageRepository.findAllById(dto.getGarageIds());
            car.setGarages(garages);
        }

        Car saved = carRepository.save(car);
        return toResponse(saved);
    }

    public List<ResponseCarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ResponseCarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return toResponse(car);
    }

    public ResponseCarDTO updateCar(Long id, CreateCarDTO dto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setProductionYear(dto.getProductionYear());
        car.setLicensePlate(dto.getLicensePlate());

        if (dto.getGarageIds() != null) {
            List<Garage> garages = garageRepository.findAllById(dto.getGarageIds());
            car.setGarages(garages);
        }

        Car updated = carRepository.save(car);
        return toResponse(updated);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private ResponseCarDTO toResponse(Car car) {
        ResponseCarDTO dto = new ResponseCarDTO();
        dto.setId(car.getId());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        dto.setProductionYear(car.getProductionYear());
        dto.setLicensePlate(car.getLicensePlate());

        List<ResponseGarageDTO> garageDtos = car.getGarages().stream()
                .map(g -> {
                    ResponseGarageDTO gr = new ResponseGarageDTO();
                    gr.setId(g.getId());
                    gr.setName(g.getName());
                    gr.setLocation(g.getLocation());
                    gr.setCity(g.getCity());
                    gr.setCapacity(g.getCapacity());
                    return gr;
                })
                .toList();
        dto.setGarages(garageDtos);

        return dto;
    }
}
