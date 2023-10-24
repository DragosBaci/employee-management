package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.DTO.DepartmentDTO;
import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import employeeManagementFinal.employeeManagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    @Autowired
    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentDTO> departmentDTOs = departments.stream()
                .map(this::mapDepartmentToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(departmentDTOs);
    }
    private DepartmentDTO mapDepartmentToDTO(Department department) {
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        departmentDTO.setEmployeeIds(department.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList()));

        departmentDTO.setSubdepartmentIds(department.getSubdepartments().stream()
                .map(Department::getId)
                .collect(Collectors.toList()));

        return departmentDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        departmentDTO.setEmployeeIds(modelMapper.map(department, List.class));
        return ResponseEntity.ok(departmentDTO);
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
         departmentService.saveDepartment(department);
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        departmentService.updateDepartment(id,department);
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
    @PutMapping("subdepartment/{departmentId}/{subdepartmentId}")
    public ResponseEntity<Object> addSubdepartmentToDepartment(@PathVariable Long departmentId, @PathVariable Long subdepartmentId) {
        try {
            Department department = departmentService.getDepartmentById(departmentId);
            Department subdepartment = departmentService.getDepartmentById(subdepartmentId);

            if(department == null || subdepartment == null) {
                return ResponseEntity.badRequest().body("Department or subdepartment not found");
            }
            department.getSubdepartments().add(subdepartment);
            departmentService.saveDepartment(department);

            subdepartment.setParentDepartment(department);
            departmentService.saveDepartment(subdepartment);

            return ResponseEntity.ok("Subdepartment added to department successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("subdepartment/{parentDepartmentId}")
    public ResponseEntity<List<DepartmentDTO>> getAllSubdepartments(@PathVariable Long parentDepartmentId) {
        List<Department> subdepartments = departmentService.getAllSubdepartments(parentDepartmentId);

        List<DepartmentDTO> subdepartmentDTOs = subdepartments.stream()
                .map(this::mapDepartmentToSubdepartmentDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(subdepartmentDTOs);
    }

    private DepartmentDTO mapDepartmentToSubdepartmentDTO(Department department) {
        DepartmentDTO subdepartmentDTO = new DepartmentDTO();
        subdepartmentDTO.setId(department.getId());
        subdepartmentDTO.setDescription(department.getDescription());
        subdepartmentDTO.setParentDepartmentId(department.getParentDepartment().getId());
        subdepartmentDTO.setEmployeeIds(department.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList()));
        subdepartmentDTO.setSubdepartmentIds(department.getSubdepartments().stream()
                .map(Department::getId)
                .collect(Collectors.toList()));

        return subdepartmentDTO;
    }


}
