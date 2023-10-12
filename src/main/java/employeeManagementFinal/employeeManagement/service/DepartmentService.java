package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) { return departmentRepository.save(department); }

    public Optional<Department> getDepartmentById(Long id) { return departmentRepository.findById(id); }

    public Department updateDepartment(Long id, Department department) { return departmentRepository.saveAndFlush(department);}

    public void deleteDepartment(Long id) { departmentRepository.deleteById(id);}

    public List<Department> getAllDepartments() { return departmentRepository.findAll();}
}
