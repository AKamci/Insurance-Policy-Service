package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.CreateEarthQuakeResponse;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.DeleteEarthQuakeResponse;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.GetEarthQuakeResponse;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.UpdateEarthQuakeResponse;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.CreateHealthPolicyResponse;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.DeleteHealthPolicyResponse;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.GetHealthPolicyResponse;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.UpdateHealthPolicyResponse;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.domain.model.HealthPolicyModel;

public interface IHealthPolicyService extends IBaseService<
        CreateHealthPolicyResponse,
        UpdateHealthPolicyResponse,
        DeleteHealthPolicyResponse,
        GetHealthPolicyResponse,
        GetHealthPolicyResponse,
        HealthPolicyModel
        >
{}