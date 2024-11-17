package PolicyProject.policyService.domain.dto.response.PersonalHealthResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

import java.time.LocalDateTime;

public record GetPersonalHealthWithCustomerResponse(

        Long id,
        Integer height,
        Double weight,
        Double bmi,
        String bloodType,
        Boolean alcoholConsumption,
        Boolean smokeConsumption,
        Boolean isPregnant,
        Boolean hasDisability,
        Boolean hasPreviousSurgeries,
        LocalDateTime createdAt,
        Customer customer
) {
}
