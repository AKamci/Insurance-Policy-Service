package PolicyProject.policyService.infrastructure.config;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.application.gateways.carPolicyGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.application.service.Service.carPolicyService;
import PolicyProject.policyService.application.usecases.executeCarPolicy;
import PolicyProject.policyService.application.usecases.executeCustomer;
import PolicyProject.policyService.infrastructure.gateways.CustomerRepositoryGateway;
import PolicyProject.policyService.infrastructure.gateways.DatabaseSeed;
import PolicyProject.policyService.infrastructure.gateways.carPolicyRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDependecyInjection {


    @Bean
    executeCustomer executeCustomer(CustomerGateway executeCustomer) {
        return new executeCustomer(executeCustomer);
    }

    @Bean
    CustomerGateway CustomerGateway(CustomerRepository CustomerRepository)
    {
        return new CustomerRepositoryGateway(CustomerRepository);
    }

    @Bean
    CustomerService CustomerService(executeCustomer executeCustomer, ObjectValidation ObjectValidation) {
        return new CustomerService(executeCustomer, ObjectValidation);
    }

}
