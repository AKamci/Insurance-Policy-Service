package PolicyProject.policyService.infrastructure.config.DependecyInjection.AuxiliaryDependecyInjection.EarthquakePolicy;

import PolicyProject.policyService.application.gateways.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy.HouseService;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.application.usecases.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.application.usecases.ExecuteHouse;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.HouseRepositoryGateway;

import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway.EarthQuakeWeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.HouseRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HouseDependecyInjection {

    @Bean
    HouseGateway houseGateway(HouseRepository houseRepository) {
        return new HouseRepositoryGateway(houseRepository);
    }

    @Bean
    EarthQuakeWeightGateway earthQuakeWeightGateway(EarthQuakeWeightsRepository weightsRepository) {
        return new EarthQuakeWeightRepositoryGateway(weightsRepository);
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
    HouseService houseService(ExecuteHouse executeHouse, ObjectValidation objectValidation) {
        return new HouseService(executeHouse, objectValidation);
    }
}