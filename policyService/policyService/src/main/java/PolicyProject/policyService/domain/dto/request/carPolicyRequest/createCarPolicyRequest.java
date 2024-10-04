package PolicyProject.policyService.domain.dto.request.carPolicyRequest;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;

import java.sql.Date;

public record createCarPolicyRequest(

         String policyName,
         String policyDescription,
         String policyType,
         boolean policyStatus,
         Date policyDate,

         Long customerId,
         Car car


) implements ICarPolicyRequest{
}
