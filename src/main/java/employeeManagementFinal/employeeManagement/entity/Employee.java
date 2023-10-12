package employeeManagementFinal.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department=" + (department != null ? department.getId() : null) + // Reference the department's ID
                '}';
    }
}