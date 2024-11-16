package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.*;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.*;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.domain.model.HealthPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface HealthPolicyMapper {

    HealthPolicyMapper INSTANCE = Mappers.getMapper(HealthPolicyMapper.class);



    HealthPolicyModel createHealthPolicyRequestToHealthPolicyModel(CreateHealthPolicyRequest createHealthPolicyRequest);
    HealthPolicyModel updateHealthPolicyRequestToHealthPolicyModel(UpdateHealthPolicyRequest updateHealthPolicyRequest);
    HealthPolicyModel setStateHealthPolicyRequestToHealthPolicyModel(SetStateHealthPolicyRequest setStateHealthPolicyRequest);
    HealthPolicyModel getHealthPolicyRequestToHealthPolicyModel(GetHealthPolicyRequest getHealthPolicyRequest);
    HealthPolicyModel deleteHealthPolicyRequestToHealthPolicyModel(DeleteHealthPolicyRequest deleteHealthPolicyRequest);
    HealthPolicyModel getHealthPolicyRequestListToHealthPolicyModelList(GetListHealthPolicyRequest getListHealthPolicyRequest);

    CreateHealthPolicyResponse healthPolicyModelToCreateHealthPolicyResponse(HealthPolicyModel healthPolicyModel);
    UpdateHealthPolicyResponse healthPolicyModelToUpdateHealthPolicyResponse(HealthPolicyModel healthPolicyModel);
    SetStateHealthPolicyResponse healthPolicyModelToSetStateHealthPolicyResponse(HealthPolicyModel healthPolicyModel);
    GetHealthPolicyResponse healthPolicyModelToGetHealthPolicyResponse(HealthPolicyModel healthPolicyModel);
    List<GetHealthPolicyResponse> healthPolicyModelListToGetHealthPolicyResponseList(List<HealthPolicyModel> healthPolicyModelList);
    DeleteHealthPolicyResponse healthPolicyModelToDeleteHealthPolicyResponse(HealthPolicyModel healthPolicyModel);

    @Mapping(source = "policyId", target = "id")
    @Mapping(source = "coverageCode", target = "coverage", qualifiedByName = "coverageFromInteger")
    HealthPolicy healthPolicyModelToHealthPolicyEntity(HealthPolicyModel healthPolicyModel);

    @Mapping(source = "policyId", target = "id")
    @Mapping(source = "coverage", target = "coverage", qualifiedByName = "coverageFromInteger")
    List<HealthPolicyModel> healthPolicyEntityListToHealthPolicyModelList(List<HealthPolicy> healthPolicyList);

    @Mapping(source = "id", target = "policyId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.tckn", target = "tckn")
    @Mapping(source = "coverage", target = "coverage")
    HealthPolicyModel healthPolicyEntityToHealthPolicyModel(HealthPolicy healthPolicy);





    @Named("coverageFromInteger")
    default Coverage mapIntegerToCoverage(Integer coverage) {
        if (coverage == null) {
            return null;
        }
        Coverage coverageEntity = findCoverageById(coverage);
        return coverageEntity;
    }

    default Coverage findCoverageById(Integer coverageId) {
        Coverage coverage = new Coverage();
        coverage.setId(Long.valueOf(coverageId));
        return coverage;
    }

    @Named("coverageToInteger")
    default Integer mapCoverageToInteger(Coverage coverage) {
        if (coverage == null || coverage.getCoverageType() == null) {
            return null;
        }
        return coverage.getCoverageType().getCode();
    }

}
