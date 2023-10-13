package employeeManagementFinal.employeeManagement.service;

import employeeManagementFinal.employeeManagement.entity.Department;
import employeeManagementFinal.employeeManagement.entity.Employee;
import employeeManagementFinal.employeeManagement.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> saveAllEmployees(List<Employee> employees) { return employeeRepository.saveAll(employees);}

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getReferenceById(id);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }

        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }

        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }

        return employeeRepository.saveAndFlush(existingEmployee);
    }


    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }
}
