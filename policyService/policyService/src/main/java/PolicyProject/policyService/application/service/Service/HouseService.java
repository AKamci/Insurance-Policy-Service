package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteEarthQuakePolicy;
import PolicyProject.policyService.application.usecases.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecuteLicensePlate;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.interfaces.mappers.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.LicensePlateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final ExecuteHouse executeHouse;
    private final ObjectValidation objectValidation;


    public GetHouseWCustomerResponse getWCustomer(HouseModel houseModel)
    {
        HouseModel houseModelResult = executeHouse.ExecuteGetWithCustomer(houseModel);
        return HouseMapper.INSTANCE.HouseModelToGetHouseWCustomerResponse(houseModelResult);
    }
}
