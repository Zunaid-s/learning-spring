package com.tunacake.webTutorial.services;


import com.tunacake.webTutorial.dto.CreateEmployeeDTO;
import com.tunacake.webTutorial.dto.EmployeeDTO;
import com.tunacake.webTutorial.entities.EmployeeEntity;
import com.tunacake.webTutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeByID(UUID id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map((entity) -> modelMapper.map(entity, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO createNewEmployee(CreateEmployeeDTO newemployeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(newemployeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeByID(UUID id, CreateEmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);

    }

    public boolean deleteEmployee(UUID id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists)
            return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeByID(UUID id, Map<String, Object> partialemployeeinfo) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        modelMapper.map(partialemployeeinfo, employeeEntity);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
