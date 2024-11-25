package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EarthquakePolicyType_StrategyTest {

    private HouseModel createHouseModel(Integer coverageCode) {
        return new HouseModel(1L, coverageCode, 1, 100, null, null, "12345678901", 1000L);
    }

    @Test
    public void testGetValue() {
        EarthquakePolicyType_Strategy strategy = new EarthquakePolicyType_Strategy();

        HouseModel model = createHouseModel(10);
        BigDecimal expected = BigDecimal.valueOf(10);

        BigDecimal actual = strategy.getValue(model);

        assertEquals(expected, actual);
    }

    @Test
    public void testCalculate() {
        EarthquakePolicyType_Strategy strategy = new EarthquakePolicyType_Strategy();
        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);

        when(weights.getWeight()).thenReturn(BigDecimal.valueOf(2));

        HouseModel model = createHouseModel(10);
        BigDecimal expected = BigDecimal.valueOf(20);

        BigDecimal actual = strategy.calculate(model, weights);

        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateWithZeroWeight() {
        EarthquakePolicyType_Strategy strategy = new EarthquakePolicyType_Strategy();
        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);

        when(weights.getWeight()).thenReturn(BigDecimal.ZERO);

        HouseModel model = createHouseModel(10);
        BigDecimal expected = BigDecimal.ZERO;

        BigDecimal actual = strategy.calculate(model, weights);

        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateWithNegativeWeight() {
        EarthquakePolicyType_Strategy strategy = new EarthquakePolicyType_Strategy();
        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);

        when(weights.getWeight()).thenReturn(BigDecimal.valueOf(-2));

        HouseModel model = createHouseModel(10);
        BigDecimal expected = BigDecimal.valueOf(-20);

        BigDecimal actual = strategy.calculate(model, weights);

        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateWithHighCoverageCode() {
        EarthquakePolicyType_Strategy strategy = new EarthquakePolicyType_Strategy();
        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);

        when(weights.getWeight()).thenReturn(BigDecimal.valueOf(2));

        HouseModel model = createHouseModel(1000);
        BigDecimal expected = BigDecimal.valueOf(2000);

        BigDecimal actual = strategy.calculate(model, weights);

        assertEquals(expected, actual);
    }
}