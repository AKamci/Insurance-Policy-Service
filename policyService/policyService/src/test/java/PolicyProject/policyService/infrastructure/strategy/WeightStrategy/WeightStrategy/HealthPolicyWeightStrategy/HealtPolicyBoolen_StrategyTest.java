package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mockito.*;

public class HealtPolicyBoolen_StrategyTest {

    @Test
    public void testGetValue_ReturnsZero() {
        // Arrange
        PersonalHealthModel personalHealthModel = Mockito.mock(PersonalHealthModel.class);
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getValue(personalHealthModel);

        // Assert
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testCalculate_WithAlcoholKey() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("ALCOHOL");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_WithSmokeKey() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("SMOKE");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_WithPregnantKey() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("PREGNANT");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_WithDisabilityKey() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("DISABILITY");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_WithSurgeriesKey() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("SURGERIES");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_GetAlcoholConsumption() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("ALCOHOL");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_GetSmokeConsumption() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("SMOKE");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_GetIsPregnant() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("PREGNANT");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_GetHasDisability() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("DISABILITY");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testCalculate_GetHasPreviousSurgeries() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getKey()).thenReturn("SURGERIES");
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.calculate(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testGetSmokeConsumption_WhenSmokeConsumptionIsTrue() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getSmokeConsumption(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testGetSmokeConsumption_WhenSmokeConsumptionIsFalse() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, false, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getSmokeConsumption(personalHealthModel, healthPolicyWeight);

        // Assert
        assertTrue(BigDecimal.ZERO.compareTo(result) == 0);
    }

    @Test
    public void testGetSmokeConsumption_WhenWeightIsZero() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(BigDecimal.ZERO);
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getSmokeConsumption(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testGetIsPregnant_WhenIsPregnantIsTrue() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getIsPregnant(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testGetIsPregnant_WhenIsPregnantIsFalse() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, false, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getIsPregnant(personalHealthModel, healthPolicyWeight);

        // Assert
        assertTrue(BigDecimal.ZERO.compareTo(result) == 0);
    }

    @Test
    public void testGetIsPregnant_WhenWeightIsZero() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(BigDecimal.ZERO);
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getIsPregnant(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testGetHasPreviousSurgeries_WhenHasPreviousSurgeriesIsTrue() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getHasPreviousSurgeries(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testGetHasPreviousSurgeries_WhenHasPreviousSurgeriesIsFalse() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, false, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getHasPreviousSurgeries(personalHealthModel, healthPolicyWeight);

        // Assert
        assertTrue(BigDecimal.ZERO.compareTo(result) == 0);
    }

    @Test
    public void testGetHasPreviousSurgeries_WhenWeightIsZero() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(BigDecimal.ZERO);
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getHasPreviousSurgeries(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testGetHasDisability_WhenHasDisabilityIsTrue() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getHasDisability(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    public void testGetHasDisability_WhenHasDisabilityIsFalse() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, false, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(new BigDecimal("1.5"));
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getHasDisability(personalHealthModel, healthPolicyWeight);

        // Assert
        assertTrue(BigDecimal.ZERO.compareTo(result) == 0);
    }

    @Test
    public void testGetHasDisability_WhenWeightIsZero() {
        // Arrange
        PersonalHealthModel personalHealthModel = new PersonalHealthModel(
                1L, "12345678901", null, 123, null, 180, 75.0, 23.15,
                null, true, true, true, true, true, 1000L
        );
        HealthPolicyWeight healthPolicyWeight = Mockito.mock(HealthPolicyWeight.class);
        Mockito.when(healthPolicyWeight.getWeight()).thenReturn(BigDecimal.ZERO);
        HealtPolicyBoolen_Strategy strategy = new HealtPolicyBoolen_Strategy();

        // Act
        BigDecimal result = strategy.getHasDisability(personalHealthModel, healthPolicyWeight);

        // Assert
        assertEquals(BigDecimal.ZERO, result);
    }


}
