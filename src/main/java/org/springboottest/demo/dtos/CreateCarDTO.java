package org.springboottest.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCarDTO {
    /*
    "make": "string",
"model": "string",
"productionYear": 0,
"licensePlate": "string",
     */

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotBlank
    private String licensePlate;

    @NotNull
    private int productionYear;

    private List<Long> garageIds;
}
