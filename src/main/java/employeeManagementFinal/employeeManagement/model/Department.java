package employeeManagementFinal.employeeManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="parentDepartment")
    private Department parentDepartment;

    @OneToMany(mappedBy="parentDepartment")
    private Set<Department> subdepartments = new HashSet<>();

}
