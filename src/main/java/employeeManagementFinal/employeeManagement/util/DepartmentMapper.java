package employeeManagementFinal.employeeManagement.util;

import employeeManagementFinal.employeeManagement.dto.department.DepartmentResponse;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentResponse mapToDepartmentResponse(Department department) {
        List<Long> employeeIds = department.getEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        List<Long> subdepartmentIds = department.getSubdepartments()
                .stream()
                .map(Department::getId)
                .collect(Collectors.toList());

        Long parentDepartmentId = department.getParentDepartment() != null ? department.getParentDepartment().getId() : null;

        return DepartmentResponse.builder()
                .id(department.getId())
                .description(department.getDescription())
                .imageUri(department.getImageUri())
                .employeeIds(employeeIds)
                .subdepartmentIds(subdepartmentIds)
                .parentDepartmentId(parentDepartmentId)
                .build();
    }
}