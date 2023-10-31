package employeeManagementFinal.employeeManagement.repository;

import employeeManagementFinal.employeeManagement.model.Department;
import employeeManagementFinal.employeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartment(Department department);
    List<Employee> findByManagerId(Long managerId);
}
