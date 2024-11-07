package PolicyProject.policyService.domain.dto.response.WeightResponse;

import java.math.BigDecimal;

public record GetWeightResponse(
        Long id,
        String key,
        BigDecimal weight,
        BigDecimal minValue,
        BigDecimal maxValue,
        String type
) implements IWeightResponse {
}
