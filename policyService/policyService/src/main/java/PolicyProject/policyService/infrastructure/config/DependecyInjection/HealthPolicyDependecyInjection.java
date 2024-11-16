package PolicyProject.policyService.infrastructure.config.DependecyInjection;

import PolicyProject.policyService.application.gateways.EarthQuakeGateway;
import PolicyProject.policyService.application.gateways.HealthPolicyGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.EarthQuakeService;
import PolicyProject.policyService.application.service.Service.HealthPolicyService;
import PolicyProject.policyService.application.usecases.*;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.EarthQuakeRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.HealthPolicyRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.EarthQuakeSpecificationBuild;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.HealthPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.HealthPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.PoliciesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthPolicyDependecyInjection {

    @Bean
    ExecuteHealthPolicy executeHealthPolicy(HealthPolicyGateway healthPolicyGateway,
                                      ExecuteCustomer executeCustomer,
                                      HealthPolicySpecificationBuild healthPolicySpecificationBuild,
                                      ExecutePersonalHealth executePersonalHealth)
    {
        return new ExecuteHealthPolicy(healthPolicyGateway, executeCustomer, healthPolicySpecificationBuild, executePersonalHealth);
    }

    @Bean
    HealthPolicyGateway healthPolicyGateway(HealthPolicyRepository healthPolicyRepository, PoliciesRepository policiesRepository)
    {
        return new HealthPolicyRepositoryGateway(healthPolicyRepository, policiesRepository);
    }

    @Bean
    HealthPolicyService healthPolicyService(ExecuteHealthPolicy executeHealthPolicy, ObjectValidation ObjectValidation) {
        return new HealthPolicyService(executeHealthPolicy, ObjectValidation);
    }

}
