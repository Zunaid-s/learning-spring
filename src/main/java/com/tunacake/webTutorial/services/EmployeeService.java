package com.tunacake.webTutorial.services;


import com.tunacake.webTutorial.dto.EmployeeDTO;
import com.tunacake.webTutorial.entities.EmployeeEntity;
import com.tunacake.webTutorial.exception.ResourceNotFoundException;
import com.tunacake.webTutorial.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeByID(UUID id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);

         return employeeRepository.findById(id)
                 .map(employeeEntity ->
                         modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map((entity) -> modelMapper.map(entity, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO createNewEmployee(@Valid EmployeeDTO newemployeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(newemployeeDTO, EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public Optional<EmployeeDTO> updateEmployeeByID(UUID id, EmployeeDTO employeeDTO) {

        boolean exists = employeeRepository.existsById(id);

        if (exists) {
            EmployeeEntity employeeEntity = employeeRepository.save(modelMapper.map(employeeDTO, EmployeeEntity.class));
            return Optional.ofNullable(modelMapper.map(employeeEntity, EmployeeDTO.class));
        }else{
            throw new ResourceNotFoundException("Employee not found with id: "+id);
        }

//        if(!exists) throw new ResourceNotFoundException("Employee not found with id: "+id);
//        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
//        employeeEntity.setId(id);
//        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
//        return modelMapper.map(savedEntity, EmployeeDTO.class);

    }

    public boolean deleteEmployee(UUID id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists)
            throw new ResourceNotFoundException("Employee not found with id: "+id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeByID(UUID id, Map<String, Object> partialemployeeinfo) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) throw  new ResourceNotFoundException("Employee not found with id: "+id);
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        modelMapper.map(partialemployeeinfo, employeeEntity);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
