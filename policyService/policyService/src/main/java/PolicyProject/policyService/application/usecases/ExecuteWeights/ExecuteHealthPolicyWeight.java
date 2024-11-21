package PolicyProject.policyService.application.usecases.ExecuteWeights;

import PolicyProject.policyService.application.gateways.WeightsGateway.HealthPolicyWeightGateway;
import PolicyProject.policyService.application.service.ModelFactory.PersonalHealthModelFactory;
import PolicyProject.policyService.application.service.StrategyFactory.HealthPolicyWeightStrategyFactory;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.EarthQuakeConstantStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy.HealtPolicyBoolen_Strategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy.HealthPolicyConstant_Strategy;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.HealthPolicyWeightMapper;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteHealthPolicyWeight {
    private final HealthPolicyWeightGateway healthPolicyWeightGateway;
    private final HealthPolicyWeightStrategyFactory healthPolicyWeightStrategyFactory;

    public PersonalHealthModel Get_APersonalHealthModel(PersonalHealthModel personalHealthModel, Integer coverageCode) {
        PersonalHealthModel calculateModel = PersonalHealthModelFactory.
                createCalculatedPersonalHealthModel(personalHealthModel, coverageCode);

        BigDecimal Amount = calculatePolicyPrice(calculateModel);
        return PersonalHealthModelFactory.createPersonalHealthModelWithAmount(personalHealthModel, Amount.longValue());
    }

    private BigDecimal calculatePolicyPrice(PersonalHealthModel personalHealthModel) {
        BigDecimal total = BigDecimal.ZERO;
        List<HealthPolicyWeight> parameters = healthPolicyWeightGateway.list();

        for (HealthPolicyWeight parameter : parameters) {
            IWeightStrategy strategy = healthPolicyWeightStrategyFactory.getStrategy(parameter.getType());
            BigDecimal valueToCheck = strategy.getValue(personalHealthModel);

            if (strategy instanceof HealthPolicyConstant_Strategy) {
                System.out.println("Parametre :" + parameter);
                System.out.println("Total :" + total);
                System.out.println("Strategy :" + strategy);;
                total = total.add(strategy.calculate(personalHealthModel, parameter));
            } else if (strategy instanceof HealtPolicyBoolen_Strategy) {
                System.out.println("Parametre :" + parameter);
                System.out.println("Total :" + total);
                System.out.println("Strategy :" + strategy);
                total = total.add(strategy.calculate(personalHealthModel, parameter));
            } else if (parameter.getMinValue() != null && parameter.getMaxValue() != null) {
                if (valueToCheck != null && parameter.getMinValue().compareTo(valueToCheck) <= 0
                        && parameter.getMaxValue().compareTo(valueToCheck) >= 0) {
                    total = total.add(strategy.calculate(personalHealthModel, parameter));
                    System.out.println("Parametre :" + parameter);
                    System.out.println("Total :" + total);
                    System.out.println("Strategy :" + strategy);
                    System.out.println();
                }
            } else {
                System.out.println("Doğru Aralığa girmedi");
                System.out.println(strategy);
                total = total.add(strategy.calculate(personalHealthModel, parameter));
            }
        }

        return total;
    }

    public WeightsModel executeUpdate(WeightsModel weightsModel)
    {
        Optional<HealthPolicyWeight> optionalEntity = Optional.ofNullable
                (healthPolicyWeightGateway.update(HealthPolicyWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel)));
        HealthPolicyWeight weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return HealthPolicyWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeCreate(WeightsModel weightsModel)
    {
        HealthPolicyWeight EnityObject = healthPolicyWeightGateway.create(HealthPolicyWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel));

        return HealthPolicyWeightMapper.INSTANCE.WeightsEntityToWeightsModel(EnityObject);
    }

    public WeightsModel executeGet(WeightsModel weightsModel)
    {
        Optional<HealthPolicyWeight> optionalEntity = Optional.ofNullable
                (healthPolicyWeightGateway.get(HealthPolicyWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
        HealthPolicyWeight weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return HealthPolicyWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public WeightsModel executeDelete(WeightsModel weightsModel)
    {
        System.out.println(weightsModel);
        Optional<HealthPolicyWeight> optionalEntity = Optional.ofNullable
                (healthPolicyWeightGateway.delete(HealthPolicyWeightMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
        HealthPolicyWeight weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
        return HealthPolicyWeightMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
    }

    public List<WeightsModel> executeGetList()
    {
        Optional<List<HealthPolicyWeight>> optionalEntity = Optional.ofNullable
                (healthPolicyWeightGateway.listFilter());
        List<HealthPolicyWeight> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
        return HealthPolicyWeightMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }

    public List<WeightsModel> executeUpdateList(List<WeightsModel> weightsModels) {
        Optional<List<HealthPolicyWeight>> optionalEntity = Optional.ofNullable
                (healthPolicyWeightGateway.updateOrSave(HealthPolicyWeightMapper.INSTANCE.WeightsModelListToWeightEntityList(weightsModels)));
        List<HealthPolicyWeight> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
        return HealthPolicyWeightMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
    }


}
