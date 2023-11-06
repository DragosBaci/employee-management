package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.dto.department.DepartmentRequest;
import employeeManagementFinal.employeeManagement.dto.department.DepartmentResponse;
import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    ResponseEntity<Object> saveDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse getDepartmentById(Long id);
    DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest);
    ResponseEntity<Object> deleteDepartment(Long id);
    List<EmployeeResponse> getEmployeesInDepartment(Long id);
    ResponseEntity<Object> addEmployeeToDepartment(Long employeeId, Long departmentId);
}
