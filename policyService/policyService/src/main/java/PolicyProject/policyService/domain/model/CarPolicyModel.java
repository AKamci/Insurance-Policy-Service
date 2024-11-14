package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;

import java.sql.Date;
import java.time.LocalDate;

public record CarPolicyModel(

        Long policyId,
        LocalDate policyOfferDate, //
        String policyDescription, //
        Integer coverage,
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
