package employeeManagementFinal.employeeManagement.repository;

import employeeManagementFinal.employeeManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByParentDepartmentId(Long parentDepartmentId);
}

