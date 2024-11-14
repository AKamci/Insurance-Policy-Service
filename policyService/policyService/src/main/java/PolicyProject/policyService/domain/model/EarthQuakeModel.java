package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;

import java.time.LocalDate;

public record EarthQuakeModel(


        Long policyId,
        LocalDate policyOfferDate, //
        String policyDescription, //
        Integer coverage,
        Double policyAmount,
        Long customerId,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        PolicyState state,
        int page,
        int size,

        //Navigation Properties
        CustomerModel CustomerModel,
        House house
) {
}
