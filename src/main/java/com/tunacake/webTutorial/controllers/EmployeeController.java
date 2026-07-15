package com.tunacake.webTutorial.controllers;


import com.tunacake.webTutorial.dto.EmployeeDTO;
import com.tunacake.webTutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController /* This will tell Spring that this is a REST Controller. Shortcut for "@Controller + @ResponseBody" */
/* The @ResponseBody annotation tells Spring to return the response as JSON/XML instead of a view name */
@RequestMapping("/employees") /* The @RequestMapping annotation tells Spring that this class will handle requests to the /:route: path mentinoned in the annotation*/
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;// dependency injection here
    }

//    @GetMapping(path="/getSecretMessage") /*The @GetMapping annotation tells Spring that this method will handle GET requests to the root path*/
//    public String getSecretMessage() {
//        return "Secret Message: Hello World!";
//    }

    /* The @PathVariable annotator enables us to take variables wrapped around curly braces and get their value as passed in the URLs */
    /* If I want to have diffrent names for the path variable and the actually passed variable, I can use the @PathVariable(name="variableName") attribute where the "variableName" is the name of the variable in the URL*/
    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") UUID id) {
        return employeeService.getEmployeeByID(id);
    }

    @GetMapping(path = "/getAllEmployees")
    /*The @RequestParams annotator lets us extract values of variables from URLs. The varaible name is the same as the one in the URL */
    /*Eg: localhost:8080/employees?age=22 */
    /*Eg: localhost:8080/employees?age=22&name=Juneth*/
    /*If I want to make the variable name different from the one in the URL, I can use the @RequestParam(name="variableName") attribute where the "variableName" is the name of the variable in the URL*/
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(/*@RequestParam(required = false ) int age, @RequestParam(required = false) String name*/) {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping/* The @PostMapping annotation tells Spring that this method will handle POST requests to the root path*/
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO newemployeeDTO) {/*The @RequestBody annotation allows spring to read the body/contents of the data(usually JSON/XML data) that came with the request body*/
        return new ResponseEntity<>(employeeService.createNewEmployee(newemployeeDTO),HttpStatus.CREATED);
    }

    /*Methords to pass data to the client -
    * @PathVariable - read the explicitly named variable in the URL (Eg: localhost:8080/employees/123)(here 123 is the variable value)
    * @RequestParams - directly read the variable data in the URL (Eg: localhost:8080/employees?age=22)(here age is the explicitly named variable present in the URL)
    * @RequestBody - read the data in the body of the request*/

    @PutMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeByID(@RequestBody EmployeeDTO employeeDTO, @PathVariable(name = "employeeID") UUID id){
        return new ResponseEntity<>(employeeService.updateEmployeeByID(id, employeeDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{employeeID}")
    public ResponseEntity<Boolean> deleteEmployeeByID(@PathVariable(name = "employeeID") UUID id){
        boolean isDeleted = employeeService.deleteEmployee(id);
        if(isDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> editEmployeeByID(@RequestBody Map<String, Object> partialEmployeeinfo, @PathVariable(name = "employeeID") UUID id){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeByID(id, partialEmployeeinfo);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);

    }

    /* Similar to @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping, @RequestMapping can be used on the same method to handle different types of requests*/
}
