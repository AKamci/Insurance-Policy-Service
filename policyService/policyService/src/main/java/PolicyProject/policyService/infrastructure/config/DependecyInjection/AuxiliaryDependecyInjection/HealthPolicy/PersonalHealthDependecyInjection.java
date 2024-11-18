package PolicyProject.policyService.infrastructure.config.DependecyInjection.AuxiliaryDependecyInjection.HealthPolicy;

import PolicyProject.policyService.application.gateways.HealthPolicyWeightGateway;
import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.AuxiliaryService.HealthPolicy.PersonalHealthService;
import PolicyProject.policyService.application.service.StrategyFactory.HealthPolicyWeightStrategyFactory;
import PolicyProject.policyService.application.usecases.*;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy.ExecutePersonalHealth;
import PolicyProject.policyService.application.usecases.ExecuteHealthPolicyWeight;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PersonalHealthRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway.HealthPolicyWeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonalHealthDependecyInjection {

    @Bean
    PersonalHealthGateway personalHealthGateway(PersonalHealthRepository personalHealthRepository) {
        return new PersonalHealthRepositoryGateway(personalHealthRepository);
    }

    @Bean
    HealthPolicyWeightGateway healthPolicyWeightGateway(HealthPolicyWeightRepository healthPolicyWeightRepository) {
        return new HealthPolicyWeightRepositoryGateway(healthPolicyWeightRepository);
    }

    @Bean
    ExecutePersonalHealth executePersonalHealth(PersonalHealthGateway personalHealthGateway, ExecuteHealthPolicyWeight executeHealthPolicyWeight, ExecuteCustomer executeCustomer) {
        return new ExecutePersonalHealth(personalHealthGateway, executeHealthPolicyWeight, executeCustomer);
    }

    @Bean
    ExecuteHealthPolicyWeight executeHealthPolicyWeight(HealthPolicyWeightGateway healthPolicyWeightGateway, HealthPolicyWeightStrategyFactory strategyFactory) {
        return new ExecuteHealthPolicyWeight(healthPolicyWeightGateway, strategyFactory);
    }

    @Bean
    PersonalHealthService personalHealthService(ExecutePersonalHealth executePersonalHealth, ObjectValidation objectValidation) {
        return new PersonalHealthService(executePersonalHealth, objectValidation);
    }


}