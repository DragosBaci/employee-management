package employeeManagementFinal.employeeManagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Long departmentId;
    private Long managerId;
    private List<Long> employeesIDs;
}
