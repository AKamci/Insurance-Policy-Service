package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ConstructionStyle_StrategyTest {

    @Test
    public void testGetValue() {
        // Arrange
        ConstructionStyle_Strategy strategy = new ConstructionStyle_Strategy();
        HouseModel mockHouseModel = mock(HouseModel.class);

        // Act
        BigDecimal result = strategy.getValue(mockHouseModel);

        // Assert
        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    public void testCalculate() {
        // Arrange
        ConstructionStyle_Strategy strategy = new ConstructionStyle_Strategy();
        HouseModel mockHouseModel = mock(HouseModel.class);
        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);
        BigDecimal weight = new BigDecimal("2");
        BigDecimal expected = new BigDecimal("2");

        Mockito.when(mockWeights.getWeight()).thenReturn(weight);

        // Act
        BigDecimal result = strategy.calculate(mockHouseModel, mockWeights);

        // Assert
        assertTrue(expected.compareTo(result) == 0  );
    }

    @Test
    public void testCalculateWithDifferentWeight() {
        // Arrange
        ConstructionStyle_Strategy strategy = new ConstructionStyle_Strategy();
        HouseModel mockHouseModel = mock(HouseModel.class);
        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);
        BigDecimal weight = new BigDecimal("3");
        BigDecimal expected = new BigDecimal("3");

        Mockito.when(mockWeights.getWeight()).thenReturn(weight);

        // Act
        BigDecimal result = strategy.calculate(mockHouseModel, mockWeights);

        // Assert
        assertTrue(expected.compareTo(result) == 0  );
    }

    @Test
    public void testCalculateWithZeroWeight() {
        // Arrange
        ConstructionStyle_Strategy strategy = new ConstructionStyle_Strategy();
        HouseModel mockHouseModel = mock(HouseModel.class);
        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);
        BigDecimal weight = BigDecimal.ZERO;
        BigDecimal expected = BigDecimal.ZERO;

        Mockito.when(mockWeights.getWeight()).thenReturn(weight);

        // Act
        BigDecimal result = strategy.calculate(mockHouseModel, mockWeights);

        // Assert
        assertTrue(expected.compareTo(result) == 0  );
    }

    @Test
    public void testCalculateWithNullWeight() {
        // Arrange
        ConstructionStyle_Strategy strategy = new ConstructionStyle_Strategy();
        HouseModel mockHouseModel = mock(HouseModel.class);
        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);

        Mockito.when(mockWeights.getWeight()).thenReturn(null);

        // Act & Assert
        try {
            strategy.calculate(mockHouseModel, mockWeights);
        } catch (NullPointerException e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }
}