package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.usecases.executeCarPolicy;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class carPolicyService implements ICarPolicyService {

private final executeCarPolicy executeCarPolicy;
private final ObjectValidation ObjectValidation;

@Override
public createCarPolicyResponse create (carPolicyModel carPolicyModel)
{
    boolean IsValid = true;//ObjectValidation.carPolicyModelValidations(carPolicyModel);

    if (IsValid) {
        return CarPolicyMapper.INSTANCE.carPolicyModelToCreateCarPolicyResponse
                (executeCarPolicy.executeCreate(carPolicyModel));
    }

    return null;
}

@Override
public updateCarPolicyResponse update (carPolicyModel carPolicyModel)
{
    return null;
}

@Override
public List<carPolicyModel> getList()
{
    return List.of();
}

@Override
public getCarPolicyResponse get (carPolicyModel carPolicyModel)
{
    return CarPolicyMapper.INSTANCE.getCarPolicyResponse(executeCarPolicy.executeGet(carPolicyModel));
}

@Override
public deleteCarPolicyResponse delete(carPolicyModel carPolicyModel)
{
    return null;
}



}
