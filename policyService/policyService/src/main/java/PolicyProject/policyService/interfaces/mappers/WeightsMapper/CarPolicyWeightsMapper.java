package PolicyProject.policyService.interfaces.mappers.WeightsMapper;


import PolicyProject.policyService.domain.dto.request.WeightRequest.*;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper()
public interface CarPolicyWeightsMapper {
    CarPolicyWeightsMapper INSTANCE = Mappers.getMapper(CarPolicyWeightsMapper.class);

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



    Weights WeightsModelToWeightEntity (WeightsModel weightsModel);
    List<Weights> WeightsModelListToWeightEntityList (List<WeightsModel> weightsModelList);

    WeightsModel WeightsEntityToWeightsModel (Weights weights);
    List<WeightsModel> WeightsEntityListToWeightsModelList (List<Weights> weightsList);


}
