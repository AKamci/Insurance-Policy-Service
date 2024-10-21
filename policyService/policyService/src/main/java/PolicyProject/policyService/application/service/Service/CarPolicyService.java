package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCarPolicy;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;


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
public CompletableFuture<UpdateCarPolicyResponse> update (CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> updatedCarPolicyModelFuture = executeCarPolicy.executeUpdate(carPolicyModel);
    return updatedCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.cartPolicyModelToUpdateCarPolicyResponse(carPolicyModel));
}


@Override
public CompletableFuture<DeleteCarPolicyResponse> delete(CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> deletedCarPolicyModelFuture = executeCarPolicy.executeDelete(carPolicyModel);
    return deletedCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.cartPolicyModelToDeleteCarPolicyResponse(carPolicyModel));
}

@Override
public CompletableFuture<List<GetCarPolicyResponse>> getList(CarPolicyModel carPolicyModel) {
  CompletableFuture<List<CarPolicyModel>> listCarPolicyModelFuture = executeCarPolicy.executeGetList(carPolicyModel);
  return listCarPolicyModelFuture.thenApply(CarPolicyMapper.INSTANCE::cartPolicyModelListToGetCarPolicyResponseList);
}

@Override
public CompletableFuture<GetCarPolicyResponse> get (CarPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    CompletableFuture<CarPolicyModel> getCarPolicyModelFuture = executeCarPolicy.executeGet(carPolicyModel);
    return getCarPolicyModelFuture.thenApply(createdModel ->
            CarPolicyMapper.INSTANCE.cartPolicyModelToGetCarPolicyResponse(carPolicyModel));
}


    public List<GetCarPolicyResponse> getByPlate (CarPolicyModel carPolicyModel)
    {
        //objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelListToGetCarPolicyResponseList
                (executeCarPolicy.executeGetWPlate(carPolicyModel));
    }


    public List<GetCustomerCarPoliciesResponse> get_wPolicy(CarPolicyModel carPolicyModel) {
        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
                (executeCarPolicy.executeGet_wPolicy(carPolicyModel));
    }

    public List<GetCustomerCarPoliciesResponse> get_Policies_BetweenDate(CarPolicyModel carPolicyModel) {
        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
                (executeCarPolicy.executeGet_BetweenDate(carPolicyModel));
    }


    public int getTotalRecord() {
        return executeCarPolicy.executeGetTotalRecord();
    }



}
