
package com.example.employeemanagement;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();

    // Part (b): Add Employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee employee) {

        employees.add(employee);
        return ResponseEntity.status(201).body(employee);
    }

    // Part (c): Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Part (c): Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable int id) {

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return ResponseEntity.ok(employee);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Part (d): Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        for (Employee employee : employees) {
            if (employee.getId() == id) {

                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());
                employee.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(employee);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Part (e): Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id) {

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {

                employees.remove(i);
                return ResponseEntity.ok("Employee Deleted Successfully");
            }
        }

        return ResponseEntity.notFound().build();
    }
}
