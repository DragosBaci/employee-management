package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManagerService {
    ResponseEntity<Object> addEmployeeToManager(Long managerId, Long subordinateId);
    List<EmployeeResponse> getAllSubordinates(Long managerId);
}
