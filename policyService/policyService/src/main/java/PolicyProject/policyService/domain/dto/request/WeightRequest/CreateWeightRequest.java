package PolicyProject.policyService.domain.dto.request.WeightRequest;

import PolicyProject.policyService.domain.dto.request.IRequest.IWeightRequest;

import java.math.BigDecimal;

public record CreateWeightRequest(

        String key,
        BigDecimal weight,
        BigDecimal minValue,
        BigDecimal maxValue,
        String type

) implements IWeightRequest {
}
