package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class EarthQuakeRisk_StrategyTest {

    private final EarthQuakeRisk_Strategy strategy = new EarthQuakeRisk_Strategy();

    @Test
    public void testGetValue_ShouldReturnOne_WhenModelIsNotNull() {
        HouseModel model = new HouseModel(1L, 123, 456, 789, null, null, "12345678901", 100000L);
        BigDecimal value = strategy.getValue(model);
        Assertions.assertEquals(BigDecimal.valueOf(1), value);
    }

    @Test
    public void testCalculate_ShouldMultiplyWeightAndValue_WhenModelAndParameterAreNotNull() {
        HouseModel model = new HouseModel(1L, 123, 456, 789, null, null, "12345678901", 100000L);
        EarthQaukeWeights parameter = new EarthQaukeWeights();
        parameter.setWeight(BigDecimal.valueOf(10));

        BigDecimal result = strategy.calculate(model, parameter);
        Assertions.assertEquals(BigDecimal.valueOf(10), result);
    }

    @Test
    public void testCalculate_ShouldReturnZero_WhenWeightIsZero() {
        HouseModel model = new HouseModel(1L, 123, 456, 789, null, null, "12345678901", 100000L);
        EarthQaukeWeights parameter = new EarthQaukeWeights();
        parameter.setWeight(BigDecimal.ZERO);

        BigDecimal result = strategy.calculate(model, parameter);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_ShouldReturnZero_WhenModelIsNull() {
        EarthQaukeWeights parameter = new EarthQaukeWeights();
        parameter.setWeight(BigDecimal.valueOf(10));

        BigDecimal result = strategy.calculate(null, parameter);
       Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_ShouldReturnZero_WhenParameterIsNull() {
        HouseModel model = new HouseModel(1L, 123, 456, 789, null, null, "12345678901", 100000L);

        BigDecimal result = strategy.calculate(model, null);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_ShouldReturnZero_WhenModelAndParameterAreNull() {
        BigDecimal result = strategy.calculate(null, null);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_ShouldHandleNegativeWeight() {
        HouseModel model = new HouseModel(1L, 123, 456, 789, null, null, "12345678901", 100000L);
        EarthQaukeWeights parameter = new EarthQaukeWeights();
        parameter.setWeight(BigDecimal.valueOf(-10));

        BigDecimal result = strategy.calculate(model, parameter);
        Assertions.assertEquals(BigDecimal.valueOf(-10), result);
    }
}