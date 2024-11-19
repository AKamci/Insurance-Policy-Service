package PolicyProject.policyService.domain.dto.response.EarthQuakeResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.IEarthQuakeResponse;

public record DeleteEarthQuakeResponse(
        Long policyId


) implements IEarthQuakeResponse {
}
