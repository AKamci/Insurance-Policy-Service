package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy;

import PolicyProject.policyService.application.gateways.AuxiliaryGateway.CarPolicy.LicensePlateGateway;
import PolicyProject.policyService.application.service.ModelFactory.LicensePlateModelFactory;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteCarPolicyWeight;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteLicensePlate {

    private final LicensePlateGateway licensePlateGateway;
    private final ExecuteCarPolicyWeight executeWeight;

    public LicensePlateModel ExecuteGetLicensePlateWithCustomer(LicensePlateModel licensePlateModel) {
        LicensePlate licensePlateEntity = Optional.ofNullable
                        (licensePlateGateway.getWCustomer(LicensePlateMapper.INSTANCE.LicensePlateModelToCustomerEntity(licensePlateModel)))
                .orElseThrow(() -> new EntityNotFoundException(licensePlateModel.id(), "Entity not found"));

        var licenseModelWCoverage = LicensePlateModelFactory.
                createLicensePlateModelWithCoverageCode
                        (LicensePlateMapper.INSTANCE.licensePlateEntityToLicensePlateModel(licensePlateEntity), licensePlateModel.coverageCode());
        return executeWeight.Get_ALicensePlateModel(LicensePlateModelFactory.createLicensePlateModelFromExisting(licenseModelWCoverage));
    }

    public LicensePlateModel ExecuteGetLicensePlate(String plate) {
        LicensePlate licensePlateEntity = Optional.ofNullable(licensePlateGateway.get(plate))
                .orElseThrow(() -> new EntityNotFoundException(Long.parseLong(plate), "Entity not found"));

        var licenseModel = LicensePlateMapper.INSTANCE.licensePlateEntityToLicensePlateModel(licensePlateEntity);

        return licenseModel;
    }

}
