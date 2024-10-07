package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

import java.sql.Date;

public record carPolicyModel(

        Long id,
        String policyName, //
        String policyDescription, //
        String policyType, //
        boolean policyStatus,
        Date policyDate, //
        Double policyAmount,
        Long customerId,

        //Navigation Properties
        CustomerModel CustomerModel,
        Car car


) {



}
