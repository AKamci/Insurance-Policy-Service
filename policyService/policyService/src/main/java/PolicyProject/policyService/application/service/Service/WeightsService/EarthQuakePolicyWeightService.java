package PolicyProject.policyService.application.service.Service.WeightsService;

import PolicyProject.policyService.application.service.IService.IWeightService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EarthQuakePolicyWeightService implements IWeightService {


    private final ExecuteEarthQuakeWeight executeWeight;
    private final ObjectValidation objectValidation;

    @Override
    public CreateWeightResponse create(WeightsModel weightsModel) {

        // objectValidation.CustomerModelValidations(weightsModel);
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToCreateWeightResponse
                (executeWeight.executeCreate(weightsModel));
    }

    @Override
    public UpdateWeightResponse update(WeightsModel weightsModel) {
        // objectValidation.CustomerModelValidations(weightsModel);
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToUpdateWeightResponse
                (executeWeight.executeUpdate(weightsModel));
    }

    @Override
    public DeleteWeightResponse delete(WeightsModel weightsModel) {
        //objectValidation.CustomerModelValidations(weightsModel);
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToDeleteWeightResponse
                (executeWeight.executeDelete(weightsModel));
    }


    public List<GetWeightResponse> getList() {
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelListToGetWeightResponse
                (executeWeight.executeGetList());
    }

    @Override
    public GetWeightResponse get(WeightsModel weightsModel) {
        //objectValidation.CustomerModelValidations(weightsModel);
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelToGetWeightResponse
                (executeWeight.executeGet(weightsModel));
    }

    public List<UpdateWeightResponse> updateList(List<WeightsModel> weightsModelList) {
        // objectValidation.CustomerModelValidations(weightsModel);
        return CarPolicyWeightsMapper.INSTANCE.WeightsModelListToUpdateWeightResponseList
                (executeWeight.executeUpdateList(weightsModelList));
    }

}
