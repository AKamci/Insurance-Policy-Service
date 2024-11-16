package PolicyProject.policyService.infrastructure.config.DependecyInjection;

import PolicyProject.policyService.application.gateways.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.gateways.HealthPolicyWeightGateway;
import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.HouseService;
import PolicyProject.policyService.application.service.Service.PersonalHealthService;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.application.usecases.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.application.usecases.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecutePersonalHealth;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.EarthQuakeWeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.HouseRepositoryGateway;
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
        return new PersonalHealthGateway(personalHealthRepository);
    }

    @Bean
    HealthPolicyWeightGateway earthQuakeWeightGateway(HealthPolicyWeightRepository healthPolicyWeightRepository) {
        return new HealthPolicyWeightRepository(healthPolicyWeightRepository);
    }

    @Bean
    ExecuteHouse executeHouse(HouseGateway houseGateway, ExecuteEarthQuakeWeight executeEarthQuakeWeight) {
        return new ExecuteHouse(houseGateway, executeEarthQuakeWeight);
    }

    @Bean
    ExecuteEarthQuakeWeight executeEarthQuakeWeight(EarthQuakeWeightGateway weightGateway, EarthQuakeWeightStrategyFactory strategyFactory) {
        return new ExecuteEarthQuakeWeight(weightGateway, strategyFactory);
    }

    @Bean
    PersonalHealthService personalHealthService(ExecutePersonalHealth executePersonalHealth, ObjectValidation objectValidation) {
        return new PersonalHealthService(executePersonalHealth, objectValidation);
    }


}
