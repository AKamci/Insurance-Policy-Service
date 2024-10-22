package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCarPolicy;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CarPolicyService implements ICarPolicyService {

private final ExecuteCarPolicy executeCarPolicy;
private final ObjectValidation objectValidation;

@Override
@Async
public CompletableFuture<CreateCarPolicyResponse> create (CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> createdCarPolicyModelFuture = executeCarPolicy.executeCreate(carPolicyModel);
    return createdCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.carPolicyModelToCreateCarPolicyResponse(carPolicyModel));
}

@Override
@Async
public CompletableFuture<UpdateCarPolicyResponse> update (CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> updatedCarPolicyModelFuture = executeCarPolicy.executeUpdate(carPolicyModel);
    return updatedCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.cartPolicyModelToUpdateCarPolicyResponse(carPolicyModel));
}


@Override
@Async
public CompletableFuture<DeleteCarPolicyResponse> delete(CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> deletedCarPolicyModelFuture = executeCarPolicy.executeDelete(carPolicyModel);
    return deletedCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.cartPolicyModelToDeleteCarPolicyResponse(carPolicyModel));
}

@Override
@Async
public CompletableFuture<List<GetCarPolicyResponse>> getList(CarPolicyModel carPolicyModel) {
    return executeCarPolicy.executeGetList(carPolicyModel)
            .thenCompose(customerModels -> {
                List<CompletableFuture<GetCarPolicyResponse>> futures = customerModels.stream()
                        .map(customerModelItem ->
                                CompletableFuture.supplyAsync(() ->
                                        CarPolicyMapper.INSTANCE.cartPolicyModelToGetCarPolicyResponse(customerModelItem)
                                )
                        )
                        .toList();
                return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                        .thenApply(v -> futures.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()));
            });

}
@Override
@Async
public CompletableFuture<GetCarPolicyResponse> get(CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> getCarPolicyModelFuture = executeCarPolicy.executeGet(carPolicyModel);
    return getCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.cartPolicyModelToGetCarPolicyResponse(carPolicyModel));
}


    @Async
    @Override
    public CompletableFuture<Integer> getTotalRecord() {
        return executeCarPolicy.executeGetTotalRecord();
    }







//    public List<GetCarPolicyResponse> getByPlate (CarPolicyModel carPolicyModel)
//    {
//        //objectValidation.carPolicyModelValidations(carPolicyModel);
//        return CarPolicyMapper.INSTANCE.cartPolicyModelListToGetCarPolicyResponseList
//                (executeCarPolicy.executeGetWPlate(carPolicyModel));
//    }
//
//
//    public List<GetCustomerCarPoliciesResponse> get_wPolicy(CarPolicyModel carPolicyModel) {
//        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
//                (executeCarPolicy.executeGet_wPolicy(carPolicyModel));
//    }
//
//    public List<GetCustomerCarPoliciesResponse> get_Policies_BetweenDate(CarPolicyModel carPolicyModel) {
//        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
//                (executeCarPolicy.executeGet_BetweenDate(carPolicyModel));
//    }





}
