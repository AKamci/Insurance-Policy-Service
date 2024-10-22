package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.config.Specifications.CarPolicySpecification;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CarPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.Calculator;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ExecuteCarPolicy {

    private final CarPolicyGateway carPolicyGateway;
    private final ExecuteCustomer executeCustomer;
    private final CarPolicySpecificationBuild carPolicySpecificationBuild;


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
                null,null,null,null,null, null,null,null, 0, 0, null );
        //Get a CUSTOMER
        Customer customer = CustomerMapper.INSTANCE.customerModelToCustomerEntity
                (executeCustomer.executeGet(customerModel));

        CarPolicy EnityObject = carPolicyGateway.create
                (CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel),customer);
        return CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(EnityObject);
    }

    public CarPolicyModel executeGet(CarPolicyModel carPolicyModel)
    {
        Optional<CarPolicy> optionalEntity = Optional.ofNullable
                (carPolicyGateway.get(CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel)));
        CarPolicy carPolicyEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.customerId(),"Entity not found"));
        return (CarPolicyMapper.INSTANCE.carPolicyEntityToCarPolicyModel(carPolicyEntity));
    }

    public List<CarPolicyModel> executeGetWPlate(CarPolicyModel carPolicyModel)
    {
        String plate = carPolicyModel.licensePlateNumber();
        String tckn = carPolicyModel.tckn();
        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
                (carPolicyGateway.getCarPoliciesByPlateAndTckn(plate, tckn));

        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);

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
        String tckn = carPolicyModel.tckn();

        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
                (carPolicyGateway.getCarPoliciesByCustomer(tckn));

        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
    }

    public List<CarPolicyModel> executeGetList(CarPolicyModel carPolicyModel)
    {
        Specification<CarPolicy> specification = carPolicySpecificationBuild.CarPolicyBuild(CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel));
        int page = carPolicyModel.page();
        int size = carPolicyModel.size();
        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
                (carPolicyGateway.getList(specification, page, size));

        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
    }

    public List<CarPolicyModel> executeGet_BetweenDate(CarPolicyModel carPolicyModel)
    {
        LocalDate startDate = carPolicyModel.policyStartDate();
        LocalDate endDate = carPolicyModel.policyEndDate();

        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
                (carPolicyGateway.getCarPoliciesBetweenDate(startDate, endDate));

        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
    }

    public int executeGetTotalRecord()
    {
        return carPolicyGateway.getTotal();
    }


}
