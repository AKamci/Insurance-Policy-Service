package PolicyProject.policyService.infrastructure.config.DependecyInjection.WeightsDependecyIncjection;


import PolicyProject.policyService.application.gateways.WeightGateway;
import PolicyProject.policyService.application.service.StrategyFactory.WeightStrategyFactory;
import PolicyProject.policyService.application.usecases.ExecuteWeight;

import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway.WeightRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.WeightsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeightDependecyInjection {

    @Bean
    WeightGateway weightGateway(WeightsRepository weightsRepository)
    {
        return new WeightRepositoryGateway(weightsRepository);
    }

    @Bean
    ExecuteWeight executeWeight(WeightGateway weightGateway, WeightStrategyFactory weightStrategyFactory) {
        return new ExecuteWeight(weightGateway, weightStrategyFactory);
    }
}
