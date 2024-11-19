package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
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
