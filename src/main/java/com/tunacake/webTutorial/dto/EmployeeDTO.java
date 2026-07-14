package com.tunacake.webTutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO { /*The DTO is a data transfer object that contains the data that will be sent to the client or received from the client*/
/* The DTO is used exchange data between the controller and the service layer*/
/*DTO stays betenn the controller and the service layer*/
/*DTOs are not used in the persistence layer*/
/*Jackson is used to convert DTOs to JSON when sending responses to the client and vice versa*/
    private UUID id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateofjoining;
    private boolean isActive;
}
