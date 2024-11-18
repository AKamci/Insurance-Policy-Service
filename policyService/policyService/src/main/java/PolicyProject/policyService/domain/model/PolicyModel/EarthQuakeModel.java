package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;

import java.time.LocalDate;

public record EarthQuakeModel(


        Long policyId,
        Long houseId,
        Integer coverageCode,
        LocalDate policyOfferDate, //
        String policyDescription, //
        Coverage coverage,
        Double policyAmount,
        Long customerId,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        PolicyState state,
        int page,
        int size,

        Integer number,
        Integer apartmentNumber,
        String city,
        String district,
        String neighborhood,



        //Navigation Properties
        CustomerModel CustomerModel,
        House house
) {
}
