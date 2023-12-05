package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.dto.employee.EmployeeRequest;
import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<Object> saveEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);
    ResponseEntity<Object> deleteEmployee(Long id);
    List<EmployeeResponse> getAllEmployeesInDepartmentAndSubdepartments(Long departmentId);
}