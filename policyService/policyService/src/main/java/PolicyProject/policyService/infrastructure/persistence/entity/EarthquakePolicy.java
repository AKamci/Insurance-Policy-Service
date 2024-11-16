package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
