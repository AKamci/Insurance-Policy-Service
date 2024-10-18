package PolicyProject.policyService.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

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


    @Required
    private String name;
    @Column(unique = true, nullable = false)
    @Required @Size(min = 11, max = 11)
    private String tckn;
    @Required
    private String address;
    @Column(unique = true, nullable = false)
    private String phone;
    @Email @Required
    private String email;
    private String password;
    @Required
    private LocalDate birthDay;
    @Required
    private String gender;


    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    private List<CarPolicy> carPolicies;

}
