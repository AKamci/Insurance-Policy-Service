package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IHealthPolicyWeightStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthPolicyCustomerHeight_StrategyTest {

    @Test
    public void testCalculateWithHeight170() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L,
                "12345678901",
                null,
                100,
                null,
                170,
                75.5,
                24.7,
                null,
                true,
                false,
                false,
                false,
                false,
                null
        );
        HealthPolicyWeight parameter = mock(HealthPolicyWeight.class);
        when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(2));
        BigDecimal result = strategy.calculate(model, parameter);
        assertEquals(BigDecimal.valueOf(340), result);
    }

    @Test
    public void testCalculateWithHeight180() {
        PersonalHealthModel model = new PersonalHealthModel(
                2L,
                "10987654321",
                null,
                200,
                null,
                180,
                85.0,
                26.2,
                null,
                false,
                true,
                true,
                true,
                false,
                null
        );
        HealthPolicyWeight parameter = mock(HealthPolicyWeight.class);
        when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(1.5));
        BigDecimal result = strategy.calculate(model, parameter);
        assertEquals(BigDecimal.valueOf(270), result);
    }

    @Test
    public void testCalculateWithHeight150() {
        PersonalHealthModel model = new PersonalHealthModel(
                3L,
                "56789432109",
                null,
                300,
                null,
                150,
                50.0,
                22.2,
                null,
                true,
                true,
                false,
                false,
                false,
                null
        );
        HealthPolicyWeight parameter = mock(HealthPolicyWeight.class);
        when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(1.2));
        BigDecimal result = strategy.calculate(model, parameter);
        assertEquals(BigDecimal.valueOf(180.0), result);
    }

    private final HealthPolicyCustomerHeight_Strategy strategy = new HealthPolicyCustomerHeight_Strategy();

    @Test
    public void testGetValueWithHeight170() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L,
                "12345678901",
                null,
                100,
                null,
                170,
                75.5,
                24.7,
                null,
                true,
                false,
                false,
                false,
                false,
                null
        );
        BigDecimal value = strategy.getValue(model);
        assertEquals(BigDecimal.valueOf(170.0), value);
    }

    @Test
    public void testGetValueWithHeight180() {
        PersonalHealthModel model = new PersonalHealthModel(
                2L,
                "10987654321",
                null,
                200,
                null,
                180,
                85.0,
                26.2,
                null,
                false,
                true,
                true,
                true,
                false,
                null
        );
        BigDecimal value = strategy.getValue(model);
        assertEquals(BigDecimal.valueOf(180), value);
    }

    @Test
    public void testGetValueWithHeight150() {
        PersonalHealthModel model = new PersonalHealthModel(
                3L,
                "56789432109",
                null,
                300,
                null,
                150,
                50.0,
                22.2,
                null,
                true,
                true,
                false,
                false,
                false,
                null
        );
        BigDecimal value = strategy.getValue(model);
        assertEquals(BigDecimal.valueOf(150), value);
    }
}