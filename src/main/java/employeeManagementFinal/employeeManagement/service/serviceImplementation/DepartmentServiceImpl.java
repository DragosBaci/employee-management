package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    public DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) { return departmentRepository.save(department); }

    public Department getDepartmentById(Long id) { return departmentRepository.getReferenceById(id); }

    public void updateDepartment(Long id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        existingDepartment.setDescription(department.getDescription());
         departmentRepository.saveAndFlush(existingDepartment);
    }

    public void deleteDepartment(Long id) { departmentRepository.deleteById(id);}

    public List<Department> getAllDepartments() { return departmentRepository.findAll();}
}


