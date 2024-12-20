package lk.sits.practicaltest.domain.service;


import lk.sits.practicaltest.domain.entity.Employee;
import lk.sits.practicaltest.domain.service.impl.EmployeeServiceImpl;
import lk.sits.practicaltest.external.exception.CustomException;
import lk.sits.practicaltest.external.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getAllEmployeesTest() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Prabath");
        List<Employee> employees = new ArrayList<>(List.of(employee));
        when(repository.findAll()).thenReturn(employees);
        assertEquals(employees, employeeService.getAllEmployees());
    }

    @Test
    void getAllEmployeesWhenNoEmployeesFoundTest() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        CustomException exception = assertThrows(CustomException.class, () -> {
            employeeService.getAllEmployees();
        });
        assertEquals("Error Occurred In Getting All Employees | No Any Employees Found", exception.getMessage());
    }

    @Test
    void getEmployeeByIdTest() {
        Employee employee = new Employee();
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        assertEquals(employee, employeeService.getEmployeeById(1L));
    }

    @Test
    void createEmployeeTest() {
        Employee employee = new Employee();
        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeService.createEmployee(employee));
    }

    @Test
    void updateEmployeeTest() {
        Employee employee = new Employee();
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeService.updateEmployee(1L, employee));
    }

    @Test
    void deleteEmployeeTest() {
        employeeService.deleteEmployee(1L);
        verify(repository, times(1)).deleteById(1L);
    }

}
