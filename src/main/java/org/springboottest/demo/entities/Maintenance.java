package org.springboottest.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Maintenance {
    /*
    id": 0,
    "carId": 0,
    "carName": "string",
    "serviceType": "string",
    "scheduledDate": "2024-12-10",
    "garageId": 0,
    "garageName": "string"
     */

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private String serviceType;

    private String scheduledDate;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
}
