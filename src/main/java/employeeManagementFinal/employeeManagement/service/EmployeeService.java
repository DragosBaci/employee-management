package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.model.Department;
import employeeManagementFinal.employeeManagement.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> saveAllEmployees(List<Employee> employees);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByDepartment(Department department);

    List<Employee> getAllSubordinates(Long managerId);
}