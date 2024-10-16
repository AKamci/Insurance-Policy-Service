package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;

import java.sql.Date;
import java.time.LocalDate;

public record CarPolicyModel(

        Long id,
        String policyName, //
        String policyDescription, //
        String policyType, //
        boolean policyStatus,//
        Double policyAmount,
        Long customerId,
        String licensePlateNumber,
        String tckn,
        LocalDate policyStartDate,
        LocalDate policyEndDate,
        int page,
        int size,

        //Navigation Properties
        CustomerModel CustomerModel,
        Car car,
        LicensePlate licensePlate


) {



}
