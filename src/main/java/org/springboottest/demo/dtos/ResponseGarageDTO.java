package org.springboottest.demo.dtos;

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
