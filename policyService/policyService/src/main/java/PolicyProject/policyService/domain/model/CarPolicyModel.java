package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;

import java.time.LocalDate;

public record CarPolicyModel(

        Long policyId,
        LocalDate policyOfferDate, //
        String policyDescription, //
        Integer coverageCode,
        Coverage coverage,
        Double policyAmount,
        Long customerId,
        String licensePlateNumber,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        CarPolicyState state,
        int page,
        int size,

        //Navigation Properties
        CustomerModel CustomerModel,
        Car car,
        LicensePlate licensePlate


) {



}
