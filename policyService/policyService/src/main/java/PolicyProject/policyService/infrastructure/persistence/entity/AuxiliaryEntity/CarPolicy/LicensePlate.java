package PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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



    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "licensePlate", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<CarPolicy> carPolicies;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;





}
