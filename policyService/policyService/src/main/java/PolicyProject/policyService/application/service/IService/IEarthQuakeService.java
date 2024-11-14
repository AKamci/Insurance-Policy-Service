package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.CreateCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.DeleteCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.GetCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.UpdateCarPolicyResponse;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.EarthQuakeModel;

public interface IEarthQuakeService extends IBaseService<
        CreateEarthQuakeResponse,
        UpdateEarthQuakeResponse,
        DeleteEarthQuakeResponse,
        GetEarthQuakeResponse,
        GetEarthQuakeResponse,
        EarthQuakeModel
        >
{}
