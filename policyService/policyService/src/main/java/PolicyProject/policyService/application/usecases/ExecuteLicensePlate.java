package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.application.gateways.LicensePlateGateway;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CustomerSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.Calculator;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ExecuteLicensePlate {

    private final LicensePlateGateway licensePlateGateway;

    public LicensePlateModel ExecuteGetLicensePlate(LicensePlateModel licensePlateModel) {
        LicensePlate entity = LicensePlateMapper.INSTANCE.LicensePlateModelToCustomerEntity(licensePlateModel);
        LicensePlate licensePlateEntity = Optional.ofNullable(licensePlateGateway.get(entity))
                .orElseThrow(() -> new EntityNotFoundException(licensePlateModel.id(), "Entity not found"));

        var licenseModel = LicensePlateMapper.INSTANCE.licensePlateEntityToLicensePlateModel(licensePlateEntity);

        var PlateWCustomer = Calculator.Calculate(licenseModel,licensePlateModel);

        return PlateWCustomer;
    }
}
