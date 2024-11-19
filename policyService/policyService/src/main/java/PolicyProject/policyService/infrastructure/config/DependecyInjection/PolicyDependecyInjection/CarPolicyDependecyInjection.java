package PolicyProject.policyService.infrastructure.config.DependecyInjection.PolicyDependecyInjection;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.PolicyService.CarPolicyService;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteCarPolicy;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway.CarPolicyRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CarPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.CarPolicyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarPolicyDependecyInjection {

    @Bean
    ExecuteCarPolicy executeCarPolicy(CarPolicyGateway carPolicyGateway,
                                      ExecuteCustomer executeCustomer,
                                      ExecuteLicensePlate executeLicensePlate,
                                      CarPolicySpecificationBuild carPolicySpecificationBuild)
    {
        return new ExecuteCarPolicy(carPolicyGateway, executeCustomer, executeLicensePlate, carPolicySpecificationBuild);
    }

    @Bean
    CarPolicyGateway carPolicyGateway(CarPolicyRepository carPolicyRepository)
    {
        return new CarPolicyRepositoryGateway(carPolicyRepository);
    }

    @Bean
    ObjectValidation ObjectValidation()
    {
        return new ObjectValidation();
    }

    @Bean
    CarPolicyService carPolicyService(ExecuteCarPolicy executeCarPolicy, ObjectValidation ObjectValidation) {
        return new CarPolicyService(executeCarPolicy, ObjectValidation);
    }


}
