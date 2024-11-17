package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.WeightGateway;
import PolicyProject.policyService.application.service.StrategyFactory.WeightStrategyFactory;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.domain.model.WeightsModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.ConstantStrategy;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteWeight {


    private final WeightGateway weightGateway;
    private final WeightStrategyFactory strategyFactory;

    public LicensePlateModel Get_ALicensePlateModel(LicensePlateModel licensePlateModel) {
        BigDecimal Amount = calculatePolicyPrice(licensePlateModel);
        LicensePlateModel newPlate = new LicensePlateModel(
                null,
                licensePlateModel.plate(),
                licensePlateModel.car(),
                licensePlateModel.customer(),
                licensePlateModel.coverageCode(),
                licensePlateModel.policyStartDate(),
                licensePlateModel.policyEndDate(),
                Amount.longValue()
        );
        return newPlate;
    }

    private BigDecimal calculatePolicyPrice(LicensePlateModel licensePlateModel) {
        BigDecimal total = BigDecimal.ZERO;
        List<Weights> parameters = weightGateway.list();

        for (Weights parameter : parameters) {
            IWeightStrategy strategy = strategyFactory.getStrategy(parameter.getType());
            BigDecimal valueToCheck = strategy.getValue(licensePlateModel);

            if (strategy instanceof ConstantStrategy) {
                total = total.add(strategy.calculate(licensePlateModel, parameter));
            } else if (parameter.getMinValue() != null && parameter.getMaxValue() != null) {
                if (valueToCheck != null && parameter.getMinValue().compareTo(valueToCheck) <= 0
                        && parameter.getMaxValue().compareTo(valueToCheck) >= 0) {
                    total = total.add(strategy.calculate(licensePlateModel, parameter));
                    System.out.println("Parametre :" + parameter);
                    System.out.println("Total :" + total);
                    System.out.println("Strategy :" + strategy);
                    System.out.println();
                }
            } else {
                System.out.println("Doğru Aralığa girmedi");
                System.out.println(strategy);
                total = total.add(strategy.calculate(licensePlateModel, parameter));
            }
        }

        return total;
    }

    public WeightsModel executeUpdate(WeightsModel weightsModel)
    {
        Optional<Weights> optionalEntity = Optional.ofNullable
                (weightGateway.update(WeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel)));
        Weights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return WeightsMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeCreate(WeightsModel weightsModel)
    {
        Weights EnityObject = weightGateway.create(WeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel));

        return WeightsMapper.INSTANCE.WeightsEntityToWeightsModel(EnityObject);
    }

    public WeightsModel executeGet(WeightsModel weightsModel)
    {
        Optional<Weights> optionalEntity = Optional.ofNullable
                (weightGateway.get(WeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
        Weights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return WeightsMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeDelete(WeightsModel weightsModel)
    {
        System.out.println(weightsModel);
        Optional<Weights> optionalEntity = Optional.ofNullable
                (weightGateway.delete(WeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
        Weights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return WeightsMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public List<WeightsModel> executeGetList()
    {
        Optional<List<Weights>> optionalEntity = Optional.ofNullable
                (weightGateway.listFilter());
        List<Weights> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
        return WeightsMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }

    public List<WeightsModel> executeUpdateList(List<WeightsModel> weightsModels) {
        Optional<List<Weights>> optionalEntity = Optional.ofNullable
                (weightGateway.updateOrSave(WeightsMapper.INSTANCE.WeightsModelListToWeightEntityList(weightsModels)));
        List<Weights> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
        return WeightsMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }


}

