package PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "House")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private Integer squareMeters;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
