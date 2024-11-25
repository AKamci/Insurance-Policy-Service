package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ConstructionYear_StrategyTest {

    @Test
    public void testGetValueWhenYearIsPresent() {
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getConstructionYear()).thenReturn(2000);

        HouseModel houseModel = new HouseModel(
                1L, 123, 456, 789, null, mockBuilding, "12345678901", 10000L
        );

        ConstructionYear_Strategy strategy = new ConstructionYear_Strategy();
        BigDecimal result = strategy.getValue(houseModel);

        BigDecimal expectedAge = BigDecimal.valueOf(LocalDate.now().getYear() - 2000);
        assertEquals(expectedAge, result);
    }

    @Test
    public void testGetValueWhenYearIsNull() {
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getConstructionYear()).thenReturn(null);

        HouseModel houseModel = new HouseModel(
                1L, 123, 456, 789, null, mockBuilding, "12345678901", 10000L
        );

        ConstructionYear_Strategy strategy = new ConstructionYear_Strategy();
        BigDecimal result = strategy.getValue(houseModel);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testGetValueWhenYearIsFuture() {
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getConstructionYear()).thenReturn(LocalDate.now().getYear() + 1);

        HouseModel houseModel = new HouseModel(
                1L, 123, 456, 789, null, mockBuilding, "12345678901", 10000L
        );

        ConstructionYear_Strategy strategy = new ConstructionYear_Strategy();
        BigDecimal result = strategy.getValue(houseModel);

        BigDecimal expectedAge = BigDecimal.valueOf(-1);
        assertEquals(expectedAge, result);
    }

    @Test
    public void testCalculateWhenYearIsPresent() {
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getConstructionYear()).thenReturn(2000);

        HouseModel houseModel = new HouseModel(
                1L, 123, 456, 789, null, mockBuilding, "12345678901", 10000L
        );

        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);
        when(mockWeights.getWeight()).thenReturn(BigDecimal.valueOf(10));

        ConstructionYear_Strategy strategy = new ConstructionYear_Strategy();
        BigDecimal result = strategy.calculate(houseModel, mockWeights);

        BigDecimal expectedValue = BigDecimal.valueOf(LocalDate.now().getYear() - 2000).multiply(BigDecimal.valueOf(10));
        assertEquals(expectedValue, result);
    }

    @Test
    public void testCalculateWhenYearIsNull() {
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getConstructionYear()).thenReturn(null);

        HouseModel houseModel = new HouseModel(
                1L, 123, 456, 789, null, mockBuilding, "12345678901", 10000L
        );

        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);
        when(mockWeights.getWeight()).thenReturn(BigDecimal.valueOf(10));

        ConstructionYear_Strategy strategy = new ConstructionYear_Strategy();
        BigDecimal result = strategy.calculate(houseModel, mockWeights);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculateWhenYearIsFuture() {
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getConstructionYear()).thenReturn(LocalDate.now().getYear() + 1);

        HouseModel houseModel = new HouseModel(
                1L, 123, 456, 789, null, mockBuilding, "12345678901", 10000L
        );

        EarthQaukeWeights mockWeights = mock(EarthQaukeWeights.class);
        when(mockWeights.getWeight()).thenReturn(BigDecimal.valueOf(10));

        ConstructionYear_Strategy strategy = new ConstructionYear_Strategy();
        BigDecimal result = strategy.calculate(houseModel, mockWeights);

        BigDecimal expectedValue = BigDecimal.valueOf(-1).multiply(BigDecimal.valueOf(10));
        assertEquals(expectedValue, result);
    }
}