package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.executeCarPolicy;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCarPoliciesByCustomer;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
public class carPolicyService implements ICarPolicyService {

private final executeCarPolicy executeCarPolicy;
private final ObjectValidation objectValidation;

@Override
public createCarPolicyResponse create (carPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    return CarPolicyMapper.INSTANCE.carPolicyModelToCreateCarPolicyResponse
                (executeCarPolicy.executeCreate(carPolicyModel));
}

@Override
public updateCarPolicyResponse update (carPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    return CarPolicyMapper.INSTANCE.cartPolicyModelToUpdateCarPolicyResponse
            (executeCarPolicy.executeUpdate(carPolicyModel));
}


@Override
public deleteCarPolicyResponse delete(carPolicyModel carPolicyModel)
{
    objectValidation.carPolicyModelValidations(carPolicyModel);
    return CarPolicyMapper.INSTANCE.cartPolicyModelToDeleteCarPolicyResponse
            (executeCarPolicy.executeDelete(carPolicyModel));
}


    /*
    @Override
    public Iterable<GetCustomerResponse> getList() {
           return List.of();
    }
    */
    @Override
    public getCarPolicyResponse get (carPolicyModel carPolicyModel)
    {
        objectValidation.carPolicyModelValidations(carPolicyModel);
        return CarPolicyMapper.INSTANCE.cartPolicyModelToGetCarPolicyResponse
                (executeCarPolicy.executeGet(carPolicyModel));
    }


    public List<getCarPolicyResponse> get_wPolicy(carPolicyModel carPolicyModel) {
        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
                (executeCarPolicy.executeGet_wPolicy(carPolicyModel));
    }






}
