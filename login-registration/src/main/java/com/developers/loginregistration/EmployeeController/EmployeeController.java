package com.developers.loginregistration.EmployeeController;

import com.developers.loginregistration.Dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @PostMapping(path = "/save")
    public String  saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }
}
