package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name = "HealthPolicy")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("HEALTH")
public class HealthPolicy extends Policies {


    @ManyToOne
    @JoinColumn(name = "personal_health_id")
    private PersonalHealth personalHealth;

}
