package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.WeightsModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.ConstantStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.EarthQuakeConstantStrategy;
import PolicyProject.policyService.interfaces.mappers.EarthQuakeWeightMapper;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteEarthQuakeWeight {

    private final EarthQuakeWeightGateway weightGateway;
    private final EarthQuakeWeightStrategyFactory strategyFactory;

    public HouseModel Get_AHouseModel(HouseModel houseModel) {
        BigDecimal Amount = calculatePolicyPrice(houseModel);
        HouseModel newModel = new HouseModel(
                houseModel.id(),
                houseModel.number(),
                houseModel.squareMeters(),
                houseModel.customer(),
                houseModel.building(),
                houseModel.tckn(),
                Amount.longValue()
        );
        return newModel;
    }

    private BigDecimal calculatePolicyPrice(HouseModel houseModel) {
        BigDecimal total = BigDecimal.ZERO;
        List<EarthQaukeWeights> parameters = weightGateway.list();

        for (EarthQaukeWeights parameter : parameters) {
            IWeightStrategy strategy = strategyFactory.getStrategy(parameter.getType());
            BigDecimal valueToCheck = strategy.getValue(houseModel);

            if (strategy instanceof EarthQuakeConstantStrategy) {
                total = total.add(strategy.calculate(houseModel, parameter));
            } else if (parameter.getMinValue() != null && parameter.getMaxValue() != null) {
                if (valueToCheck != null && parameter.getMinValue().compareTo(valueToCheck) <= 0
                        && parameter.getMaxValue().compareTo(valueToCheck) >= 0) {
                    total = total.add(strategy.calculate(houseModel, parameter));
                    System.out.println("Parametre :" + parameter);
                    System.out.println("Total :" + total);
                    System.out.println("Strategy :" + strategy);
                    System.out.println();
                }
            } else {
                System.out.println("Doğru Aralığa girmedi");
                System.out.println(strategy);
                total = total.add(strategy.calculate(houseModel, parameter));
            }
        }

        return total;
    }

    public WeightsModel executeUpdate(WeightsModel weightsModel)
    {
        Optional<EarthQaukeWeights> optionalEntity = Optional.ofNullable
                (weightGateway.update(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel)));
        EarthQaukeWeights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeCreate(WeightsModel weightsModel)
    {
        EarthQaukeWeights EnityObject = weightGateway.create(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel));

        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(EnityObject);
    }

    public WeightsModel executeGet(WeightsModel weightsModel)
    {
        Optional<EarthQaukeWeights> optionalEntity = Optional.ofNullable
                (weightGateway.get(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
        EarthQaukeWeights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeDelete(WeightsModel weightsModel)
    {
        System.out.println(weightsModel);
        Optional<EarthQaukeWeights> optionalEntity = Optional.ofNullable
                (weightGateway.delete(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
        EarthQaukeWeights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public List<WeightsModel> executeGetList()
    {
        Optional<List<EarthQaukeWeights>> optionalEntity = Optional.ofNullable
                (weightGateway.listFilter());
        List<EarthQaukeWeights> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }

    public List<WeightsModel> executeUpdateList(List<WeightsModel> weightsModels) {
        Optional<List<EarthQaukeWeights>> optionalEntity = Optional.ofNullable
                (weightGateway.updateOrSave(EarthQuakeWeightMapper.INSTANCE.WeightsModelListToWeightEntityList(weightsModels)));
        List<EarthQaukeWeights> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }

}
