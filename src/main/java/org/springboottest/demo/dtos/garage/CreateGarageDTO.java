package org.springboottest.demo.dtos.garage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGarageDTO {

    //NotBlank - NotEmpty, but removes spaces
    //NotEmpty - NotNull, but also checks empty strings ("")

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotBlank
    private String city;

    @NotNull
    @Min(0)
    private int capacity;
}
