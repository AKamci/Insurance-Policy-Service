package PolicyProject.policyService.application.service.Service.WeightsService;
import PolicyProject.policyService.application.service.IService.IWeightService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteCarPolicyWeight;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarPolicyWeightService implements IWeightService {

    private final ExecuteCarPolicyWeight executeWeight;
    private final ObjectValidation objectValidation;

    @Override
    public CreateWeightResponse create(WeightsModel weightsModel) {
        objectValidation.validateModel(weightsModel, "weightsModel");
    return CarPolicyWeightsMapper.INSTANCE.WeightsModelToCreateWeightResponse
                (executeWeight.executeCreate(weightsModel));
    }

    @Override
    public UpdateWeightResponse update(WeightsModel weightsModel) {
        objectValidation.validateModel(weightsModel, "weightsModel");
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToUpdateWeightResponse
                (executeWeight.executeUpdate(weightsModel));
    }

    @Override
    public DeleteWeightResponse delete(WeightsModel weightsModel) {
        objectValidation.validateModel(weightsModel, "weightsModel");
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToDeleteWeightResponse
                (executeWeight.executeDelete(weightsModel));
    }


    public List<GetWeightResponse> getList() {
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelListToGetWeightResponse
                (executeWeight.executeGetList());
    }

    @Override
    public GetWeightResponse get(WeightsModel weightsModel) {
        objectValidation.validateModel(weightsModel, "weightsModel");
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToGetWeightResponse
                (executeWeight.executeGet(weightsModel));
    }

    public List<UpdateWeightResponse> updateList(List<WeightsModel> weightsModelList) {
        objectValidation.validateModelList(weightsModelList, "weightsModelList");
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelListToUpdateWeightResponseList
                (executeWeight.executeUpdateList(weightsModelList));
    }


}
