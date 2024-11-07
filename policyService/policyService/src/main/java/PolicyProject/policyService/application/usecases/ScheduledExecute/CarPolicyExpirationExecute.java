package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarPolicyExpirationExecute {

    CarPolicyGateway carPolicyGateway;

    CarPolicyExpirationExecute(CarPolicyGateway carPolicyGateway) {
        this.carPolicyGateway = carPolicyGateway;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void expirePolicies() {
        List<CarPolicy> expiredPolicies = carPolicyGateway.findByStateAndExpiryDateBefore(CarPolicyState.CREATED, LocalDate.now());

        expiredPolicies.forEach(policy -> {
            policy.setState(CarPolicyState.EXPIRED);
            policy.setExpiryDate(LocalDate.now());
            carPolicyGateway.update(policy);
        });
    }




}
