package PolicyProject.policyService.infrastructure.config.DependecyInjection;

import PolicyProject.policyService.application.gateways.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.gateways.HealthPolicyWeightGateway;
import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.HouseService;
import PolicyProject.policyService.application.service.Service.PersonalHealthService;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.application.service.StrategyFactory.HealthPolicyWeightStrategyFactory;
import PolicyProject.policyService.application.usecases.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.application.usecases.ExecuteHealthPolicyWeight;
import PolicyProject.policyService.application.usecases.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecutePersonalHealth;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.EarthQuakeWeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.HealthPolicyWeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.HouseRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PersonalHealthRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.HouseRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
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
    ExecutePersonalHealth executePersonalHealth(PersonalHealthGateway personalHealthGateway, ExecuteHealthPolicyWeight executeHealthPolicyWeight) {
        return new ExecutePersonalHealth(personalHealthGateway, executeHealthPolicyWeight);
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
