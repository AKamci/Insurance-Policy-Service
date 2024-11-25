package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class HealthPolicyConstant_StrategyTest {
    @Test
    public void testCalculate_withValidInputs_returnsCorrectResult() {
        // Given
        HealthPolicyConstant_Strategy strategy = new HealthPolicyConstant_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                1L, "12345678910", null, 1, null, 180, 75.0, 23.15, null,
                false, false, false, false, false, 1000L);
        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(BigDecimal.valueOf(2));

        // When
        BigDecimal result = strategy.calculate(model, parameter);

        // Then
        Assertions.assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    public void testCalculate_withZeroWeight_returnsZero() {
        // Given
        HealthPolicyConstant_Strategy strategy = new HealthPolicyConstant_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                2L, "09876543210", null, 2, null, 170, 65.0, 22.49, null,
                true, true, false, false, false, 2000L);
        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(BigDecimal.ZERO);

        // When
        BigDecimal result = strategy.calculate(model, parameter);

        // Then
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_withNegativeWeight_returnsNegativeResult() {
        // Given
        HealthPolicyConstant_Strategy strategy = new HealthPolicyConstant_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                3L, "11223344556", null, 3, null, 160, 55.0, 21.48, null,
                false, true, true, true, true, 3000L);
        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(BigDecimal.valueOf(-3));

        // When
        BigDecimal result = strategy.calculate(model, parameter);

        // Then
        Assertions.assertEquals(BigDecimal.valueOf(-3), result);
    }

    @Test
    public void testCalculate_withNullWeight_throwsNullPointerException() {
        // Given
        HealthPolicyConstant_Strategy strategy = new HealthPolicyConstant_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                4L, "667788990011", null, 4, null, 150, 50.0, 24.44, null,
                true, false, false, true, false, 4000L);
        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(null);

        // When / Then
        Assertions.assertThrows(NullPointerException.class, () -> {
            strategy.calculate(model, parameter);
        });
    }

    @Test
    public void testGetValue_withValidModel_returnsOne() {
        // Given
        HealthPolicyConstant_Strategy strategy = new HealthPolicyConstant_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                5L, "12345678912", null, 5, null, 160, 55.0, 21.48, null,
                false, true, true, true, true, 3000L);

        // When
        BigDecimal result = strategy.getValue(model);

        // Then
        Assertions.assertEquals(BigDecimal.ONE, result);
    }

    @Test
    public void testGetValue_withNullModel_returnsDefault() {
        // Given
        HealthPolicyConstant_Strategy strategy = new HealthPolicyConstant_Strategy();
        PersonalHealthModel model = null;

        // When
        BigDecimal result = strategy.getValue(model);

        // Then
        Assertions.assertNotNull(result); // Or any expected default value check
    }
}