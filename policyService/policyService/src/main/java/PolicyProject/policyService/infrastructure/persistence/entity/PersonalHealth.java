package PolicyProject.policyService.infrastructure.persistence.entity;

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







    @ManyToOne
    @JoinColumn(name = "health_policy_id")
    private HealthPolicy healthPolicy;



}
