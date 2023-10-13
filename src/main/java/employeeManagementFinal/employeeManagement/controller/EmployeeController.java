package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.DTO.EmployeeDTO;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService,ModelMapper modelMapper){
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(employeeDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employeeService.updateEmployee(id,employee);
        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        return ResponseEntity.ok(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
