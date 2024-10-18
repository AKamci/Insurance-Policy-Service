package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCarPolicy;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;


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


    public int getTotalRecord() {
        return executeCarPolicy.executeGetTotalRecord();
    }



}
