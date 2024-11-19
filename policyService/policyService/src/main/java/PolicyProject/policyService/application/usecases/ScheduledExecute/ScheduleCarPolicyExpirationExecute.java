package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleCarPolicyExpirationExecute {

    CarPolicyGateway carPolicyGateway;

    ScheduleCarPolicyExpirationExecute(CarPolicyGateway carPolicyGateway) {
        this.carPolicyGateway = carPolicyGateway;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void expirePolicies() {
        List<CarPolicy> expiredPolicies = carPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now());

        expiredPolicies.forEach(policy -> {
            policy.setState(PolicyState.EXPIRED);
            policy.setExpiryDate(LocalDate.now());
            carPolicyGateway.update(policy);
        });
    }




}
