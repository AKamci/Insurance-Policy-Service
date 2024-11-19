package PolicyProject.policyService.infrastructure.config.DependecyInjection.PolicyDependecyInjection;

import PolicyProject.policyService.application.gateways.PolicyGateway.EarthQuakeGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.PolicyService.EarthQuakeService;
import PolicyProject.policyService.application.usecases.*;

import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteEarthQuakePolicy;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway.EarthQuakeRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.EarthQuakeSpecificationBuild;

import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.PoliciesRepository;
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
    EarthQuakeGateway earthQuakeGateway(EarthQuakeRepository earthQuakeRepository, PoliciesRepository policiesRepository)
    {
        return new EarthQuakeRepositoryGateway(earthQuakeRepository, policiesRepository);
    }

    @Bean
    EarthQuakeService earthQuakeService(ExecuteEarthQuakePolicy executeEarthQuakePolicy, ObjectValidation ObjectValidation) {
        return new EarthQuakeService(executeEarthQuakePolicy, ObjectValidation);
    }

}
