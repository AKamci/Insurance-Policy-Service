package PolicyProject.policyService.domain.dto.response.EarthQuakeResponse;

import PolicyProject.policyService.domain.dto.response.IResponse.IEarthQuakeResponse;

public record SetStateEarthQuakeResponse(
        Long policyId

) implements IEarthQuakeResponse {
}
