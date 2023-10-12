package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    public DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) { return departmentRepository.save(department); }

    public Department getDepartmentById(Long id) { return departmentRepository.getReferenceById(id); }

    public Department updateDepartment(Long id, Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public void deleteDepartment(Long id) { departmentRepository.deleteById(id);}

    public List<Department> getAllDepartments() { return departmentRepository.findAll();}
}
