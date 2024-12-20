package lk.sits.practicaltest.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lk.sits.practicaltest.domain.entity.Employee;
import lk.sits.practicaltest.domain.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * This is controller for get all of employees
     *
     * @return {@link ResponseEntity<List<Employee>>}
     */
    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * This is controller for get employee by id
     *
     * @return {@link ResponseEntity<Employee>}
     */
    @Operation(summary = "Get employees by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    /**
     * This is controller for create employee
     *
     * @return {@link ResponseEntity<Employee>}
     */
    @Operation(summary = "Create employee")
    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody final Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    /**
     * This is controller for update employee by id
     *
     * @return {@link ResponseEntity<Employee>}
     */
    @Operation(summary = "Update employee")
    @PutMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody final Employee employee, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    /**
     * This is controller for delete employee by id
     *
     * @return {@link ResponseEntity<String>}
     */
    @Operation(summary = "Delete employee")
    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Deleted Successfully For Id | " + id);
    }

}
