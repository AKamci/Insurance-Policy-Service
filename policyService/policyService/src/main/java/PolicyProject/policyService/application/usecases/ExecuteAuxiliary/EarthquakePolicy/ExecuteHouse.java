package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy;

import PolicyProject.policyService.application.gateways.AuxiliaryGateway.EarthquakePolicy.HouseGateway;
import PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;

import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteHouse {

    private final HouseGateway houseGateway;
    private final ExecuteEarthQuakeWeight executeWeight;

    public HouseModel ExecuteGetWithCustomer(HouseModel houseModel) {
        House entity = HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel);
        House houseEntity = Optional.ofNullable(houseGateway.getWCustomer(entity))
                .orElseThrow(() -> new EntityNotFoundException(houseModel.id(), "Entity not found"));

        var newModel = HouseMapper.INSTANCE.houseEntityToHouseModel(houseEntity);
        var modelWCode = HouseModelFactory.createNewHouseModelFromExistingWithCoverageCode(newModel, houseModel.coverageCode());
        return executeWeight.Get_AHouseModel(modelWCode);
    }

    public HouseModel ExecuteGetHouse(HouseModel houseModel) {
        House entity = HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel);
        House houseEntity = Optional.ofNullable(houseGateway.get(entity))
                .orElseThrow(() -> new EntityNotFoundException(houseModel.id(), "Entity not found"));

        var newHouseModel = HouseMapper.INSTANCE.houseEntityToHouseModel(houseEntity);

        return newHouseModel;
    }


}
