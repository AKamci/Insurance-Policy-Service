//package PolicyProject.policyService.application.usecases.ExecuteWeights;
//
//import PolicyProject.policyService.application.gateways.WeightsGateway.CarPolicyWeightGateway;
//import PolicyProject.policyService.application.service.ModelFactory.LicensePlateModelFactory;
//import PolicyProject.policyService.application.service.StrategyFactory.CarPolicyWeightStrategyFactory;
//import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
//import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
//import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
//import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
//import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
//import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.ConstantStrategy;
//import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//
//public class ExecuteCarPolicyWeight {
//
//    private final CarPolicyWeightGateway weightGateway;
//    private final CarPolicyWeightStrategyFactory strategyFactory;
//
//    public LicensePlateModel Get_ALicensePlateModel(LicensePlateModel licensePlateModel) {
//        BigDecimal Amount = calculatePolicyPrice(licensePlateModel);
//        return LicensePlateModelFactory.createLicensePlateModelWithAmount(licensePlateModel, Amount.longValue());
//    }
//
//    private BigDecimal calculatePolicyPrice(LicensePlateModel licensePlateModel) {
//        BigDecimal total = BigDecimal.ZERO;
//        List<Weights> parameters = weightGateway.list();
//
//        for (Weights parameter : parameters) {
//            IWeightStrategy strategy = strategyFactory.getStrategy(parameter.getType());
//            BigDecimal valueToCheck = strategy.getValue(licensePlateModel);
//
//            if (strategy instanceof ConstantStrategy) {
//                total = total.add(strategy.calculate(licensePlateModel, parameter));
//            } else if (parameter.getMinValue() != null && parameter.getMaxValue() != null) {
//                if (valueToCheck != null && parameter.getMinValue().compareTo(valueToCheck) <= 0
//                        && parameter.getMaxValue().compareTo(valueToCheck) >= 0) {
//                    total = total.add(strategy.calculate(licensePlateModel, parameter));
//                    System.out.println("Parametre :" + parameter);
//                    System.out.println("Total :" + total);
//                    System.out.println("Strategy :" + strategy);
//                    System.out.println();
//                }
//            } else {
//                System.out.println("Doğru Aralığa girmedi");
//                System.out.println(strategy);
//                total = total.add(strategy.calculate(licensePlateModel, parameter));
//            }
//        }
//
//        return total;
//    }
//
//    public WeightsModel executeUpdate(WeightsModel weightsModel)
//    {
//        Optional<Weights> optionalEntity = Optional.ofNullable
//                (weightGateway.update(CarPolicyWeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel)));
//        Weights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
//        return CarPolicyWeightsMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
//    }
//
//    public WeightsModel executeCreate(WeightsModel weightsModel)
//    {
//        Weights EnityObject = weightGateway.create(CarPolicyWeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel));
//
//        return CarPolicyWeightsMapper.INSTANCE.WeightsEntityToWeightsModel(EnityObject);
//    }
//
//    public WeightsModel executeGet(WeightsModel weightsModel)
//    {
//        Optional<Weights> optionalEntity = Optional.ofNullable
//                (weightGateway.get(CarPolicyWeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
//        Weights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
//        return CarPolicyWeightsMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
//    }
//
//    public WeightsModel executeDelete(WeightsModel weightsModel)
//    {
//        System.out.println(weightsModel);
//        Optional<Weights> optionalEntity = Optional.ofNullable
//                (weightGateway.delete(CarPolicyWeightsMapper.INSTANCE.WeightsModelToWeightEntity(weightsModel).getKey()));
//        Weights weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(weightsModel.id(),"Entity not found"));
//        return CarPolicyWeightsMapper.INSTANCE.WeightsEntityToWeightsModel(weightsEntity);
//    }
//
//    public List<WeightsModel> executeGetList()
//    {
//        Optional<List<Weights>> optionalEntity = Optional.ofNullable
//                (weightGateway.listFilter());
//        List<Weights> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
//        return CarPolicyWeightsMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
//    }
//
//    public List<WeightsModel> executeUpdateList(List<WeightsModel> weightsModels) {
//        Optional<List<Weights>> optionalEntity = Optional.ofNullable
//                (weightGateway.updateOrSave(CarPolicyWeightsMapper.INSTANCE.WeightsModelListToWeightEntityList(weightsModels)));
//        List<Weights> weightsEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(0L,"Entity not found"));
//        return CarPolicyWeightsMapper.INSTANCE.WeightsEntityListToWeightsModelList(weightsEntity);
//    }
//
//
//}
//
