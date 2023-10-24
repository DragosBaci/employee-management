package employeeManagementFinal.employeeManagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class DepartmentDTO {

    private Long id;
    private String description;
    private List<Long> employeeIds;


}