package com.tunacake.webTutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tunacake.webTutorial.annotations.BornInLeapYearValivation;
import com.tunacake.webTutorial.annotations.EmployeeRoleValidation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
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

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters") //This is a validation annotation that checks the size whether the size of the field is between min and max
    @NotBlank(message = "Name cannot be null") //This is a validation annotation that checks if the name field is not null
    private String name;

    @BornInLeapYearValivation
    @Max(value = 80, message = "Age must be less than 80")
    @Min(value = 18, message = "Age must be greater than 18") //This is a validation annotation that checks if the age field is greater than equal to the given value
    private Integer age;

    @Email //This is a validation annotation that checks if the email field is a valid email address
    private String email;

    @NotNull(message = "Role cannot be null")
    @EmployeeRoleValidation
//    @Pattern(regexp = "^(Admin|User|Manager)$", message = "Role must be Admin, User or Manager")
    private String role;// Admin, User, Manager

    @PastOrPresent(message = "Date of joining must be in the past or present")
    private LocalDate dateofjoining;

    @JsonProperty("isActive")
    private boolean isActive;
}
