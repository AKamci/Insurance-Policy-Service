package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@Entity
@Table(name = "CarPolicy")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarPolicy extends Policies {

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "license_plate_id", nullable = false)
    private LicensePlate licensePlate;

}
