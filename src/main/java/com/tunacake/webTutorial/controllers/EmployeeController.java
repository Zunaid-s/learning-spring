package com.tunacake.webTutorial.controllers;


import com.tunacake.webTutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController /* This will tell Spring that this is a REST Controller. Shortcut for "@Controller + @ResponseBody" */
/* The @ResponseBody annotation tells Spring to return the response as JSON/XML instead of a view name */
@RequestMapping("/employees") /* The @RequestMapping annotation tells Spring that this class will handle requests to the /:route: path mentinoned in the annotation*/
public class EmployeeController {

//    @GetMapping(path="/getSecretMessage") /*The @GetMapping annotation tells Spring that this method will handle GET requests to the root path*/
//    public String getSecretMessage() {
//        return "Secret Message: Hello World!";
//    }

    @GetMapping(path="/getEmployee/{employeeID}")
    /* The @PathVariable annotator enables us to take variables wrapped around curly braces and get their value as passed in the URLs */
    /* If I want to have diffrent names for the path variable and the actually passed variable, I can use the @PathVariable(name="variableName") attribute where the "variableName" is the name of the variable in the URL*/
    public EmployeeDTO getEmployee(@PathVariable long employeeID) {
        return new EmployeeDTO(employeeID, "Juneth","juneth@mail.com",22, LocalDate.of(2026,2,4),true );
    }

    @GetMapping
    /*The @RequestParams annotator lets us extract values of variables from URLs. The varaible name is the same as the one in the URL */
    /*Eg: localhost:8080/employees?age=22 */
    /*Eg: localhost:8080/employees?age=22&name=Juneth*/
    /*If I want to make the variable name different from the one in the URL, I can use the @RequestParam(name="variableName") attribute where the "variableName" is the name of the variable in the URL*/
    public String getAllEmployees(@RequestParam/*(required = true/false )*/ int age, @RequestParam(required = false) String name) {
        return "All employees with age: " + age;
    }

    @PostMapping/* The @PostMapping annotation tells Spring that this method will handle POST requests to the root path*/
    public String createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {/*The @RequestBody annotation allows spring to read the body/contents of the data(usually JSON/XML data) that came with the request body*/

        return "Employee added with name: " + employeeDTO.getName();
    }

    /*Methords to pass data to the client -
    * @PathVariable - read the explicitly named variable in the URL (Eg: localhost:8080/employees/123)(here 123 is the variable value)
    * @RequestParams - directly read the variable data in the URL (Eg: localhost:8080/employees?age=22)(here age is the explicitly named variable present in the URL)
    * @RequestBody - read the data in the body of the request*/

    /* Similar to @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping, @RequestMapping can be used on the same method to handle different types of requests*/
}
