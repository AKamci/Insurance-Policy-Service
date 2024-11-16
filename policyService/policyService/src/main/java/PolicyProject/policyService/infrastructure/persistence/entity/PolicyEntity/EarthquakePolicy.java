package PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@Table(name = "EartquakePolicy")
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EARTHQUAKE")
public class EarthquakePolicy extends Policies {


   @ManyToOne
   @JoinColumn(name = "house_id")
   private House house;

}
