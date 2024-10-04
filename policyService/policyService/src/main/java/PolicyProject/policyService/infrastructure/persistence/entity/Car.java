package PolicyProject.policyService.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Car {

    private String make;
    private String model;
    private int year;
    private String engine;
    private int kilometers;
    private int price;
}
