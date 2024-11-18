package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.interfaces.mappers.HouseMapper;
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

        return executeWeight.Get_AHouseModel(newModel);
    }

    public HouseModel ExecuteGetHouse(HouseModel houseModel) {
        House entity = HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel);
        House houseEntity = Optional.ofNullable(houseGateway.get(entity))
                .orElseThrow(() -> new EntityNotFoundException(houseModel.id(), "Entity not found"));

        var newHouseModel = HouseMapper.INSTANCE.houseEntityToHouseModel(houseEntity);

        return newHouseModel;
    }


}
