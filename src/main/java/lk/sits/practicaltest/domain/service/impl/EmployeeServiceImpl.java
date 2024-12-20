package lk.sits.practicaltest.domain.service.impl;

import lk.sits.practicaltest.domain.entity.Employee;
import lk.sits.practicaltest.domain.service.EmployeeService;
import lk.sits.practicaltest.external.exception.CustomException;
import lk.sits.practicaltest.external.exception.EmployeeDetailsNotFoundException;
import lk.sits.practicaltest.external.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    /**
     * This method get all employees from db
     *
     * @return {@link List<Employee>}
     */
    @Override
    public List<Employee> getAllEmployees() {
        try {
            List<Employee> employees = repository.findAll();
            if (CollectionUtils.isEmpty(employees)) {
                log.warn("No Any Employees Found");
                throw new EmployeeDetailsNotFoundException("No Any Employees Found");
            }
            return employees;
        } catch (Exception e) {
            log.error("Error Occurred In Getting All Employees | {}", e.getLocalizedMessage());
            throw new CustomException("Error Occurred In Getting All Employees | " + e.getLocalizedMessage());
        }
    }

    /**
     * This method get employee by employee id
     *
     * @param id {@link Integer}
     * @return {@link Employee}
     */
    @Override
    public Employee getEmployeeById(final Long id) {
        try {
            Optional<Employee> employeeById = repository.findById(id);
            if (employeeById.isPresent()) {
                return employeeById.get();
            }
            log.warn("No Any Employees Found For Id {}", id);
            throw new EmployeeDetailsNotFoundException("No Any Employees Found For Id - " + id);
        } catch (Exception e) {
            log.error("Error Occurred In Getting Employee By Id | {}", e.getLocalizedMessage());
            throw new CustomException("Error Occurred In Getting Employee By Id | " + e.getLocalizedMessage());
        }
    }

    /**
     * This method creates new employee
     *
     * @param employee {@link Employee}
     * @return {@link Employee}
     */
    @Override
    public Employee createEmployee(final Employee employee) {
        try {
            return repository.save(employee);
        } catch (Exception e) {
            log.error("Error Occurred In Creating Employee | {}", e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * This method updates already having employee details
     *
     * @param id       {@link Integer}
     * @param employee {@link Employee}
     * @return {@link Employee}
     */
    @Override
    public Employee updateEmployee(final Long id, final Employee employee) {
        try {
            Optional<Employee> employeeById = repository.findById(id);
            if (employeeById.isPresent()) {
                Employee defaultEmployee = employeeById.get();
                if (!ObjectUtils.isEmpty(employee.getFirstName())) {
                    defaultEmployee.setFirstName(employee.getFirstName());
                }
                if (!ObjectUtils.isEmpty(employee.getLastName())) {
                    defaultEmployee.setLastName(employee.getLastName());
                }
                if (!ObjectUtils.isEmpty(employee.getEmail())) {
                    defaultEmployee.setEmail(employee.getEmail());
                }
                if (!ObjectUtils.isEmpty(employee.getSalary())) {
                    defaultEmployee.setSalary(employee.getSalary());
                }
                Employee updatedEmployee = repository.save(defaultEmployee);
                if (!ObjectUtils.isEmpty(updatedEmployee)) {
                    log.info("Employee Updated Successfully | Id {}", id);
                    return updatedEmployee;
                }
            }
        } catch (Exception e) {
            log.error("Error Occurred In Updating Employee | {}", e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * This method delete employee by id
     *
     * @param id {@link Integer}
     */
    @Override
    public void deleteEmployee(final Long id) {
        try {
            repository.deleteById(id);
            log.info("Employee Deleted Successfully | Employee Id {}", id);
        } catch (Exception e) {
            log.error("Error Occurred In Deleting Employee By Id {} | {}", id, e.getLocalizedMessage());
        }
    }
}
