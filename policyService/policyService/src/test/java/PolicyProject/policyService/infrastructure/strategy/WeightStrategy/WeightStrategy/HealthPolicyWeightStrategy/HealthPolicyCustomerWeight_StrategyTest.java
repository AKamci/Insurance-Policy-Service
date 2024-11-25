package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy.HealthPolicyCustomerWeight_Strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealthPolicyCustomerWeight_StrategyTest {

    @Test
    void testGetValueForStandardWeight() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L, "12345678901", null, 1, null, 180, 75.5, 23.3, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();
        BigDecimal expectedValue = BigDecimal.valueOf(75.5);
        BigDecimal actualValue = strategy.getValue(model);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testGetValueForZeroWeight() {
        PersonalHealthModel model = new PersonalHealthModel(
                2L, "12345678902", null, 1, null, 180, 0.0, 0.0, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();
        BigDecimal expectedValue = BigDecimal.valueOf(0.0);
        BigDecimal actualValue = strategy.getValue(model);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testGetValueForNegativeWeight() {
        PersonalHealthModel model = new PersonalHealthModel(
                3L, "12345678903", null, 1, null, 180, -10.0, -3.1, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();
        BigDecimal expectedValue = BigDecimal.valueOf(-10.0);
        BigDecimal actualValue = strategy.getValue(model);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testCalculateWithValidParameters() {
        PersonalHealthModel model = new PersonalHealthModel(
                4L, "12345678904", null, 1, null, 180, 80.0, 25.0, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(BigDecimal.valueOf(1.5));

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();
        BigDecimal expectedValue = BigDecimal.valueOf(120.0);
        BigDecimal actualValue = strategy.calculate(model, parameter);

        assertTrue(expectedValue.compareTo(actualValue) == 0,
                "Expected: " + expectedValue + " but got: " + actualValue);
    }

    @Test
    void testCalculateWithZeroWeightParameter() {
        PersonalHealthModel model = new PersonalHealthModel(
                5L, "12345678905", null, 1, null, 180, 70.0, 22.5, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(BigDecimal.ZERO);

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();
        BigDecimal expectedValue = BigDecimal.valueOf(0.0);
        BigDecimal actualValue = strategy.calculate(model, parameter);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testCalculateWithNegativeWeightParameter() {
        PersonalHealthModel model = new PersonalHealthModel(
                6L, "12345678906", null, 1, null, 180, 85.0, 28.3, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(BigDecimal.valueOf(-2.0));

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();
        BigDecimal expectedValue = BigDecimal.valueOf(-170.00);
        BigDecimal actualValue = strategy.calculate(model, parameter);

        assertTrue(expectedValue.compareTo(actualValue) == 0,
                "Expected: " + expectedValue + " but got: " + actualValue);
    }
    @Test
    void testCalculateWithNullWeightParameter() {
        PersonalHealthModel model = new PersonalHealthModel(
                7L, "12345678907", null, 1, null, 180, 68.0, 21.0, null,
                false, false, false, false, false, 0L
        );

        HealthPolicyWeight parameter = new HealthPolicyWeight();
        parameter.setWeight(null);

        HealthPolicyCustomerWeight_Strategy strategy = new HealthPolicyCustomerWeight_Strategy();

        try {
            strategy.calculate(model, parameter);
        } catch (NullPointerException e) {
            String expectedMessagePart1 = "Cannot invoke \"java.math.BigDecimal.multiply(java.math.BigDecimal)\" because";
            String expectedMessagePart2 = "the return value of \"PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight.getWeight()\" is null";
            assertTrue(e.getMessage().contains(expectedMessagePart1),
                    "Expected to contain: \"" + expectedMessagePart1 + "\" but was: " + e.getMessage());
            assertTrue(e.getMessage().contains(expectedMessagePart2),
                    "Expected to contain: \"" + expectedMessagePart2 + "\" but was: " + e.getMessage());
        }
    }
}