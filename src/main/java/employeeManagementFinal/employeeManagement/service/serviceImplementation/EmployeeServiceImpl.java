package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.dto.employee.EmployeeRequest;
import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.exception.employeeExceptions.EmployeeCreationException;
import employeeManagementFinal.employeeManagement.exception.employeeExceptions.EmployeeNotFoundException;
import employeeManagementFinal.employeeManagement.exception.employeeExceptions.EmployeeUpdateException;
import employeeManagementFinal.employeeManagement.repository.EmployeeRepository;
import employeeManagementFinal.employeeManagement.service.EmployeeService;
import employeeManagementFinal.employeeManagement.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<Object> saveEmployee(EmployeeRequest employeeRequest) {
        try {
            Employee employee = Employee.builder()
                    .email(employeeRequest.getEmail())
                    .name(employeeRequest.getName())
                    .imageUri(employeeRequest.getImageUri())
                    .build();
            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee saved successfully");
        } catch (EmployeeCreationException e) {
            throw new EmployeeCreationException();
        }
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeResponse).toList();
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return EmployeeMapper.mapToEmployeeResponse(employee);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        existingEmployee.setName(employeeRequest.getName());
        existingEmployee.setEmail(employeeRequest.getEmail());
        existingEmployee.setImageUri(employeeRequest.getImageUri());
        if (existingEmployee.getEmail() != null && existingEmployee.getName() != null) {
            employeeRepository.saveAndFlush(existingEmployee);
            return EmployeeMapper.mapToEmployeeResponse(existingEmployee);
        } else {
            throw new EmployeeUpdateException();
        }
    }

    public ResponseEntity<Object> deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}


