package com.mustafaakurt.employee.service;

import com.mustafaakurt.employee.entity.Employee;
import com.mustafaakurt.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee postEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        if (!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee exisitingEmployee = optionalEmployee.get();

            exisitingEmployee.setName(employee.getName());
            exisitingEmployee.setEmail(employee.getEmail());
            exisitingEmployee.setPhone(employee.getPhone());
            exisitingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(exisitingEmployee);
        }
        return null;
    }
}
