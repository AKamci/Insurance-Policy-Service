package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.LicensePlateModel;
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
}
