package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.exception.departmentExceptions.DepartmentNotFoundException;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import employeeManagementFinal.employeeManagement.service.SubdepartmentService;
import employeeManagementFinal.employeeManagement.util.DepartmentMapper;
import employeeManagementFinal.employeeManagement.dto.department.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubdepartmentServiceImpl implements SubdepartmentService {

    private final DepartmentRepository departmentRepository;

    public ResponseEntity<Object> addSubdepartment(Long departmentId, Long subdepartmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
        Department subdepartment = departmentRepository.findById(subdepartmentId).orElseThrow(() -> new DepartmentNotFoundException(subdepartmentId));

        department.getSubdepartments().add(subdepartment);
        departmentRepository.save(department);

        subdepartment.setParentDepartment(department);
        departmentRepository.save(subdepartment);

        return ResponseEntity.ok("Subdepartment added to department successfully");
    }

    public List<DepartmentResponse> getAllSubdepartments(Long parentDepartmentId){
        List<Department> subdepartments = departmentRepository.findByParentDepartmentId(parentDepartmentId);
        return subdepartments.stream()
                .map(DepartmentMapper::mapToDepartmentResponse)
                .collect(Collectors.toList());
    }
}
