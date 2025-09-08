package org.springboottest.demo.services;

import org.springboottest.demo.dtos.CreateGarageDTO;
import org.springboottest.demo.dtos.ResponseGarageDTO;
import org.springboottest.demo.entities.Garage;
import org.springboottest.demo.repos.GarageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageService {

    private GarageRepository garageRepository;

    public GarageService(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO) {

        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        garage.setCapacity(createGarageDTO.getCapacity());

        var saved = garageRepository.save(garage);
        return toResponse(saved);
    }

    public List<ResponseGarageDTO> getAllGarages() {
        return garageRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ResponseGarageDTO getGarageById(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found"));
        return toResponse(garage);
    }

    private ResponseGarageDTO toResponse(Garage g) {

        ResponseGarageDTO response = new ResponseGarageDTO();
        response.setId(g.getId());
        response.setName(g.getName());
        response.setLocation(g.getLocation());
        response.setCity(g.getCity());
        response.setCapacity(g.getCapacity());
        return response;
    }
}
