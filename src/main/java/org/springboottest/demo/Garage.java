package org.springboottest.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
