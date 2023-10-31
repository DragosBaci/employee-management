//package employeeManagementFinal.employeeManagement.controller;
//
//import employeeManagementFinal.employeeManagement.DTO.EmployeeDTO;
//import employeeManagementFinal.employeeManagement.model.Employee;
//import employeeManagementFinal.employeeManagement.service.EmployeeService;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequestMapping("/api/employee")
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//    private final ModelMapper modelMapper;
//
//    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper){
//        this.employeeService = employeeService;
//        this.modelMapper = modelMapper;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
//        List<Employee> employees = employeeService.getAllEmployees();
//        List<EmployeeDTO> employeeDTOs = employees.stream()
//                .map(this::mapEmployeesToDTO)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(employeeDTOs);
//    }
//    private EmployeeDTO mapEmployeesToDTO(Employee employee) {
//        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
//        employeeDTO.setEmployeesIDs(employee.getEmployees().stream()
//                .map(Employee::getId)
//                .collect(Collectors.toList()));
//        return employeeDTO;
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
//        Employee employee = employeeService.getEmployeeById(id);
//        if (employee == null) {
//            return ResponseEntity.notFound().build();
//        }
//        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
//        return ResponseEntity.ok(employeeDTO);
//    }
//
//    @PutMapping("manager/{managerId}/{employeeId}")
//    public ResponseEntity<Object> addEmployeeToManager(@PathVariable Long managerId, @PathVariable Long employeeId) {
//        try {
//            Employee manager = employeeService.getEmployeeById(managerId);
//            Employee employee = employeeService.getEmployeeById(employeeId);
//
//            if(manager == null || employee == null) {
//                return ResponseEntity.badRequest().body("Employee or Manager not found");
//            }
//            manager.getEmployees().add(employee);
//            employeeService.saveEmployee(manager);
//
//            employee.setManager(manager);
//            employeeService.saveEmployee(employee);
//
//            return ResponseEntity.ok("Employee added to manager successfully");
//        } catch (Exception e) {
//            return  ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
//        return ResponseEntity.ok(employeeService.saveEmployee(employee));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
//        employeeService.updateEmployee(id,employee);
//        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
//        return ResponseEntity.ok(employeeDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("manager/{managerId}")
//    public ResponseEntity<List<EmployeeDTO>> getAllSubordinates(@PathVariable Long managerId){
//        List<Employee> subordinates = employeeService.getAllSubordinates(managerId);
//
//        List<EmployeeDTO> subordinatesDTOs = subordinates.stream()
//                .map(this::mapManagerToSubordinateDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(subordinatesDTOs);
//    }
//
//    public  EmployeeDTO mapManagerToSubordinateDTO(Employee employee) {
//        EmployeeDTO subordinateDTO = new EmployeeDTO();
//        subordinateDTO.setId(employee.getId());
//        subordinateDTO.setEmail(employee.getEmail());
//        subordinateDTO.setName(employee.getName());
//        subordinateDTO.setManagerId(employee.getManager().getId());
//        return subordinateDTO;
//    }
//}
