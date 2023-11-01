package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.DTO.DepartmentRequest;
import employeeManagementFinal.employeeManagement.DTO.DepartmentResponse;
import employeeManagementFinal.employeeManagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse getDepartmentById(Long id);
    void updateDepartment(Long id, DepartmentRequest departmentRequest);
    void deleteDepartment(Long id);



}
