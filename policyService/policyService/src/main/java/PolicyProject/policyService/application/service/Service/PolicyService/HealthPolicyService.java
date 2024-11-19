package PolicyProject.policyService.application.service.Service.PolicyService;

import PolicyProject.policyService.application.service.IService.IHealthPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteHealthPolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.*;
import PolicyProject.policyService.domain.model.PolicyModel.HealthPolicyModel;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.HealthPolicyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HealthPolicyService implements IHealthPolicyService {

    private final ExecuteHealthPolicy executeHealthPolicy;
    private final ObjectValidation objectValidation;


    @Override
    public List<GetHealthPolicyResponse> getList(HealthPolicyModel healthPolicyModel) {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelListToGetHealthPolicyResponseList
                (executeHealthPolicy.executeGetList(healthPolicyModel));
    }

    @Override
    public GetHealthPolicyResponse get(HealthPolicyModel healthPolicyModel) {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelToGetHealthPolicyResponse
                (executeHealthPolicy.executeGet(healthPolicyModel));
    }

    @Override
    public CreateHealthPolicyResponse create(HealthPolicyModel healthPolicyModel) {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelToCreateHealthPolicyResponse
                (executeHealthPolicy.executeCreate(healthPolicyModel));
    }

    @Override
    public UpdateHealthPolicyResponse update(HealthPolicyModel healthPolicyModel) {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelToUpdateHealthPolicyResponse
                (executeHealthPolicy.executeUpdate(healthPolicyModel));
    }

    @Override
    public DeleteHealthPolicyResponse delete(HealthPolicyModel healthPolicyModel) {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelToDeleteHealthPolicyResponse
                (executeHealthPolicy.executeUpdate(healthPolicyModel));
    }


    public SetStateHealthPolicyResponse accept (HealthPolicyModel healthPolicyModel)
    {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelToSetStateHealthPolicyResponse
                (executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.ACTIVATE));
    }
    public SetStateHealthPolicyResponse reject (HealthPolicyModel healthPolicyModel)
    {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        return HealthPolicyMapper.INSTANCE.healthPolicyModelToSetStateHealthPolicyResponse
                (executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.CANCEL));
    }

    public int getTotalRecord() {
        return executeHealthPolicy.executeGetTotalRecord();
    }

}
