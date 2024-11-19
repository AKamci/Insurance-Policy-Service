package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.PolicyGateway.EarthQuakeGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleEarthQuakeExpirationExecute {
    EarthQuakeGateway earthQuakeGateway;

    ScheduleEarthQuakeExpirationExecute(EarthQuakeGateway earthQuakeGateway) {
        this.earthQuakeGateway = earthQuakeGateway;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void expirePolicies() {
        List<EarthquakePolicy> expiredPolicies = earthQuakeGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now());
        expiredPolicies.forEach(policy -> {
            policy.setState(PolicyState.EXPIRED);
            policy.setExpiryDate(LocalDate.now());
            earthQuakeGateway.update(policy);
        });
    }

}
