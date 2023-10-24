package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department getDepartmentById(Long id);
    void updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
    List<Department> getAllDepartments();

    List<Department> getAllSubdepartments(Long parentDepartmentId);
}
