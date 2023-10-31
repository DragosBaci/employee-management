package employeeManagementFinal.employeeManagement.converters;

import employeeManagementFinal.employeeManagement.model.Department;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class SubDepartmentIdListConverter extends AbstractConverter<Department, List<Long>> {

    @Override
    protected List<Long> convert(Department department) {
        return department.getSubdepartments().stream()
                .map(Department::getId)
                .collect(Collectors.toList());
    }
}
