package PolicyProject.policyService.domain.dto.request.WeightRequest;

import java.math.BigDecimal;

public record GetWeightRequest(

        String key

) implements IWeightRequest{
}
