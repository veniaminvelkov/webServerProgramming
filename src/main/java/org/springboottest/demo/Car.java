package org.springboottest.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Car {

    /*
    "make": "string",
    "model": "string",
    "productionYear": 0,
    "licensePlate": "string",
    "garageIds": [
    0]}
     */

    @Id
    @GeneratedValue
    private Long id;

    private String make;

    private String model;

    private int productionYear;

    private String licensePlate;

    private List<String> garageIds;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
