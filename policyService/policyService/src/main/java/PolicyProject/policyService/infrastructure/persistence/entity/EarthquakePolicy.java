package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@Table(name = "EartquakePolicy")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue("EARTHQUAKE")
public class EarthquakePolicy extends Policies {


   @ManyToOne
   @JoinColumn(name = "house_id")
   private House house;

}
