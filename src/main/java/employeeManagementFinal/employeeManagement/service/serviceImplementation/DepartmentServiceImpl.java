package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.DTO.DepartmentRequest;
import employeeManagementFinal.employeeManagement.DTO.DepartmentResponse;
import employeeManagementFinal.employeeManagement.model.Department;
import employeeManagementFinal.employeeManagement.model.Employee;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public void createDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .description(departmentRequest.getDescription())
                .build();
        departmentRepository.save(department);
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.getReferenceById(id);
        Set<Department> visited = new HashSet<>();
        return mapToDepartmentResponse(department, visited);
    }

    public void updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        existingDepartment.setDescription(departmentRequest.getDescription());
        departmentRepository.saveAndFlush(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        Set<Department> visited = new HashSet<>();

        return departments.stream()
                .map(department -> mapToDepartmentResponse(department, visited))
                .filter(Objects::nonNull)
                .toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department, Set<Department> visited) {
        if (visited.contains(department)) {
            return null;
        }

        visited.add(department);

        DepartmentResponse.DepartmentResponseBuilder builder = DepartmentResponse.builder()
                .id(department.getId())
                .description(department.getDescription());


        if (department.getSubdepartments() != null && !department.getSubdepartments().isEmpty()) {
            List<DepartmentResponse> subdepartmentResponses = department.getSubdepartments()
                    .stream()
                    .map(subdepartment -> mapToDepartmentResponse(subdepartment, visited))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            builder.subdepartments(subdepartmentResponses);
        }

        if (department.getParentDepartment() != null) {
            //DepartmentResponse parentDepartmentResponse = mapToDepartmentResponse(department.getParentDepartment(), visited);
            builder.parentDepartmentId( department.getParentDepartment().getId());
        }

        return builder.build();
    }


    public void createSubdepartment(Long departmentId, Long subdepartmentId) {
        Department department = departmentRepository.getReferenceById(departmentId);
        Department subdepartment = departmentRepository.getReferenceById(subdepartmentId);


        department.getSubdepartments().add(subdepartment);
        subdepartment.setParentDepartment(department);

        departmentRepository.save(department);
        departmentRepository.save(subdepartment);

    }

}

