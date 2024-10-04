package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String address;
    private String phone;
    private String email;
    private String password;
    private int age;
    private String gender;

    //Navigation Properties
    @Transient
    @org.springframework.data.annotation.Transient
    private List<carPolicy> carPolicy;

    @OneToMany(mappedBy = "customer")

    private List<carPolicy> carPolicies;

}
