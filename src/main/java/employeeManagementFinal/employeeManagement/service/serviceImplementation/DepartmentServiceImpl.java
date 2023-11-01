package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.DTO.DepartmentDTO;
import employeeManagementFinal.employeeManagement.DTO.DepartmentRequest;
import employeeManagementFinal.employeeManagement.DTO.DepartmentResponse;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public void saveDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .description(departmentRequest.getDescription())
                .build();
        departmentRepository.save(department);
    }

    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.getReferenceById(id);
        return mapToDepartmentResponse(department);
    }

    public void updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        existingDepartment.setDescription(departmentRequest.getDescription());
         departmentRepository.saveAndFlush(existingDepartment);
    }

    public void deleteDepartment(Long id) { departmentRepository.deleteById(id);}

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        List<Long> employeeIds = department.getEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        List<Long> subdepartmentIds = department.getSubdepartments()
                .stream()
                .map(Department::getId)
                .collect(Collectors.toList());

        return DepartmentResponse.builder()
                .id(department.getId())
                .description(department.getDescription())
                .employeeIds(employeeIds)
                .subdepartmentIds(subdepartmentIds)
                .build();
    }
}


