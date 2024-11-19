package PolicyProject.policyService.interfaces.mappers.WeightsMapper;

import PolicyProject.policyService.domain.dto.request.WeightRequest.*;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper()

public interface HealthPolicyWeightMapper {

    HealthPolicyWeightMapper INSTANCE = Mappers.getMapper(HealthPolicyWeightMapper.class);

    WeightsModel CreateWeightRequestToWeightsModel(CreateWeightRequest createWeightRequest);
    WeightsModel UpdateWeightRequestToWeightsModel(UpdateWeightRequest updateWeightRequest);
    WeightsModel DeleteWeightRequestToWeightsModel(DeleteWeightRequest deleteWeightRequest);
    WeightsModel GetWeightRequestToWeightsModel(GetWeightRequest getWeightRequest);
    WeightsModel GetListWeightRequestToWeightsModel(GetListWeightRequest getListWeightRequest);
    List<WeightsModel> GetWeightRequestListToWeightsModelList(List<GetWeightRequest> getWeightRequestList);
    List<WeightsModel> UpdateWeightRequestListToWeightsModelList(List<UpdateWeightRequest> updateWeightRequestlist);



    CreateWeightResponse WeightsModelToCreateWeightResponse(WeightsModel weightsModel);
    UpdateWeightResponse WeightsModelToUpdateWeightResponse(WeightsModel weightsModel);
    DeleteWeightResponse WeightsModelToDeleteWeightResponse(WeightsModel weightsModel);
    GetWeightResponse WeightsModelToGetWeightResponse(WeightsModel weightsModel);
    List<GetWeightResponse> WeightsModelListToGetWeightResponse(List<WeightsModel> weightsModelList);
    List<UpdateWeightResponse> WeightsModelListToUpdateWeightResponseList(List<WeightsModel> weightsModelList);



    HealthPolicyWeight WeightsModelToWeightEntity (WeightsModel weightsModel);
    List<HealthPolicyWeight> WeightsModelListToWeightEntityList (List<WeightsModel> weightsModelList);

    WeightsModel WeightsEntityToWeightsModel (HealthPolicyWeight weights);
    List<WeightsModel> WeightsEntityListToWeightsModelList (List<HealthPolicyWeight> weightsList);

}
