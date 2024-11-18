package PolicyProject.policyService.domain.model;

import java.math.BigDecimal;

public record WeightsModel(

         Long id,
         String key,
         BigDecimal weight,
         BigDecimal minValue,
         BigDecimal maxValue,
         String type


) {


}
