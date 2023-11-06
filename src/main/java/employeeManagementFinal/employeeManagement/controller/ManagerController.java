package employeeManagementFinal.employeeManagement.controller;

import employeeManagementFinal.employeeManagement.dto.employee.EmployeeResponse;
import employeeManagementFinal.employeeManagement.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    @PutMapping("/{managerId}/{subordinateId}")
    public ResponseEntity<Object> addEmployeeToManager(@PathVariable Long managerId, @PathVariable Long subordinateId) {
        return ResponseEntity.ok(managerService.addEmployeeToManager(managerId, subordinateId));
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<List<EmployeeResponse>> getAllSubordinates(@PathVariable Long managerId){
        List<EmployeeResponse> subordinates = managerService.getAllSubordinates(managerId);
        return ResponseEntity.ok(subordinates);
    }

}
