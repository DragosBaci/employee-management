package employeeManagementFinal.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id",  referencedColumnName = "id", foreignKey = @ForeignKey(name = "departmentKey"))
    private Department department;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees = new ArrayList<>();
}