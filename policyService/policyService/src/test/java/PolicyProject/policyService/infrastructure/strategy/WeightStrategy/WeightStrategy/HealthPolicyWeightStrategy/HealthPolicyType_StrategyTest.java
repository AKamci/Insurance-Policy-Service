package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy.HealthPolicyType_Strategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HealthPolicyType_StrategyTest {

    @Test
    public void testGetValue_WithValidCoverageCode() {
        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.coverageCode()).thenReturn(100);

        HealthPolicyType_Strategy strategy = new HealthPolicyType_Strategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(100), result);
    }

    @Test
    public void testGetValue_WithZeroCoverageCode() {
        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.coverageCode()).thenReturn(0);

        HealthPolicyType_Strategy strategy = new HealthPolicyType_Strategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testGetValue_WithNegativeCoverageCode() {
        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.coverageCode()).thenReturn(-50);

        HealthPolicyType_Strategy strategy = new HealthPolicyType_Strategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(-50), result);
    }

    @Test
    public void testCalculate_WithPositiveWeight() {
        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.coverageCode()).thenReturn(100);

        HealthPolicyWeight parameter = mock(HealthPolicyWeight.class);
        when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(2));

        HealthPolicyType_Strategy strategy = new HealthPolicyType_Strategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(200), result);
    }

    @Test
    public void testCalculate_WithZeroWeight() {
        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.coverageCode()).thenReturn(100);

        HealthPolicyWeight parameter = mock(HealthPolicyWeight.class);
        when(parameter.getWeight()).thenReturn(BigDecimal.ZERO);

        HealthPolicyType_Strategy strategy = new HealthPolicyType_Strategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_WithNegativeWeight() {
        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.coverageCode()).thenReturn(100);

        HealthPolicyWeight parameter = mock(HealthPolicyWeight.class);
        when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(-1));

        HealthPolicyType_Strategy strategy = new HealthPolicyType_Strategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(-100), result);
    }
}