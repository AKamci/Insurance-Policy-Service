package PolicyProject.policyService.domain.dto.request.WeightRequest;
import PolicyProject.policyService.domain.dto.request.IRequest.IWeightRequest;

import java.math.BigDecimal;

public record UpdateWeightRequest(

        Long id,
        String key,
        BigDecimal weight,
        BigDecimal minValue,
        BigDecimal maxValue,
        String type

) implements IWeightRequest {
}
