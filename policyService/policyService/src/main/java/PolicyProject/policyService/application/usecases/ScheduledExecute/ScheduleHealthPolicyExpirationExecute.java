package PolicyProject.policyService.application.usecases.ScheduledExecute;


import PolicyProject.policyService.application.gateways.PolicyGateway.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleHealthPolicyExpirationExecute {
    HealthPolicyGateway healthPolicyGateway;

    ScheduleHealthPolicyExpirationExecute(HealthPolicyGateway healthPolicyGateway) {
        this.healthPolicyGateway = healthPolicyGateway;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void expirePolicies() {
        List<HealthPolicy> expiredPolicies = healthPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now());
        expiredPolicies.forEach(policy -> {
            policy.setState(PolicyState.EXPIRED);
            policy.setExpiryDate(LocalDate.now());
            healthPolicyGateway.update(policy);
        });
    }
}
