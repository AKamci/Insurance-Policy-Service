package PolicyProject.policyService.infrastructure.config;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.infrastructure.gateways.CustomerRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDependecyInjection {


    @Bean
    ExecuteCustomer executeCustomer(CustomerGateway executeCustomer) {
        return new ExecuteCustomer(executeCustomer);
    }

    @Bean
    CustomerGateway CustomerGateway(CustomerRepository CustomerRepository)
    {
        return new CustomerRepositoryGateway(CustomerRepository);
    }

    @Bean
    CustomerService CustomerService(ExecuteCustomer executeCustomer, ObjectValidation ObjectValidation) {
        return new CustomerService(executeCustomer, ObjectValidation);
    }

}
