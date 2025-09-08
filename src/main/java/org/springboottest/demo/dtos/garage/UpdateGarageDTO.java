package org.springboottest.demo.dtos.garage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateGarageDTO {

    private String name;

    private String location;

    private int capacity;

    private String city;
}
