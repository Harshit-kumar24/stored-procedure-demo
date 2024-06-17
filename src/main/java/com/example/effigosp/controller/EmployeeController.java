package com.example.effigosp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.effigosp.entity.EmployeeEntity;
import com.example.effigosp.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	  private final EmployeeService employeeService;

	    @Autowired
	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }

	    @PostMapping("/insert")
	    public EmployeeEntity insertEmployee(@RequestBody EmployeeEntity employee) {
	        return employeeService.insertEmployee(employee);
	    }
}
