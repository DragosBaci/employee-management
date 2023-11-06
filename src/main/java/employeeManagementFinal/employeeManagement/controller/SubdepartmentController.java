package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.dto.department.DepartmentResponse;
import employeeManagementFinal.employeeManagement.service.SubdepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subdepartment")
public class SubdepartmentController {

    private final SubdepartmentService subdepartmentService;

    @PutMapping("/{departmentId}/{subdepartmentId}")
    public ResponseEntity<Object> addSubdepartmentToDepartment(@PathVariable Long departmentId, @PathVariable Long subdepartmentId) {
        return ResponseEntity.ok(subdepartmentService.addSubdepartment(departmentId,subdepartmentId));
    }

    @GetMapping("/{parentDepartmentId}")
    public ResponseEntity<List<DepartmentResponse>> getAllSubdepartments(@PathVariable Long parentDepartmentId) {
        return ResponseEntity.ok(subdepartmentService.getAllSubdepartments(parentDepartmentId));
    }

}
