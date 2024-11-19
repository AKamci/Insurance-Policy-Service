package PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@SuperBuilder
@Entity
@Data
@Table(name = "EartquakePolicy")
@NoArgsConstructor
@AllArgsConstructor
public class EarthquakePolicy extends Policies {


   @JsonIgnore
   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @ManyToOne
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "house_id")
   private House house;
}
