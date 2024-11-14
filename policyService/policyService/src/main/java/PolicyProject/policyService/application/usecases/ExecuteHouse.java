package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.application.gateways.LicensePlateGateway;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.interfaces.mappers.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteHouse {

    private final HouseGateway houseGateway;
    private final ExecuteEarthQuakeWeight executeWeight;

    public HouseModel ExecuteGetLicensePlateWithCustomer(HouseModel houseModel) {
        House entity = HouseMapper.INSTANCE.HouseModelToHouseEntity(houseModel);
        House houseEntity = Optional.ofNullable(houseGateway.getWCustomer(entity))
                .orElseThrow(() -> new EntityNotFoundException(houseModel.id(), "Entity not found"));

        var newModel = HouseMapper.INSTANCE.houseEntityToHouseModel(houseEntity);

        return executeWeight.Get_AHouseModel(newModel);
    }

    public HouseModel ExecuteGetHouse(String plate) {
        House house = Optional.ofNullable(houseGateway.get(plate))
                .orElseThrow(() -> new EntityNotFoundException(Long.parseLong(plate), "Entity not found"));

        var houseModel = HouseMapper.INSTANCE.houseEntityToHouseModel(house);

        return houseModel;
    }


}
