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

    @Async
    public CompletableFuture<CarPolicyModel> executeUpdate(CarPolicyModel carPolicyModel)
    {
        return CompletableFuture.supplyAsync(() ->
                        CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel))
                .thenCompose(entity ->  (Optional.ofNullable(carPolicyGateway.update(entity))).orElseThrow(() ->
                                new EntityNotFoundException(carPolicyModel.id(),"Entity not found"))
                        .thenApply(CarPolicyMapper.INSTANCE::carPolicyEntityToCarPolicyModel));
    }
    @Async
    public CompletableFuture<CarPolicyModel> executeCreate(CarPolicyModel carPolicyModel) {
        return CompletableFuture.supplyAsync(() -> {

            CustomerModel customerModel = new CustomerModel(
                    carPolicyModel.customerId(), null, null, null, null, null, null, null, null, 0, 0, null);

            return executeCustomer.executeGet(customerModel)
                    .thenApply(CustomerMapper.INSTANCE::customerModelToCustomerEntity)
                    .join();
        }).thenCompose(customer -> {
            return carPolicyGateway.create(
                    CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel),
                    carPolicyModel.policyAmount(), customer);
        }).thenApply(CarPolicyMapper.INSTANCE::carPolicyEntityToCarPolicyModel);
    }
    @Async
    public CompletableFuture<CarPolicyModel> executeGet(CarPolicyModel carPolicyModel)
    {
        return CompletableFuture.supplyAsync(() ->
                        CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel))
                .thenCompose(entity ->  (Optional.ofNullable(carPolicyGateway.get(entity))).orElseThrow(() ->
                                new EntityNotFoundException(carPolicyModel.id(),"Entity not found"))
                        .thenApply(CarPolicyMapper.INSTANCE::carPolicyEntityToCarPolicyModel));
    }
    @Async
    public CompletableFuture<CarPolicyModel> executeDelete(CarPolicyModel carPolicyModel)
    {
        return CompletableFuture.supplyAsync(() ->
                        CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel))
                .thenCompose(entity ->  (Optional.ofNullable(carPolicyGateway.delete(entity))).orElseThrow(() ->
                                new EntityNotFoundException(carPolicyModel.id(),"Entity not found"))
                        .thenApply(CarPolicyMapper.INSTANCE::carPolicyEntityToCarPolicyModel));
    }

    @Async
    public CompletableFuture<List<CarPolicyModel>> executeGetList(CarPolicyModel carPolicyModel) {
        int page = carPolicyModel.page();
        int size = carPolicyModel.size();

        return CompletableFuture.supplyAsync(() ->
                        CarPolicyMapper.INSTANCE.carPolicyModelToCarPolicyEntity(carPolicyModel))
                .thenApply(carPolicySpecificationBuild::CarPolicyBuild)
                .thenCompose(specification -> carPolicyGateway.getList(specification, page, size))
                .thenApply(CarPolicyMapper.INSTANCE::carPolicyEntityListToCarPolicyModelList); // List dönüşümü
    }


    public CompletableFuture<Integer> executeGetTotalRecord() {
        // Asenkron işlemi başlatır
        return CompletableFuture.supplyAsync(() -> carPolicyGateway.getTotal());
    }


//    public List<CarPolicyModel> executeGet_BetweenDate(CarPolicyModel carPolicyModel)
//    {
//        LocalDate startDate = carPolicyModel.policyStartDate();
//        LocalDate endDate = carPolicyModel.policyEndDate();
//
//        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
//                (carPolicyGateway.getCarPoliciesBetweenDate(startDate, endDate));
//
//        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
//        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
//    }
//    public List<CarPolicyModel> executeGetWPlate(CarPolicyModel carPolicyModel)
//    {
//        String plate = carPolicyModel.licensePlateNumber();
//        String tckn = carPolicyModel.tckn();
//        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
//                (carPolicyGateway.getCarPoliciesByPlateAndTckn(plate, tckn));
//
//        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
//        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
//
//    }
//
//
//    public List<CarPolicyModel> executeGet_wPolicy(CarPolicyModel carPolicyModel)
//    {
//        String tckn = carPolicyModel.tckn();
//
//        Optional<List<CarPolicy>> EntityList = Optional.ofNullable
//                (carPolicyGateway.getCarPoliciesByCustomer(tckn));
//
//        List<CarPolicy> CarPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(carPolicyModel.id(),"Entity not found"));
//        return CarPolicyMapper.INSTANCE.carPolicyEntityListToCarPolicyModelList(CarPolicyList);
//    }



}
