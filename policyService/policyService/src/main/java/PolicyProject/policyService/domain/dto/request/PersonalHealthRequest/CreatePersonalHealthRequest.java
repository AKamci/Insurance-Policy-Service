package PolicyProject.policyService.domain.dto.request.PersonalHealthRequest;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public record CreatePersonalHealthRequest(

        @NotBlank(message = "TCKN boş olamaz.")
        @Pattern(regexp = "\\d{11}", message = "TCKN 11 haneli bir sayı olmalıdır.")
        String tckn,

        @NotNull(message = "Coverage code boş olamaz.")
        @Min(value = 100, message = "Coverage code en az 1 olmalıdır.")
        Integer coverageCode,

        @NotNull(message = "Boy bilgisi boş olamaz.")
        @Min(value = 50, message = "Boy 50 cm'den küçük olamaz.")
        @Max(value = 250, message = "Boy 250 cm'den büyük olamaz.")
        Integer height,

        @NotNull(message = "Kilo bilgisi boş olamaz.")
        @Min(value = 20, message = "Kilo 20 kg'dan küçük olamaz.")
        @Max(value = 300, message = "Kilo 300 kg'dan büyük olamaz.")
        Double weight,

        @NotNull(message = "BMI boş olamaz.")
        @DecimalMin(value = "10.0", inclusive = true, message = "BMI en az 10.0 olmalıdır.")
        @DecimalMax(value = "40.0", inclusive = true, message = "BMI en fazla 40.0 olmalıdır.")
        Double bmi,

        @NotNull(message = "Kan grubu boş olamaz.")
        @Enumerated(EnumType.ORDINAL)
        BloodType bloodType,

        @NotNull(message = "Alkol kullanımı bilgisi boş olamaz.")
        Boolean alcoholConsumption,

        @NotNull(message = "Sigara kullanımı bilgisi boş olamaz.")
        Boolean smokeConsumption,

        @NotNull(message = "Hamilelik bilgisi boş olamaz.")
        Boolean isPregnant,

        @NotNull(message = "Engellilik durumu boş olamaz.")
        Boolean hasDisability,

        @NotNull(message = "Geçmiş ameliyat bilgisi boş olamaz.")
        Boolean hasPreviousSurgeries


) {
}
