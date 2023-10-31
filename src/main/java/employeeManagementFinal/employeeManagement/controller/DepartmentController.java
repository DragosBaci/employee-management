package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.DTO.DepartmentRequest;
import employeeManagementFinal.employeeManagement.DTO.DepartmentResponse;
import employeeManagementFinal.employeeManagement.service.DepartmentService;
import employeeManagementFinal.employeeManagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDepartment(@RequestBody DepartmentRequest departmentRequest) {
         departmentService.createDepartment(departmentRequest);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest) {
        departmentService.updateDepartment(id,departmentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping("subdepartment/{departmentId}/{subdepartmentId}")
    public void addSubdepartmentToDepartment(@PathVariable Long departmentId, @PathVariable Long subdepartmentId) {
            departmentService.createSubdepartment(departmentId,subdepartmentId);
    }

}
