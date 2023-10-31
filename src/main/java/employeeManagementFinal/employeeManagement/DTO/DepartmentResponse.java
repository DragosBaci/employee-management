package employeeManagementFinal.employeeManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String description;
    private List<DepartmentResponse> subdepartments;
    private Long parentDepartmentId;
}
