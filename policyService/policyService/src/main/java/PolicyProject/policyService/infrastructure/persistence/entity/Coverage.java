package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.CoverageTypeConverter;
import PolicyProject.policyService.domain.Enums.Enums.CoverageType;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.Policies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "Coverage")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Coverage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String coverageDescription;

    @Convert(converter = CoverageTypeConverter.class)
    private CoverageType coverageType;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "coverage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Policies> policiesList;
}


