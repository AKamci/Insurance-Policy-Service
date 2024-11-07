package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCarPolicy;
import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
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
    public CreateCarPolicyResponse create (CarPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.carPolicyModelToCreateCarPolicyResponse
                (executeCarPolicy.executeCreate(carPolicyModel));
    }

    @Override
    public UpdateCarPolicyResponse update (CarPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelToUpdateCarPolicyResponse
                (executeCarPolicy.executeUpdate(carPolicyModel));
    }


    @Override
    public DeleteCarPolicyResponse delete(CarPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelToDeleteCarPolicyResponse
                (executeCarPolicy.executeDelete(carPolicyModel));
    }


    @Override
    public List<GetCarPolicyResponse> getList(CarPolicyModel carPolicyModel) {
        return CarPolicyMapper.INSTANCE.cartPolicyModelListToGetCarPolicyResponseList
                (executeCarPolicy.executeGetList(carPolicyModel));
    }

    @Override
    public GetCarPolicyResponse get (CarPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelToGetCarPolicyResponse
                (executeCarPolicy.executeGet(carPolicyModel));
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

    public SetCarPolicyStatusResponse acceptCarPolicy (CarPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelToSetStateCarPolicyResponse
                (executeCarPolicy.changeCarPolicyState(carPolicyModel, CarPolicyEvent.ACTIVATE));
    }
    public SetCarPolicyStatusResponse rejectCarPolicy (CarPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelToSetStateCarPolicyResponse
                (executeCarPolicy.changeCarPolicyState(carPolicyModel, CarPolicyEvent.CANCEL));
    }




    public int getTotalRecord() {
        return executeCarPolicy.executeGetTotalRecord();
    }



}