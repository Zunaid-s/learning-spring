package com.tunacake.webTutorial.dto;

import java.time.LocalDate;


public class EmployeeDTO { /*The DTO is a data transfer object that contains the data that will be sent to the client or received from the client*/
/* The DTO is used exchange data between the controller and the service layer*/
/*DTO stays betenn the controller and the service layer*/
/*DTOs are not used in the persistence layer*/
/*Jackson is used to convert DTOs to JSON when sending responses to the client and vice versa*/
    private long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateofjoining;
    private boolean isActive;

    public EmployeeDTO() {

    }

    public EmployeeDTO(long id, String name, String email, Integer age, LocalDate dateofjoining, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateofjoining = dateofjoining;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateofjoining() {
        return dateofjoining;
    }

    public void setDateofjoining(LocalDate dateofjoining) {
        this.dateofjoining = dateofjoining;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
