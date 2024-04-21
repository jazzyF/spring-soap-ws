package com.springbootsoap.services;

import com.springbootsoap.model.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void updateEmployee(Employee employee);
    void deleteEmployee(long id);
}
