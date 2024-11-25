package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.ConstantStrategy;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarthQuakeConstantStrategyTest {

    @Test
    void testCalculate_WithZeroWeight() {
        // Arrange
        EarthQuakeConstantStrategy strategy = new EarthQuakeConstantStrategy();
        HouseModel houseModel = new HouseModel(1L, 101, 3, 120, null, null, "12345678901", 100000L);
        EarthQaukeWeights weights = Mockito.mock(EarthQaukeWeights.class);
        BigDecimal weightValue = BigDecimal.ZERO;

        Mockito.when(weights.getWeight()).thenReturn(weightValue);

        // Act
        BigDecimal result = strategy.calculate(houseModel, weights);

        // Assert
        assertEquals(weightValue, result, "The calculate method should return 0 when the weight is zero");
    }

    @Test
    void testCalculate_WithZeroWeight_ConstantStrategy() {
        // Arrange
        ConstantStrategy constantStrategy = new ConstantStrategy();
        LicensePlateModel licensePlateModel = new LicensePlateModel(1L, "ABC123", null, null, 200, LocalDate.now(), LocalDate.now().plusDays(365), 50000L);
        Weights weights = Mockito.mock(Weights.class);
        BigDecimal weightValue = BigDecimal.ZERO;

        Mockito.when(weights.getWeight()).thenReturn(weightValue);

        // Act
        BigDecimal result = constantStrategy.calculate(licensePlateModel, weights);

        // Assert
        assertEquals(weightValue, result, "The calculate method should return 0 when the weight is zero");
    }

    @Test
    void testCalculate_WithPositiveWeight_ConstantStrategy() {
        // Arrange
        ConstantStrategy constantStrategy = new ConstantStrategy();
        LicensePlateModel licensePlateModel = new LicensePlateModel(1L, "ABC123", null, null, 200, LocalDate.now(), LocalDate.now().plusDays(365), 50000L);
        Weights weights = Mockito.mock(Weights.class);
        BigDecimal weightValue = BigDecimal.valueOf(1);

        Mockito.when(weights.getWeight()).thenReturn(weightValue);

        // Act
        BigDecimal result = constantStrategy.calculate(licensePlateModel, weights);

        // Assert
        assertEquals(weightValue, result, "The calculate method should return the positive weight value correctly");
    }

    @Test
    void testCalculate_WithNegativeWeight_ConstantStrategy() {
        // Arrange
        ConstantStrategy constantStrategy = new ConstantStrategy();
        LicensePlateModel licensePlateModel = new LicensePlateModel(1L, "ABC123", null, null, 200, LocalDate.now(), LocalDate.now().plusDays(365), 50000L);
        Weights weights = Mockito.mock(Weights.class);
        BigDecimal weightValue = BigDecimal.valueOf(-1);

        Mockito.when(weights.getWeight()).thenReturn(weightValue);

        // Act
        BigDecimal result = constantStrategy.calculate(licensePlateModel, weights);

        // Assert
        assertEquals(weightValue, result, "The calculate method should return the negative weight value correctly");
    }

    @Test
    void testCalculate_WithNegativeWeight() {
        // Arrange
        EarthQuakeConstantStrategy strategy = new EarthQuakeConstantStrategy();
        HouseModel houseModel = new HouseModel(1L, 101, 3, 120, null, null, "12345678901", 100000L);
        EarthQaukeWeights weights = Mockito.mock(EarthQaukeWeights.class);
        BigDecimal weightValue = BigDecimal.valueOf(-1);

        Mockito.when(weights.getWeight()).thenReturn(weightValue);

        // Act
        BigDecimal result = strategy.calculate(houseModel, weights);

        // Assert
        assertEquals(weightValue, result, "The calculate method should return the negative weight value correctly");
    }

    @Test
    void testGetValue_ReturnsOne() {
        // Arrange
        EarthQuakeConstantStrategy strategy = new EarthQuakeConstantStrategy();
        HouseModel houseModel = new HouseModel(1L, 101, 3, 120, null, null, "12345678901", 100000L);

        // Act
        BigDecimal result = strategy.getValue(houseModel);

        // Assert
        assertEquals(BigDecimal.valueOf(1), result, "The getValue method should return 1");
    }

    @Test
    void testCalculate_WithMockData() {
        // Arrange
        EarthQuakeConstantStrategy strategy = new EarthQuakeConstantStrategy();
        HouseModel houseModel = new HouseModel(1L, 101, 3, 120, null, null, "12345678901", 100000L);
        EarthQaukeWeights weights = Mockito.mock(EarthQaukeWeights.class);
        BigDecimal weightValue = BigDecimal.valueOf(2);

        Mockito.when(weights.getWeight()).thenReturn(weightValue);

        // Act
        BigDecimal result = strategy.calculate(houseModel, weights);

        // Assert
        assertEquals(weightValue, result.multiply(BigDecimal.valueOf(1)), "The calculate method should return the correct value");
    }

    @Test
    void testGetValue_ReturnsOne_WithLicensePlateModel() {
        // Arrange
        ConstantStrategy constantStrategy = new ConstantStrategy();
        LicensePlateModel licensePlateModel = new LicensePlateModel(1L, "ABC123", null, null, 200, LocalDate.now(), LocalDate.now().plusDays(365), 50000L);

        // Act
        BigDecimal result = constantStrategy.getValue(licensePlateModel);

        // Assert
        assertEquals(BigDecimal.valueOf(1), result, "The getValue method should return 1 for any LicensePlateModel");
    }

    @Test
    void testGetValue_ReturnsOne_WithAnotherLicensePlateModel() {
        // Arrange
        ConstantStrategy constantStrategy = new ConstantStrategy();
        LicensePlateModel licensePlateModel = new LicensePlateModel(2L, "XYZ789", null, null, 300, LocalDate.now(), LocalDate.now().plusDays(180), 75000L);

        // Act
        BigDecimal result = constantStrategy.getValue(licensePlateModel);

        // Assert
        assertEquals(BigDecimal.valueOf(1), result, "The getValue method should return 1 for any LicensePlateModel");
    }
}