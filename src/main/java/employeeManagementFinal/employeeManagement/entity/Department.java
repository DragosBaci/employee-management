package employeeManagementFinal.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")
@ToString
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long departmentId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "parentDepartment")
    private List<Department> subDepartments;

}
