package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.dto.department.DepartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubdepartmentService {
    ResponseEntity<Object> addSubdepartment(Long departmentId, Long SubdepartmentId);
    List<DepartmentResponse> getAllSubdepartments(Long parentDepartmentId);
}
