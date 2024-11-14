package PolicyProject.policyService.infrastructure.config.DependecyInjection;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.application.gateways.EarthQuakeGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.CarPolicyService;
import PolicyProject.policyService.application.service.Service.EarthQuakeService;
import PolicyProject.policyService.application.usecases.*;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.CarPolicyRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.EarthQuakeRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CarPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.EarthQuakeSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.repository.CarPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.EarthQuakeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EarthQuakeDependecyInjection {



    @Bean
    ExecuteEarthQuakePolicy executePolicy(EarthQuakeGateway earthQuakeGateway,
                                          ExecuteCustomer executeCustomer,
                                          EarthQuakeSpecificationBuild earthQuakeSpecificationBuild,
                                          ExecuteHouse executeHouse)
    {
        return new ExecuteEarthQuakePolicy(earthQuakeGateway, executeCustomer, earthQuakeSpecificationBuild, executeHouse);
    }

    @Bean
    EarthQuakeGateway earthQuakeGateway(EarthQuakeRepository earthQuakeRepository)
    {
        return new EarthQuakeRepositoryGateway(earthQuakeRepository);
    }

    @Bean
    EarthQuakeService earthQuakeService(ExecuteEarthQuakePolicy executeEarthQuakePolicy, ObjectValidation ObjectValidation) {
        return new EarthQuakeService(executeEarthQuakePolicy, ObjectValidation);
    }

}
