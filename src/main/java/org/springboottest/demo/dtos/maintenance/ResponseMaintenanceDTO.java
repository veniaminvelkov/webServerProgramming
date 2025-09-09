package org.springboottest.demo.dtos.maintenance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMaintenanceDTO {

    private Long carId;
    private String carName;

    private String serviceType;

    private String scheduledDate;

    private Long garageId;

    private String garageName;
}
