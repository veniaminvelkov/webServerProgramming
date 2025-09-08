package org.springboottest.demo.dtos;

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

    private String make;

    private String model;

    private String year;

    private String licensePlate;

    private List<Integer> garageIds;
}
