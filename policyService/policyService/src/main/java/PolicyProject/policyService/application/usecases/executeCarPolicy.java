package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.carPolicyGateway;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Calculator;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.Mapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class executeCarPolicy {

    private final carPolicyGateway carPolicyGateway;

    public carPolicyModel executeUpdate(carPolicyModel carPolicyModel)
    {
        carPolicy EnityObject = carPolicyGateway.update(CarPolicyMapper.INSTANCE.carPolicyModelToCarEntity(carPolicyModel));
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(Optional.ofNullable(EnityObject));
    }

    public carPolicyModel executeCreate(carPolicyModel carPolicyModel)
    {
        var PolicyAmount = Calculator.Calculate(carPolicyModel);
        carPolicy EnityObject = carPolicyGateway.create(CarPolicyMapper.INSTANCE.carPolicyModelToCarEntity(carPolicyModel), PolicyAmount);
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(Optional.ofNullable(EnityObject));
    }

    public carPolicyModel executeGet(carPolicyModel carPolicyModel)
    {
        carPolicy EnityObject = carPolicyGateway.get(CarPolicyMapper.INSTANCE.carPolicyModelToCarEntity(carPolicyModel));
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(Optional.ofNullable(EnityObject));

    }

    public carPolicyModel executeDelete(carPolicyModel carPolicyModel)
    {
        carPolicy EnityObject = carPolicyGateway.delete(CarPolicyMapper.INSTANCE.carPolicyModelToCarEntity(carPolicyModel));
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(Optional.ofNullable(EnityObject));
    }

    public carPolicyModel executeGetList(carPolicyModel carPolicyModel)
    {
        carPolicy EnityObject = carPolicyGateway.getList(CarPolicyMapper.INSTANCE.carPolicyModelToCarEntity(carPolicyModel));
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(Optional.ofNullable(EnityObject));
    }




}
