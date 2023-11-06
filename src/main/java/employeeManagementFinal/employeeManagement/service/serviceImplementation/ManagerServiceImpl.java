package employeeManagementFinal.employeeManagement.service.serviceImplementation;

import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.exception.employeeExceptions.EmployeeNotFoundException;
import employeeManagementFinal.employeeManagement.repository.EmployeeRepository;
import employeeManagementFinal.employeeManagement.service.ManagerService;
import employeeManagementFinal.employeeManagement.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<Object> addEmployeeToManager(Long managerId, Long subordinateId) {
        Employee manager = employeeRepository.findById(managerId).orElseThrow(() -> new EmployeeNotFoundException(managerId));
        Employee subordinate = employeeRepository.findById(subordinateId).orElseThrow(() -> new EmployeeNotFoundException(subordinateId));

        manager.getSubordinates().add(subordinate);
        employeeRepository.save(manager);

        subordinate.setManager(manager);
        employeeRepository.save(subordinate);

        return ResponseEntity.ok("Subordinate added to Manager successfully.");
    }

    public List<EmployeeResponse> getAllSubordinates(Long managerId) {
        return employeeRepository.findByManagerId(managerId).stream().map(EmployeeMapper::mapToEmployeeResponse).toList();}
}
