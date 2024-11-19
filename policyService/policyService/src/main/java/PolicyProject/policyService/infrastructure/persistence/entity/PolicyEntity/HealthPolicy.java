package PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name = "HealthPolicy")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HealthPolicy extends Policies {


    @ManyToOne
    @JoinColumn(name = "personal_health_id")
    private PersonalHealth personalHealth;

}
