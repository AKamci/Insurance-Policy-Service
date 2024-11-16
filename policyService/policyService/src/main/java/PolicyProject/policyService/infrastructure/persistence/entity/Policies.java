package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@SuperBuilder
@Entity
@Data
@Table(name = "Policies")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "policy_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Policies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String policyDescription;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private Double policyAmount;
    private LocalDate policyOfferDate;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private PolicyState state;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "coverage_id")
    private Coverage coverage;
}
