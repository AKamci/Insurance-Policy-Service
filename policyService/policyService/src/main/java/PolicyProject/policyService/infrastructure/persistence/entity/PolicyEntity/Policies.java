package PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@SuperBuilder
@Entity
@Data
@Table(name = "Policies")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Policies {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policies_seq")
    @SequenceGenerator(name = "policies_seq", sequenceName = "policies_sequence", allocationSize = 1)
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
