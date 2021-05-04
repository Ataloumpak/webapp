package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

    private static final String EMPLOYEE_SUFFIX = "/employees";

    @Autowired
    private CustomProperties props;

    @SuppressWarnings("ConstantConditions")
    private final String getEmployeesUrl = props.getApiUrl() + EMPLOYEE_SUFFIX;

    /**
     * Get all employees
     *
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        return communicate(HttpMethod.GET,"Get all Employees call " ).getBody();
    }

    public Employee getEmployee(final int id) {

        return communicate(id, Employee.class, HttpMethod.GET,"Get an Employee call " ).getBody();
    }

    public Employee createEmployee(final Employee employee) {
        return communicate(employee, HttpMethod.POST,"Create Employees call " ).getBody();
    }


    public Employee updateEmployee(final Employee employee) {
        return communicate(employee, HttpMethod.PUT,"Update Employees call " ).getBody();
    }

    public void deleteEmployee(final int id) {
        communicate(id, Void.class, HttpMethod.DELETE,"Delete Employees call " );
    }

    private ResponseEntity<Employee> communicate(Employee employee, HttpMethod httpMethod, String debugPrefix)
    {
        ResponseEntity<Employee> response = new RestTemplate().exchange(getEmployeesUrl, httpMethod, new HttpEntity<>(employee), Employee.class);
        log.debug(debugPrefix + response.getStatusCode());
        return response;
    }

    private <T> ResponseEntity<T> communicate(int id, Class<T> tClass, HttpMethod httpMethod, String debugPrefix)
    {
        ResponseEntity<T> response = new RestTemplate().exchange(getEmployeesUrl + "/" + id, httpMethod, null, tClass);
        log.debug(debugPrefix + response.getStatusCode());
        return response;
    }

    private ResponseEntity<Iterable<Employee>> communicate(HttpMethod httpMethod, String debugPrefix)
    {
        ResponseEntity<Iterable<Employee>> response = new RestTemplate().exchange(getEmployeesUrl, httpMethod, null, new ParameterizedTypeReference<>() {});
        log.debug(debugPrefix + response.getStatusCode());
        return response;
    }
}