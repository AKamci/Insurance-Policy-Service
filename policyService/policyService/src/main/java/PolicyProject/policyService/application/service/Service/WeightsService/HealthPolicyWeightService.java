package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.IWeightService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteHealthPolicyWeight;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthPolicyWeightService implements IWeightService {


    private final ExecuteHealthPolicyWeight executeWeight;
    private final ObjectValidation objectValidation;

    @Override
    public CreateWeightResponse create(WeightsModel weightsModel) {

        // objectValidation.CustomerModelValidations(weightsModel);
        return WeightsMapper.INSTANCE.WeightsModelToCreateWeightResponse
                (executeWeight.executeCreate(weightsModel));
    }

    @Override
    public UpdateWeightResponse update(WeightsModel weightsModel) {
        // objectValidation.CustomerModelValidations(weightsModel);
        return WeightsMapper.INSTANCE.WeightsModelToUpdateWeightResponse
                (executeWeight.executeUpdate(weightsModel));
    }

    @Override
    public DeleteWeightResponse delete(WeightsModel weightsModel) {
        //objectValidation.CustomerModelValidations(weightsModel);
        return WeightsMapper.INSTANCE.WeightsModelToDeleteWeightResponse
                (executeWeight.executeDelete(weightsModel));
    }


    public List<GetWeightResponse> getList() {
        return WeightsMapper.INSTANCE.WeightsModelListToGetWeightResponse
                (executeWeight.executeGetList());
    }

    @Override
    public GetWeightResponse get(WeightsModel weightsModel) {
        //objectValidation.CustomerModelValidations(weightsModel);
        return WeightsMapper.INSTANCE.WeightsModelToGetWeightResponse
                (executeWeight.executeGet(weightsModel));
    }

    public List<UpdateWeightResponse> updateList(List<WeightsModel> weightsModelList) {
        // objectValidation.CustomerModelValidations(weightsModel);
        return WeightsMapper.INSTANCE.WeightsModelListToUpdateWeightResponseList
                (executeWeight.executeUpdateList(weightsModelList));
    }

}
