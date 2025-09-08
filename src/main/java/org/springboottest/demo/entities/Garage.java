package org.springboottest.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "garages")
@Getter
@Setter
public class Garage {

    /*
"name": "string",
"location": "string",
"city": "string",
"capacity": 0
     */

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String location;

    private String city;

    private int capacity;

    @ManyToMany(mappedBy = "garages")
    private List<Car> cars;

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
