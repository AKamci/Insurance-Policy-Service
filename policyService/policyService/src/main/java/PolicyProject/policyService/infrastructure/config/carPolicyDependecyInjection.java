package PolicyProject.policyService.infrastructure.config;


import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.application.gateways.carPolicyGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.carPolicyService;
import PolicyProject.policyService.application.usecases.executeCarPolicy;
import PolicyProject.policyService.application.usecases.executeCustomer;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.gateways.carPolicyRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class carPolicyDependecyInjection {

    @Bean
    executeCarPolicy executeCarPolicy(carPolicyGateway carPolicyGateway, executeCustomer executeCustomer) {
        return new executeCarPolicy(carPolicyGateway, executeCustomer);
    }

    @Bean
    carPolicyGateway carPolicyGateway(carPolicyRepository carPolicyRepository)
    {
        return new carPolicyRepositoryGateway(carPolicyRepository);
    }

    @Bean
    ObjectValidation ObjectValidation()
    {
        return new ObjectValidation();
    }

    @Bean
    carPolicyService carPolicyService(executeCarPolicy executeCarPolicy, ObjectValidation ObjectValidation) {
        return new carPolicyService(executeCarPolicy, ObjectValidation);
    }


}
