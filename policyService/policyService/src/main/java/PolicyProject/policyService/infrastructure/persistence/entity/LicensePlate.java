package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "LicensePlate")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "plate", nullable = false, unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

}
