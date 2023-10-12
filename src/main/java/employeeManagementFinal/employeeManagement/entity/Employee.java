package employeeManagementFinal.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@ToString
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;


}