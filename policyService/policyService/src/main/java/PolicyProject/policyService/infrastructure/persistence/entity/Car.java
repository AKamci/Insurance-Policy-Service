package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table(name = "Car")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private int year;
    private String engine;
    private int kilometers;
    private int price;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LicensePlate> licensePlates = new HashSet<>();


}
