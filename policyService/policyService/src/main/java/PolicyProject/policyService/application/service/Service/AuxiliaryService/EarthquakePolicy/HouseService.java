package PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy.ExecuteHouse;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final ExecuteHouse executeHouse;
    private final ObjectValidation objectValidation;


    public GetHouseWCustomerResponse getWCustomer(HouseModel houseModel)
    {
        objectValidation.validateModel(houseModel, "houseModel");
        HouseModel houseModelResult = executeHouse.ExecuteGetWithCustomer(houseModel);
        return HouseMapper.INSTANCE.HouseModelToGetHouseWCustomerResponse(houseModelResult);
    }
}
