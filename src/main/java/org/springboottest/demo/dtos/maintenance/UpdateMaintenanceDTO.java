package org.springboottest.demo.dtos.maintenance;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMaintenanceDTO {

    @NotNull
    private Long garageId;

    private Long carId;

    private String serviceType;

    private String scheduledDate;
}
