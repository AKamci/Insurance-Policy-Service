package PolicyProject.policyService.application.usecases.ExecuteWeights;

import PolicyProject.policyService.application.gateways.WeightsGateway.EarthQuakeWeightGateway;
import PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory;
import PolicyProject.policyService.application.service.StrategyFactory.EarthQuakeWeightStrategyFactory;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.EarthQuakeConstantStrategy;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.EarthQuakeWeightMapper;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteEarthQuakeWeight {

    private final EarthQuakeWeightGateway weightGateway;
    private final EarthQuakeWeightStrategyFactory strategyFactory;

    public HouseModel Get_AHouseModel(HouseModel houseModel) {
        BigDecimal amount = calculatePolicyPrice(houseModel);
        return HouseModelFactory.createNewHouseModelFromExisting(houseModel, amount.longValue());
    }

    private BigDecimal calculatePolicyPrice(HouseModel houseModel) {
        BigDecimal total = BigDecimal.ZERO;
        List<EarthQaukeWeights> parameters = weightGateway.list();

        for (EarthQaukeWeights parameter : parameters) {
            IWeightStrategy strategy = strategyFactory.getStrategy(parameter.getType());
            BigDecimal valueToCheck = strategy.getValue(houseModel);

            if (strategy instanceof EarthQuakeConstantStrategy) {
                System.out.println("strategy instanceof EarthQuakeConstantStrategy");
                System.out.println("SABİTELER YÜKLENİYOR");
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

    public WeightsModel executeUpdate(WeightsModel weightsModel) {
        EarthQaukeWeights weightsEntity = Optional.ofNullable(weightGateway.update(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel)))
                .orElseThrow(() -> new EntityNotFoundException(weightsModel.id(), "Entity not found during update"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeCreate(WeightsModel weightsModel) {
        EarthQaukeWeights entityObject = weightGateway.create(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(entityObject);
    }

    public WeightsModel executeGet(WeightsModel weightsModel) {
        EarthQaukeWeights weightsEntity = Optional.ofNullable(weightGateway.get(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()))
                .orElseThrow(() -> new EntityNotFoundException(weightsModel.id(), "Entity not found during get"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeDelete(WeightsModel weightsModel) {
        System.out.println(weightsModel);
        EarthQaukeWeights weightsEntity = Optional.ofNullable(weightGateway.delete(EarthQuakeWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()))
                .orElseThrow(() -> new EntityNotFoundException(weightsModel.id(), "Entity not found during delete"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public List<WeightsModel> executeGetList() {
        List<EarthQaukeWeights> weightsEntity = Optional.ofNullable(weightGateway.listFilter())
                .orElseThrow(() -> new EntityNotFoundException(0L, "Entity list not found"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }

    public List<WeightsModel> executeUpdateList(List<WeightsModel> weightsModels) {
        List<EarthQaukeWeights> weightsEntity = Optional.ofNullable(weightGateway.updateOrSave(EarthQuakeWeightMapper.INSTANCE.WeightsModelListToWeightEntityList(weightsModels)))
                .orElseThrow(() -> new EntityNotFoundException(0L, "Entity list not found during update or save"));
        return EarthQuakeWeightMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }
}
