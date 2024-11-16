package PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy;

import PolicyProject.policyService.domain.Enums.Enums.BloodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PersonalHealth")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalHealth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
