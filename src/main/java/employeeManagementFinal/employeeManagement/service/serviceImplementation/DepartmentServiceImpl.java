package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.dto.department.DepartmentRequest;
import employeeManagementFinal.employeeManagement.dto.department.DepartmentResponse;
import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.exception.departmentExceptions.DepartmentCreationException;
import employeeManagementFinal.employeeManagement.exception.departmentExceptions.DepartmentDeletionException;
import employeeManagementFinal.employeeManagement.exception.departmentExceptions.DepartmentNotFoundException;
import employeeManagementFinal.employeeManagement.exception.departmentExceptions.DepartmentUpdateException;
import employeeManagementFinal.employeeManagement.exception.employeeExceptions.EmployeeNotFoundException;
import employeeManagementFinal.employeeManagement.repository.DepartmentRepository;
import employeeManagementFinal.employeeManagement.repository.EmployeeRepository;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import employeeManagementFinal.employeeManagement.service.EmployeeService;
import employeeManagementFinal.employeeManagement.util.DepartmentMapper;
import employeeManagementFinal.employeeManagement.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public ResponseEntity<Object> saveDepartment(DepartmentRequest departmentRequest) {
        try {
            Department department = Department.builder()
                    .description(departmentRequest.getDescription())
                    .imageUri(departmentRequest.getImageUri())
                    .build();
            departmentRepository.save(department);
            return ResponseEntity.ok("Department saved successfully.");
        } catch (DepartmentCreationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(DepartmentMapper::mapToDepartmentResponse).toList();
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return DepartmentMapper.mapToDepartmentResponse(department);
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        existingDepartment.setDescription(departmentRequest.getDescription());
        existingDepartment.setImageUri(departmentRequest.getImageUri());
        if(existingDepartment.getDescription() != null) {
            departmentRepository.saveAndFlush(existingDepartment);
            return DepartmentMapper.mapToDepartmentResponse(existingDepartment);
        }
        else {
            throw new DepartmentUpdateException();
        }
    }

    public ResponseEntity<Object> deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));

        for (Employee employee : department.getEmployees()) {
            employee.setDepartment(null);
        }

        Department parentDepartment = department.getParentDepartment();
        if (parentDepartment != null) {
            parentDepartment.getSubdepartments().remove(department);
        }

        for (Department subdepartment : department.getSubdepartments()) {
            subdepartment.setParentDepartment(null);
        }

        departmentRepository.delete(department);
        return ResponseEntity.ok("Department deleted successfully.");
    }


    public List<EmployeeResponse> getEmployeesInDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
       return department.getEmployees().stream().map(EmployeeMapper::mapToEmployeeResponse).toList();
    }

    public ResponseEntity<Object> addEmployeeToDepartment(Long employeeId, Long departmentId) {
        try {
            Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));

            department.getEmployees().add(employee);
            departmentRepository.saveAndFlush(department);

            employee.setDepartment(department);
            employeeRepository.saveAndFlush(employee);

            return ResponseEntity.ok("Employee added to the department successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}


