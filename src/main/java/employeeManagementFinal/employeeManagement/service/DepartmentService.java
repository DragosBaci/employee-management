package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.DTO.DepartmentRequest;
import employeeManagementFinal.employeeManagement.DTO.DepartmentResponse;
import employeeManagementFinal.employeeManagement.model.Department;

import java.util.List;

public interface DepartmentService {
    void createDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse getDepartmentById(Long id);
    void updateDepartment(Long id, DepartmentRequest departmentRequest);
    void deleteDepartment(Long id);
    List<DepartmentResponse> getAllDepartments();

     void createSubdepartment(Long departmentId, Long subdepartmentId);

}
