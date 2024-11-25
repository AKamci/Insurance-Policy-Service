package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;

import java.math.BigDecimal;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalFloors_StrategyTest {

    @Test
    void testGetValue_withValidData_expectCorrectValue() {
        // Arrange
        Building buildingMock = Mockito.mock(Building.class);
        Mockito.when(buildingMock.getTotalFloors()).thenReturn(10);
        HouseModel houseModel = new HouseModel(1L, 100, 1, 100, null, buildingMock, "someTckn", 10000L);
        TotalFloors_Strategy strategy = new TotalFloors_Strategy();

        // Act
        BigDecimal value = strategy.getValue(houseModel);

        // Assert
        assertEquals(BigDecimal.valueOf(10), value);
    }

    @Test
    void testGetValue_withZeroFloors_expectZeroValue() {
        // Arrange
        Building buildingMock = Mockito.mock(Building.class);
        Mockito.when(buildingMock.getTotalFloors()).thenReturn(0);
        HouseModel houseModel = new HouseModel(1L, 100, 1, 100, null, buildingMock, "someTckn", 10000L);
        TotalFloors_Strategy strategy = new TotalFloors_Strategy();

        // Act
        BigDecimal value = strategy.getValue(houseModel);

        // Assert
        assertEquals(BigDecimal.valueOf(0), value);
    }

    @Test
    void testGetValue_withNegativeFloors_expectNegativeValue() {
        // Arrange
        Building buildingMock = Mockito.mock(Building.class);
        Mockito.when(buildingMock.getTotalFloors()).thenReturn(-3);
        HouseModel houseModel = new HouseModel(1L, 100, 1, 100, null, buildingMock, "someTckn", 10000L);
        TotalFloors_Strategy strategy = new TotalFloors_Strategy();

        // Act
        BigDecimal value = strategy.getValue(houseModel);

        // Assert
        assertEquals(BigDecimal.valueOf(-3), value);
    }

    @Test
    void testCalculate_withValidData_expectCorrectValue() {
        // Arrange
        Building buildingMock = Mockito.mock(Building.class);
        Mockito.when(buildingMock.getTotalFloors()).thenReturn(10);
        HouseModel houseModel = new HouseModel(1L, 100, 1, 100, null, buildingMock, "someTckn", 10000L);
        EarthQaukeWeights weightsMock = Mockito.mock(EarthQaukeWeights.class);
        Mockito.when(weightsMock.getWeight()).thenReturn(BigDecimal.valueOf(1.5));
        TotalFloors_Strategy strategy = new TotalFloors_Strategy();

        // Act
        BigDecimal result = strategy.calculate(houseModel, weightsMock);

        // Assert
        assertEquals(15, result.compareTo(BigDecimal.valueOf(15)));

    }

    @Test
    void testCalculate_withZeroFloors_expectZeroValue() {
        // Arrange
        Building buildingMock = Mockito.mock(Building.class);
        Mockito.when(buildingMock.getTotalFloors()).thenReturn(0);
        HouseModel houseModel = new HouseModel(1L, 100, 1, 100, null, buildingMock, "someTckn", 10000L);
        EarthQaukeWeights weightsMock = Mockito.mock(EarthQaukeWeights.class);
        Mockito.when(weightsMock.getWeight()).thenReturn(BigDecimal.valueOf(1.5));
        TotalFloors_Strategy strategy = new TotalFloors_Strategy();

        // Act
        BigDecimal result = strategy.calculate(houseModel, weightsMock);

        // Assert
        assertEquals(BigDecimal.ZERO.setScale(1), result.setScale(1));
    }

    @Test
    void testCalculate_withNegativeFloors_expectNegativeValue() {
        // Arrange
        Building buildingMock = Mockito.mock(Building.class);
        Mockito.when(buildingMock.getTotalFloors()).thenReturn(-3);
        HouseModel houseModel = new HouseModel(1L, 100, 1, 100, null, buildingMock, "someTckn", 10000L);
        EarthQaukeWeights weightsMock = Mockito.mock(EarthQaukeWeights.class);
        Mockito.when(weightsMock.getWeight()).thenReturn(BigDecimal.valueOf(1.5));
        TotalFloors_Strategy strategy = new TotalFloors_Strategy();

        // Act
        BigDecimal result = strategy.calculate(houseModel, weightsMock);

        // Assert
        assertEquals(BigDecimal.valueOf(-4.5), result);
    }
}