package lk.sits.practicaltest.domain.service;

import lk.sits.practicaltest.domain.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(final Integer id);

    Employee createEmployee(final Employee employee);

    Employee updateEmployee(final Integer id, final Employee employee);

    void deleteEmployee(final Integer id);
}
