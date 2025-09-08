package org.springboottest.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cars")
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

    @OneToMany(mappedBy = "car")
    private List<Garage> garages;

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
