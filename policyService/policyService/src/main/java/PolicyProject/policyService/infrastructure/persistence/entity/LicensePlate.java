package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "LicensePlate")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicensePlate implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "plate", nullable = false, unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

}
