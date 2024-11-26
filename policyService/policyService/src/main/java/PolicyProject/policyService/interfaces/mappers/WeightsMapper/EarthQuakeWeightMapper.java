package PolicyProject.policyService.interfaces.mappers.WeightsMapper;

import PolicyProject.policyService.domain.dto.request.WeightRequest.*;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper()
public interface EarthQuakeWeightMapper {
    EarthQuakeWeightMapper INSTANCE = Mappers.getMapper(EarthQuakeWeightMapper.class);

    WeightsModel CreateWeightRequestToWeightsModel(CreateWeightRequest createWeightRequest);
    WeightsModel UpdateWeightRequestToWeightsModel(UpdateWeightRequest updateWeightRequest);
    WeightsModel DeleteWeightRequestToWeightsModel(DeleteWeightRequest deleteWeightRequest);
    WeightsModel GetWeightRequestToWeightsModel(GetWeightRequest getWeightRequest);
    List<WeightsModel> UpdateWeightRequestListToWeightsModelList(List<UpdateWeightRequest> updateWeightRequestlist);



    CreateWeightResponse WeightsModelToCreateWeightResponse(WeightsModel weightsModel);
    UpdateWeightResponse WeightsModelToUpdateWeightResponse(WeightsModel weightsModel);
    DeleteWeightResponse WeightsModelToDeleteWeightResponse(WeightsModel weightsModel);
    GetWeightResponse WeightsModelToGetWeightResponse(WeightsModel weightsModel);
    List<GetWeightResponse> WeightsModelListToGetWeightResponse(List<WeightsModel> weightsModelList);
    List<UpdateWeightResponse> WeightsModelListToUpdateWeightResponseList(List<WeightsModel> weightsModelList);



    EarthQaukeWeights WeightsModelToWeightEntity (WeightsModel weightsModel);
    List<EarthQaukeWeights> WeightsModelListToWeightEntityList (List<WeightsModel> weightsModelList);

    WeightsModel WeightsEntityToWeightsModel (EarthQaukeWeights weights);
    List<WeightsModel> WeightsEntityListToWeightsModelList (List<EarthQaukeWeights> weightsList);


}