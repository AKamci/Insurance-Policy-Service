package PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "Building")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int apartmentNumber;

    private Integer constructionStyle;

    private Integer constructionYear;

    private Integer totalFloors;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany
    private List<House> houses;
}
