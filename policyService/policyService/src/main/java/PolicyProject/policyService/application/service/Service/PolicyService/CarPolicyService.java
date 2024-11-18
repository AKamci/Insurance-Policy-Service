package PolicyProject.policyService.application.service.Service.PolicyService;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.ObjectValidation;

import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteCarPolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
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
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.carPolicyModelToCreateCarPolicyResponse
                (executeCarPolicy.executeCreate(carPolicyModel));
    }

    @Override
    public UpdateCarPolicyResponse update (CarPolicyModel carPolicyModel)
    {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelToUpdateCarPolicyResponse
                (executeCarPolicy.executeUpdate(carPolicyModel));
    }


    @Override
    public DeleteCarPolicyResponse delete(CarPolicyModel carPolicyModel)
    {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelToDeleteCarPolicyResponse
                (executeCarPolicy.executeDelete(carPolicyModel));
    }


    @Override
    public List<GetCarPolicyResponse> getList(CarPolicyModel carPolicyModel) {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelListToGetCarPolicyResponseList
                (executeCarPolicy.executeGetList(carPolicyModel));
    }

    @Override
    public GetCarPolicyResponse get (CarPolicyModel carPolicyModel)
    {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelToGetCarPolicyResponse
                (executeCarPolicy.executeGet(carPolicyModel));

    }


    public List<GetCarPolicyResponse> getByPlate (CarPolicyModel carPolicyModel)
    {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelListToGetCarPolicyResponseList
                (executeCarPolicy.executeGetWPlate(carPolicyModel));
    }


    public List<GetCustomerCarPoliciesResponse> get_wPolicy(CarPolicyModel carPolicyModel) {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
                (executeCarPolicy.executeGet_wPolicy(carPolicyModel));
    }

    public List<GetCustomerCarPoliciesResponse> get_Policies_BetweenDate(CarPolicyModel carPolicyModel) {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.customerModelToGetCarPoliciesByCustomer
                (executeCarPolicy.executeGet_BetweenDate(carPolicyModel));
    }

    public SetCarPolicyStatusResponse acceptCarPolicy (CarPolicyModel carPolicyModel)
    {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelToSetStateCarPolicyResponse
                (executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.ACTIVATE));
    }
    public SetCarPolicyStatusResponse rejectCarPolicy (CarPolicyModel carPolicyModel)
    {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        return CarPolicyMapper.INSTANCE.cartPolicyModelToSetStateCarPolicyResponse
                (executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.CANCEL));
    }

    public int getTotalRecord() {
        return executeCarPolicy.executeGetTotalRecord();
    }



}