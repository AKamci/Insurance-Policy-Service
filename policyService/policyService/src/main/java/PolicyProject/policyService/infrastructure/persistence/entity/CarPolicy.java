package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "CarPolicy")
@NoArgsConstructor
@AllArgsConstructor
@Data


public class CarPolicy implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String policyDescription;
    private String policyType;
    private Boolean policyStatus;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private Double policyAmount;

    private LocalDate policyOfferDate;

    @ManyToOne
    @JoinColumn(name = "license_plate_id", nullable = false)
    private LicensePlate licensePlate;

    //Navigation Properties

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
