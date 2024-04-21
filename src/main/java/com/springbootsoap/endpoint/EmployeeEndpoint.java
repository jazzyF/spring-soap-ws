package com.springbootsoap.endpoint;

import allapis.springbootrest.com.AddEmployeeRequest;
import allapis.springbootrest.com.AddEmployeeResponse;
import allapis.springbootrest.com.DeleteEmployeeRequest;
import allapis.springbootrest.com.DeleteEmployeeResponse;
import allapis.springbootrest.com.EmployeeInfo;
import allapis.springbootrest.com.GetEmployeeByIdRequest;
import allapis.springbootrest.com.GetEmployeeResponse;
import allapis.springbootrest.com.ServiceStatus;
import allapis.springbootrest.com.UpdateEmployeeRequest;
import allapis.springbootrest.com.UpdateEmployeeResponse;
import com.springbootsoap.model.Employee;
import com.springbootsoap.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://com.springbootsoap.allapis";

    private final EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        employeeService.addEmployee(employee);
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Employee added successfully");
        response.setServiceStatus(serviceStatus);
        response.setEmployeeInfo(request.getEmployeeInfo());
        employeeService.addEmployee(employee);
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content added successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employeeService.getEmployeeById(request.getEmployeeId()), employeeInfo);
        response.setEmployeeInfo(employeeInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        employeeService.updateEmployee(employee);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content updated successfully");
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        ServiceStatus serviceStatus = new ServiceStatus();
        employeeService.deleteEmployee(request.getEmployeeId());
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content deleted successfully");
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

}
