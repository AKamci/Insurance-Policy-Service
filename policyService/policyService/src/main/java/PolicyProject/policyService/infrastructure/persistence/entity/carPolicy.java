package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;


@Entity
@Table(name = "CarPolicy")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class carPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String policyName; //
    private String policyDescription; //
    private String policyType; //
    private boolean policyStatus;
    private Date policyDate; //
    private Double policyAmount;
    private String licensePlate;



    //Isimlendirme
    //Navigation Properties

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Transient
    private Car car;
}
