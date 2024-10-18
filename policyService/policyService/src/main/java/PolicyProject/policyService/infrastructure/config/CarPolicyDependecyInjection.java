package PolicyProject.policyService.infrastructure.config;


import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.CarPolicyService;
import PolicyProject.policyService.application.usecases.ExecuteCarPolicy;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.infrastructure.gateways.CarPolicyRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CarPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.repository.CarPolicyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarPolicyDependecyInjection {

    @Bean
    ExecuteCarPolicy executeCarPolicy(CarPolicyGateway carPolicyGateway,
                                      ExecuteCustomer executeCustomer,
                                      CarPolicySpecificationBuild carPolicySpecificationBuild)
    {
        return new ExecuteCarPolicy(carPolicyGateway, executeCustomer, carPolicySpecificationBuild);
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
