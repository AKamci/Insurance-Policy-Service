package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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




    @OneToMany(mappedBy = "licensePlate", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<CarPolicy> carPolicies;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;





}
