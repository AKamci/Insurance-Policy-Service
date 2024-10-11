package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.Calculator;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteCarPolicy {

    private final CarPolicyGateway carPolicyGateway;
    private final ExecuteCustomer executeCustomer;

    public CarPolicyModel executeUpdate(CarPolicyModel carPolicyModel)
    {
        Optional<CarPolicy> optionalEntity = Optional.ofNullable
                (carPolicyGateway.update(CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel)));
        CarPolicy carPolicyEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.customerId(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(carPolicyEntity);
    }

    public CarPolicyModel executeCreate(CarPolicyModel carPolicyModel)
    {
        CustomerModel customerModel = new CustomerModel(carPolicyModel.customerId(),
                null,null,null,null,null, null,0,null, null);
        //Get a CUSTOMER
        Customer customer = CustomerMapper.INSTANCE.customerModelToCustomerEntity
                (executeCustomer.executeGet(customerModel));

        var PolicyAmount = Calculator.Calculate(carPolicyModel);
        CarPolicy EnityObject = carPolicyGateway.create
                (CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel), PolicyAmount,customer);
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(EnityObject);
    }

    public CarPolicyModel executeGet(CarPolicyModel carPolicyModel)
    {
        Optional<CarPolicy> optionalEntity = Optional.ofNullable
                (carPolicyGateway.get(CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel)));
        CarPolicy carPolicyEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.customerId(),"Entity not found"));
        carPolicyEntity.getCustomer().getId();
        return (CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(carPolicyEntity));

    }

    public CarPolicyModel executeDelete(CarPolicyModel carPolicyModel)
    {
        Optional<CarPolicy> optionalEntity = Optional.ofNullable
                (carPolicyGateway.delete(CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel)));
        CarPolicy carPolicyEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.customerId(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(carPolicyEntity);
    }

    public List<CarPolicyModel> executeGet_wPolicy(CarPolicyModel carPolicyModel)
    {
        Long customerId = carPolicyModel.customerId();

        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
                (carPolicyGateway.getCarPoliciesByCustomer(customerId));

        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
    }

    public List<CarPolicyModel> executeGetList()
    {
        var EnityObject = carPolicyGateway.getList();
        return CarPolicyMapper.INSTANCE.CarpolicyEntityListToCarpolicyModelList(EnityObject);
    }




}
