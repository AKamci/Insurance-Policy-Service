package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;

import java.sql.Date;

public record CarPolicyModel(

        Long id,
        String policyName, //
        String policyDescription, //
        String policyType, //
        boolean policyStatus,
        Date policyDate, //
        Double policyAmount,
        Long customerId,
        String licensePlateNumber,
        String tckn,
        Date startDate,
        Date endDate,

        //Navigation Properties
        CustomerModel CustomerModel,
        Car car,
        LicensePlate licensePlate


) {



}
