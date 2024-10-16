package PolicyProject.policyService.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String tckn;
    private String address;
    private String phone;
    private String email;
    private String password;
    private LocalDate birthDay;
    private String gender;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    private List<CarPolicy> carPolicies;

}
