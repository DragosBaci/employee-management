package employeeManagementFinal.employeeManagement.forms;

import employeeManagementFinal.employeeManagement.entity.Department;

public class UserMappper {

    public static Department toUser(DepartmentForm departmentForm) {
        return new Department(departmentForm.getId(), departmentForm.getDescription(),null);
    }
}
