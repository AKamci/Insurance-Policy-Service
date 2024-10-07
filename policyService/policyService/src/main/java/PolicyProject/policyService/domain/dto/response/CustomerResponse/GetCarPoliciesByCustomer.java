package PolicyProject.policyService.domain.dto.response.CustomerResponse;

import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

public record GetCarPoliciesByCustomer(


        CarPolicy carPolicy


) {
}
