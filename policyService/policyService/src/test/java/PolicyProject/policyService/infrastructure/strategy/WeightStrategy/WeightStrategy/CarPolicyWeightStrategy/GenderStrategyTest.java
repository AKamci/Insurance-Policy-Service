package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.ICarPolicyWeightStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GenderStrategyTest {

    @Test
    void testCalculateWithDifferentWeights() {
        // Arrange
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Weights parameter = Mockito.mock(Weights.class);
        BigDecimal expectedWeight = BigDecimal.valueOf(75);

        when(parameter.getWeight()).thenReturn(expectedWeight);

        // Act
        BigDecimal result = genderStrategy.calculate(model, parameter);

        // Assert
        assertEquals(expectedWeight, result);
    }

    @Test
    void testCalculateWithZeroWeight() {
        // Arrange
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Weights parameter = Mockito.mock(Weights.class);
        BigDecimal expectedWeight = BigDecimal.ZERO;

        when(parameter.getWeight()).thenReturn(expectedWeight);

        // Act
        BigDecimal result = genderStrategy.calculate(model, parameter);

        // Assert
        assertEquals(expectedWeight, result);
    }

    @Test
    void testCalculateWithNullWeight() {
        // Arrange
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Weights parameter = Mockito.mock(Weights.class);

        when(parameter.getWeight()).thenReturn(null);

        // Act
        BigDecimal result = genderStrategy.calculate(model, parameter);

        // Assert
        assertEquals(BigDecimal.ZERO, result); // Assuming the strategy returns zero in case of null weight
    }

    private GenderStrategy genderStrategy = new GenderStrategy();

    @Test
    void testCalculateMethod() {
        // Arrange
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Weights parameter = Mockito.mock(Weights.class);
        BigDecimal expectedWeight = BigDecimal.valueOf(50);

        when(parameter.getWeight()).thenReturn(expectedWeight);

        // Act
        BigDecimal result = genderStrategy.calculate(model, parameter);

        // Assert
        assertEquals(expectedWeight, result);
    }

    @Test
    void testGetValueMethodWithMaleGender() {
        // Arrange
        LicensePlateModel model = new LicensePlateModel(
                1L,
                "ABC1234",
                Mockito.mock(Car.class),
                Mockito.mock(Customer.class),
                100,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                200L
        );
        BigDecimal expectedGenderValue = BigDecimal.valueOf(1); // Assuming Male is mapped to 1

        // Act
        BigDecimal result = genderStrategy.getValue(model);

        // Assert
        assertEquals(expectedGenderValue, result);
    }

    @Test
    void testGetValueMethodWithFemaleGender() {
        // Arrange
        LicensePlateModel model = new LicensePlateModel(
                2L,
                "XYZ5678",
                Mockito.mock(Car.class),
                Mockito.mock(Customer.class),
                200,
                LocalDate.now(),
                LocalDate.now().plusYears(2),
                500L
        );
        BigDecimal expectedGenderValue = BigDecimal.valueOf(2); // Assuming Female is mapped to 2

        // Act
        BigDecimal result = genderStrategy.getValue(model);

        // Assert
        assertEquals(expectedGenderValue, result);
    }
}