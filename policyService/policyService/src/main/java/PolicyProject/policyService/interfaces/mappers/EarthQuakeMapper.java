package PolicyProject.policyService.interfaces.mappers;


import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.*;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EarthQuakeMapper {

    EarthQuakeMapper INSTANCE = Mappers.getMapper(EarthQuakeMapper.class);



    EarthQuakeModel createEarthQuakeRequestToEarthQuakeModel(CreateEarthQuakeRequest createEarthQuakeRequest);
    EarthQuakeModel updateEarthQuakeRequestToEarthQuakeModel(UpdateEarthQuakeRequest updateEarthQuakeRequest);
    EarthQuakeModel setStateEarthQuakeRequestToEarthQuakeModel(SetStateEarthQuakeRequest setStateEarthQuakeRequest);
    EarthQuakeModel getEarthQuakeRequestToEarthQuakeModel(GetEarthQuakeRequest getEarthQuakeRequest);
    EarthQuakeModel deleteEarthQuakeRequestToEarthQuakeModel(DeleteEarthQuakeRequest deleteEarthQuakeRequest);
    EarthQuakeModel getEarthQuakeRequestListToEarthQuakeModelList(GetListEarthQuakeRequest getListEarthQuakeRequest);

    CreateEarthQuakeResponse earthQuakeModelToCreateEarthQuakeResponse(EarthQuakeModel earthQuakeModel);
    UpdateEarthQuakeResponse earthQuakeModelToUpdateEarthQuakeResponse(EarthQuakeModel earthQuakeModel);
    SetStateEarthQuakeResponse earthQuakeModelToSetStateEarthQuakeResponse(EarthQuakeModel earthQuakeModel);
    GetEarthQuakeResponse earthQuakeModelToGetEarthQuakeResponse(EarthQuakeModel earthQuakeModel);
    List<GetEarthQuakeResponse> earthQuakeModelListToGetEarthQuakeResponseList(List<EarthQuakeModel> earthQuakeModelList);
    DeleteEarthQuakeResponse earthQuakeModelToDeleteEarthQuakeResponse(EarthQuakeModel earthQuakeModel);

    @Mapping(source = "policyId", target = "id")
    @Mapping(source = "number", target = "house.number")
    @Mapping(source = "apartmentNumber", target = "house.building.apartmentNumber")
    @Mapping(source = "city", target = "house.building.address.city")
    @Mapping(source = "district", target = "house.building.address.district")
    @Mapping(source = "neighborhood", target = "house.building.address.neighborhood")
    @Mapping(source = "coverageCode", target = "coverage", qualifiedByName = "coverageFromInteger")
    EarthquakePolicy earthQuakeModelToEarthQuakePolicyEntity(EarthQuakeModel earthQuakeModel);

    @Mapping(source = "policyId", target = "id")
    @Mapping(source = "coverage", target = "coverage", qualifiedByName = "coverageFromInteger")
    List<EarthQuakeModel> earthQuakePolicyEntityListToEarthQuakeModelList(List<EarthquakePolicy> earthquakePolicyList);


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

    @Mapping(source = "id", target = "policyId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.tckn", target = "tckn")
    @Mapping(source = "coverage", target = "coverage")
    EarthQuakeModel earthQuakePolicyEntityToEarthQuakeModel(EarthquakePolicy earthquakePolicy);

    @Named("coverageToInteger")
    default Integer mapCoverageToInteger(Coverage coverage) {
        if (coverage == null || coverage.getCoverageType() == null) {
            return null;
        }
        return coverage.getCoverageType().getCode();
    }
}
