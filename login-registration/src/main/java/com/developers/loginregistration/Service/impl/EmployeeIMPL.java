package com.developers.loginregistration.Service.impl;

import com.developers.loginregistration.Dto.EmployeeDTO;
import com.developers.loginregistration.Entity.Employee;
import com.developers.loginregistration.Repo.EmployeeRepo;
import com.developers.loginregistration.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee(
                employeeDTO.getEmployeeId(),
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword())
        );
        employeeRepo.save(employee);

        return employee.getEmployeeName();
    }
}
