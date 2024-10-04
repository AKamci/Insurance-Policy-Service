package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;

public interface carPolicyGateway {

    carPolicy create(carPolicy carPolicy, double amount);
    carPolicy get(carPolicy carPolicy);
    carPolicy update(carPolicy carPolicy);
    carPolicy delete(carPolicy carPolicy);
    carPolicy getList(carPolicy carPolicy);

}
