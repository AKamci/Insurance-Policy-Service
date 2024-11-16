package PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Table(name = "Car")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private int year;
    private double engine;
    private int kilometers;
    private int price;
    private double type;



    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LicensePlate> licensePlates = new HashSet<>();


}
