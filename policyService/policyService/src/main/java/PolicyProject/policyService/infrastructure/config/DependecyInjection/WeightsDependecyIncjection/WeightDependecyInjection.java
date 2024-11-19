package PolicyProject.policyService.infrastructure.config.DependecyInjection.WeightsDependecyIncjection;


import PolicyProject.policyService.application.gateways.WeightsGateway.CarPolicyWeightGateway;
import PolicyProject.policyService.application.service.StrategyFactory.CarPolicyWeightStrategyFactory;

import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteCarPolicyWeight;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway.CarPolicyWeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.WeightsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeightDependecyInjection {

    @Bean
    CarPolicyWeightGateway weightGateway(WeightsRepository weightsRepository)
    {
        return new CarPolicyWeightRepositoryGateway(weightsRepository);
    }

    @Bean
    ExecuteCarPolicyWeight executeWeight(CarPolicyWeightGateway weightGateway, CarPolicyWeightStrategyFactory carPolicyWeightStrategyFactory) {
        return new ExecuteCarPolicyWeight(weightGateway, carPolicyWeightStrategyFactory);
    }
}
