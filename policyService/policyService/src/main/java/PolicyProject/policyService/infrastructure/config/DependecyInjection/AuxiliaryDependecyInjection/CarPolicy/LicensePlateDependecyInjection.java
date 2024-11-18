package PolicyProject.policyService.infrastructure.config.DependecyInjection.AuxiliaryDependecyInjection.CarPolicy;

import PolicyProject.policyService.application.gateways.LicensePlateGateway;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy.LicensePlateService;
import PolicyProject.policyService.application.usecases.ExecuteLicensePlate;
import PolicyProject.policyService.application.usecases.ExecuteWeight;
import PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.LicensePlateRepositoryGateway;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy.LicensePlateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LicensePlateDependecyInjection {

    @Bean
    LicensePlateGateway licensePlateGateway(LicensePlateRepository licensePlateRepository)
    {
        return new LicensePlateRepositoryGateway(licensePlateRepository);
    }

    @Bean
    ExecuteLicensePlate executeLicensePlate(LicensePlateGateway licensePlateGateway, ExecuteWeight executeWeight) {
        return new ExecuteLicensePlate(licensePlateGateway, executeWeight);
    }

    @Bean
    LicensePlateService licensePlateService(ExecuteLicensePlate executeLicensePlate, ObjectValidation objectValidation) {
        return new LicensePlateService(executeLicensePlate, objectValidation);
    }

}
