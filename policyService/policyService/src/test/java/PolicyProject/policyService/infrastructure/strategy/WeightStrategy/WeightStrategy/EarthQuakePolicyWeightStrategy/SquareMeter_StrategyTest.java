package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import org.junit.jupiter.api.Test;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.SquareMeter_Strategy;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;

import java.math.BigDecimal;

public class SquareMeter_StrategyTest {

    @Test
    public void testGetValue_positiveSquareMeters() {
        HouseModel model = mock(HouseModel.class);
        when(model.squareMeters()).thenReturn(100);

        SquareMeter_Strategy strategy = new SquareMeter_Strategy();

        assertEquals(BigDecimal.valueOf(100), strategy.getValue(model));
    }

    @Test
    public void testGetValue_zeroSquareMeters() {
        HouseModel model = mock(HouseModel.class);
        when(model.squareMeters()).thenReturn(0);

        SquareMeter_Strategy strategy = new SquareMeter_Strategy();

        assertEquals(BigDecimal.valueOf(0), strategy.getValue(model));
    }

    @Test
    public void testGetValue_negativeSquareMeters() {
        HouseModel model = mock(HouseModel.class);
        when(model.squareMeters()).thenReturn(-50);

        SquareMeter_Strategy strategy = new SquareMeter_Strategy();

        assertEquals(BigDecimal.valueOf(-50), strategy.getValue(model));
    }

    @Test
    public void testCalculate_positiveSquareMeters() {
        HouseModel model = mock(HouseModel.class);
        when(model.squareMeters()).thenReturn(100);

        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);
        when(weights.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        SquareMeter_Strategy strategy = new SquareMeter_Strategy();

        assertEquals(BigDecimal.valueOf(150.0), strategy.calculate(model, weights));
    }

    @Test
    public void testCalculate_zeroSquareMeters() {
        HouseModel model = mock(HouseModel.class);
        when(model.squareMeters()).thenReturn(0);

        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);
        when(weights.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        SquareMeter_Strategy strategy = new SquareMeter_Strategy();

        assertEquals(BigDecimal.valueOf(0.0), strategy.calculate(model, weights));
    }

    @Test
    public void testCalculate_negativeSquareMeters() {
        HouseModel model = mock(HouseModel.class);
        when(model.squareMeters()).thenReturn(-50);

        EarthQaukeWeights weights = mock(EarthQaukeWeights.class);
        when(weights.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        SquareMeter_Strategy strategy = new SquareMeter_Strategy();

        assertEquals(BigDecimal.valueOf(-75.0), strategy.calculate(model, weights));
    }
}