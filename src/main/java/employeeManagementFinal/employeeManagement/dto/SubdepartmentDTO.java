package employeeManagementFinal.employeeManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubdepartmentDTO {

    private Long id;
    private String description;
    private List<Long> employeeIds;
    private Long parentDepartmentId;
}

