package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.LicensePlateGateway;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteLicensePlate {

    private final LicensePlateGateway licensePlateGateway;
    private final ExecuteWeight executeWeight;

    public LicensePlateModel ExecuteGetLicensePlateWithCustomer(LicensePlateModel licensePlateModel) {
        LicensePlate entity = LicensePlateMapper.INSTANCE.LicensePlateModelToCustomerEntity(licensePlateModel);
        LicensePlate licensePlateEntity = Optional.ofNullable(licensePlateGateway.getWCustomer(entity))
                .orElseThrow(() -> new EntityNotFoundException(licensePlateModel.id(), "Entity not found"));

        var licenseModel = LicensePlateMapper.INSTANCE.licensePlateEntityToLicensePlateModel(licensePlateEntity);
        LicensePlateModel newPlate = new LicensePlateModel(
                null,
                licenseModel.plate(),
                licenseModel.car(),
                licenseModel.customer(),
                licensePlateModel.coverageCode(),
                licenseModel.policyStartDate(),
                licenseModel.policyEndDate(),
                0L
        );
        return executeWeight.Get_ALicensePlateModel(newPlate);
    }

    public LicensePlateModel ExecuteGetLicensePlate(String plate) {
        LicensePlate licensePlateEntity = Optional.ofNullable(licensePlateGateway.get(plate))
                .orElseThrow(() -> new EntityNotFoundException(Long.parseLong(plate), "Entity not found"));

        var licenseModel = LicensePlateMapper.INSTANCE.licensePlateEntityToLicensePlateModel(licensePlateEntity);

        return licenseModel;
    }

}
