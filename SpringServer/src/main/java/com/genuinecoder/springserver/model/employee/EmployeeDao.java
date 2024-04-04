package com.genuinecoder.springserver.model.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDao {

  @Autowired
  private EmployeeRepository repository;

  public Employee save(Employee employee) {
    return repository.save(employee);
  }
  
   public Optional<Employee> findById(int employeeId) {
	   return repository.findById(employeeId);
   }

  public List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    Streamable.of(repository.findAll())
        .forEach(employees::add);
    return employees;
  }
  
  public Employee updateEmployee(Employee employee) {
	    Optional<Employee> existingEmployee = repository.findById(employee.getId());

	    if (existingEmployee.isPresent()) {
	      Employee updatedEmployee = existingEmployee.get();
	      updatedEmployee.setName(employee.getName()); 
	      updatedEmployee.setLocation(employee.getLocation());
	      updatedEmployee.setBranch(employee.getBranch());
	      return repository.save(updatedEmployee);
	    } else {
	      throw new RuntimeException("Employee with ID " + employee.getId() + " not found");
	    }
	  }

  public void delete(int employeeId) {
    repository.deleteById(employeeId);
  }
}
