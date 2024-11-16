package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.BloodType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record PersonalHealthModel(

 Long id,

 String tckn,

 Integer height,

 Double weight,

 Double bmi,

@Enumerated(EnumType.ORDINAL)
 BloodType bloodType,

 Boolean alcoholConsumption,

 Boolean smokeConsumption,

 Boolean isPregnant,

 Boolean hasDisability,

 Boolean hasPreviousSurgeries


) {
}
