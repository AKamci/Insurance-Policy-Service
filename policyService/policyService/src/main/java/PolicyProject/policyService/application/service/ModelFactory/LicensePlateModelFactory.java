package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LicensePlateModelFactory {

    public static LicensePlateModel createLicensePlateModelWithAmount(
            LicensePlateModel licensePlateModel,
            Long amount
    ) {
        return new LicensePlateModel(
                null,
                licensePlateModel.plate(),
                licensePlateModel.car(),
                licensePlateModel.customer(),
                licensePlateModel.coverageCode(),
                licensePlateModel.policyStartDate(),
                licensePlateModel.policyEndDate(),
                amount
        );
    }

    public static LicensePlateModel createLicensePlateModelFromExisting(
            LicensePlateModel licenseModel
    ) {
        return new LicensePlateModel(
                null,
                licenseModel.plate(),
                licenseModel.car(),
                licenseModel.customer(),
                licenseModel.coverageCode(),
                licenseModel.policyStartDate(),
                licenseModel.policyEndDate(),
                0L
        );
    }

    public static LicensePlateModel createLicensePlateModelWithCoverageCode(
            LicensePlateModel licensePlateModel,
            Integer coverageCode
    ) {
        return new LicensePlateModel(
                null,
                licensePlateModel.plate(),
                licensePlateModel.car(),
                licensePlateModel.customer(),
                coverageCode,
                licensePlateModel.policyStartDate(),
                licensePlateModel.policyEndDate(),
                licensePlateModel.amount()
        );
    }












}
