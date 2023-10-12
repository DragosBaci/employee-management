package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.DTO.DepartmentDTO;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.forms.DepartmentForm;
import employeeManagementFinal.employeeManagement.forms.UserMappper;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import employeeManagementFinal.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    @Autowired
    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();

        for (Department department : departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setDescription(department.getDescription());

            List<Long> employeeIds = department.getEmployees().stream()
                    .map(Employee::getId)
                    .collect(Collectors.toList());
            departmentDTO.setEmployeeIds(employeeIds);

            departmentDTOs.add(departmentDTO);
        }

        return ResponseEntity.ok(departmentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentForm departmentForm) {
        Department department = UserMappper.toUser(departmentForm);
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.updateDepartment(id,department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        if(department == null) {
            return ResponseEntity.badRequest().body("Department doesent exist");
        } else {
            List<Employee> employeesInDepartment = employeeService.getEmployeesByDepartment(department);
            for (Employee employee : employeesInDepartment) {
                employee.setDepartment(null);
            }
            employeeService.saveAllEmployees(employeesInDepartment);

            departmentService.deleteDepartment(department.getId());

        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{departmentId}/{employeeId}")
    public ResponseEntity<Object> addEmployeeToDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        try {
            Department department = departmentService.getDepartmentById(departmentId);
            Employee employee = employeeService.getEmployeeById(employeeId);

            if (department == null || employee == null) {
                return ResponseEntity.badRequest().body("Department or Employee not found");
            }

            department.getEmployees().add(employee);
            departmentService.saveDepartment(department);

            employee.setDepartment(department);
            employeeService.saveEmployee(employee);

            return ResponseEntity.ok("Employee added to the department successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
