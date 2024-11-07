package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Builder
@Entity
@Table(name = "Weights")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weights implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key;
    private BigDecimal weight;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String type;


    public void nullifyValuesIfNeeded(List<String> typesToNullify) {
        if (typesToNullify.contains(this.type)) {
            this.minValue = null;
            this.maxValue = null;
        }
    }

}
