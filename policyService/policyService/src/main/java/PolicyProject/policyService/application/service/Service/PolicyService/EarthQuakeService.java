package PolicyProject.policyService.application.service.Service.PolicyService;

import PolicyProject.policyService.application.service.IService.IEarthQuakeService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteEarthQuakePolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.EarthQuakeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EarthQuakeService implements IEarthQuakeService {


    private final ExecuteEarthQuakePolicy executeEarthQuakePolicy;
    private final ObjectValidation objectValidation;

    @Override
    public List<GetEarthQuakeResponse> getList(EarthQuakeModel earthQuakeModel) {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelListToGetEarthQuakeResponseList
                (executeEarthQuakePolicy.executeGetList(earthQuakeModel));
    }

    @Override
    public GetEarthQuakeResponse get(EarthQuakeModel earthQuakeModel) {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelToGetEarthQuakeResponse
                (executeEarthQuakePolicy.executeGet(earthQuakeModel));
    }

    @Override
    public CreateEarthQuakeResponse create(EarthQuakeModel earthQuakeModel) {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelToCreateEarthQuakeResponse
                (executeEarthQuakePolicy.executeCreate(earthQuakeModel));
    }

    @Override
    public UpdateEarthQuakeResponse update(EarthQuakeModel earthQuakeModel) {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelToUpdateEarthQuakeResponse
                (executeEarthQuakePolicy.executeUpdate(earthQuakeModel));
    }

    @Override
    public DeleteEarthQuakeResponse delete(EarthQuakeModel earthQuakeModel) {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelToDeleteEarthQuakeResponse
                (executeEarthQuakePolicy.executeDelete(earthQuakeModel));
    }

    public SetStateEarthQuakeResponse accept (EarthQuakeModel earthQuakeModel)
    {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelToSetStateEarthQuakeResponse
                (executeEarthQuakePolicy.changeCarPolicyState(earthQuakeModel, PolicyEvent.ACTIVATE));
    }
    public SetStateEarthQuakeResponse reject (EarthQuakeModel earthQuakeModel)
    {
        objectValidation.validateModel(earthQuakeModel, "earthQuakeModel");
        return EarthQuakeMapper.INSTANCE.earthQuakeModelToSetStateEarthQuakeResponse
                (executeEarthQuakePolicy.changeCarPolicyState(earthQuakeModel, PolicyEvent.CANCEL));
    }

    public int getTotalRecord() {
        return executeEarthQuakePolicy.executeGetTotalRecord();
    }

}
