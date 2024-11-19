package PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PersonalHealth")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalHealth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "bmi")
    private Double bmi;

    @Column(name = "blood_type")
    @Enumerated(EnumType.ORDINAL)
    private BloodType bloodType;

    private Boolean alcoholConsumption;

    private Boolean smokeConsumption;

    private Boolean isPregnant;

    private Boolean hasDisability;

    private Boolean hasPreviousSurgeries;

    private LocalDateTime createdAt;



    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
