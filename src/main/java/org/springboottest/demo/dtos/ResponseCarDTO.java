package org.springboottest.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springboottest.demo.dtos.garage.ResponseGarageDTO;

import java.util.List;

@Getter
@Setter
public class ResponseCarDTO {

    private Long id;

    private String make;

    private String model;

    private int productionYear;

    private String licensePlate;

    private List<ResponseGarageDTO> garages;
}
