package employeeManagementFinal.employeeManagement.converters;

import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeIdListConverter extends AbstractConverter<Department, List<Long>> {

    @Override
    protected List<Long> convert(Department department) {
        return department.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }
}