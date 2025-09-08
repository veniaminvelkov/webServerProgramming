package org.springboottest.demo.dtos.garage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGarageDTO {

    private Long id;

    private String name;

    private String location;

    private String city;

    private int capacity;
}
