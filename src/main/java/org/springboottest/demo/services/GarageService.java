package org.springboottest.demo.services;

import org.springboottest.demo.dtos.garage.CreateGarageDTO;
import org.springboottest.demo.dtos.garage.DeleteGarageDTO;
import org.springboottest.demo.dtos.garage.ResponseGarageDTO;
import org.springboottest.demo.dtos.garage.UpdateGarageDTO;
import org.springboottest.demo.entities.Garage;
import org.springboottest.demo.repos.GarageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public DeleteGarageDTO deleteGarageById(Long id) {
        garageRepository.deleteById(id);
        DeleteGarageDTO deleted = new DeleteGarageDTO();
        deleted.setId(id);
        return deleted;
    }

    public ResponseGarageDTO updateGarageDTO(Long id,  UpdateGarageDTO updateGarageDTO) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        garage.setName(updateGarageDTO.getName());
        garage.setLocation(updateGarageDTO.getLocation());
        garage.setCity(updateGarageDTO.getCity());
        garage.setCapacity(updateGarageDTO.getCapacity());
        garageRepository.save(garage);

        return toResponse(garage);
    }

    public List<ResponseGarageDTO> getGarageByCity(String city) {
        List<Garage> garages = garageRepository.findByCity(city);
        List<ResponseGarageDTO> responseGarageDTOS = new ArrayList<>();
        for(Garage garage : garages) {
            responseGarageDTOS. add(toResponse(garage));
        }

        return responseGarageDTOS;
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
