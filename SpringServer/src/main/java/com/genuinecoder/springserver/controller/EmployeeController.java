package com.genuinecoder.springserver.controller;

import com.genuinecoder.springserver.model.employee.Employee;
import com.genuinecoder.springserver.model.employee.EmployeeDao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeDao employeeDao;

  @GetMapping("/employee/get-all")
  public List<Employee> getAllEmployees() {
    return employeeDao.getAllEmployees();
  }
  
  @GetMapping("/employee/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
    Optional<Employee> employee = employeeDao.findById(id);
    if (employee.isPresent()) {
      return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PutMapping("/employee/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
    employee.setId(id); // Ensure ID matches path variable
    Employee updatedEmployee = employeeDao.updateEmployee(employee);
    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
  }

  @PostMapping("/employee/save")
  public Employee save(@RequestBody Employee employee) {
    return employeeDao.save(employee);
  }
  
  
}
