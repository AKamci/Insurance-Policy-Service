package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class CustomerAgeStrategyTest {

    @Test
    void testCalculate_weightIsZero() {
        // Arrange
        CustomerAgeStrategy customerAgeStrategy = new CustomerAgeStrategy();
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        LicensePlateModel model = mock(LicensePlateModel.class);
        Customer customer = mock(Customer.class);
        Weights parameter = mock(Weights.class);
        BigDecimal weight = BigDecimal.ZERO;

        when(model.customer()).thenReturn(customer);
        when(customer.getBirthDay()).thenReturn(birthday);
        when(parameter.getWeight()).thenReturn(weight);

        // Act
        BigDecimal result = customerAgeStrategy.calculate(model, parameter);
        BigDecimal expectedValue = BigDecimal.ZERO;

        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testCalculate_valueIsZero() {
        // Arrange
        CustomerAgeStrategy customerAgeStrategy = new CustomerAgeStrategy();
        LicensePlateModel model = mock(LicensePlateModel.class);
        Customer customer = mock(Customer.class);
        Weights parameter = mock(Weights.class);
        BigDecimal weight = BigDecimal.valueOf(2);

        when(model.customer()).thenReturn(customer);
        when(customer.getBirthDay()).thenReturn(null);
        when(parameter.getWeight()).thenReturn(weight);

        // Act
        BigDecimal result = customerAgeStrategy.calculate(model, parameter);
        BigDecimal expectedValue = BigDecimal.ZERO;

        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testGetValue_customerAgeIsCalculatedCorrectly() {
        // Arrange
        CustomerAgeStrategy customerAgeStrategy = new CustomerAgeStrategy();
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        LicensePlateModel model = mock(LicensePlateModel.class);
        Customer customer = mock(Customer.class);

        when(model.customer()).thenReturn(customer);
        when(customer.getBirthDay()).thenReturn(birthday);

        // Act
        BigDecimal result = customerAgeStrategy.getValue(model);
        BigDecimal expectedAge = BigDecimal.valueOf(Period.between(birthday, LocalDate.now()).getYears());

        // Assert
        assertEquals(expectedAge, result);
    }

    @Test
    void testGetValue_customerBirthdayIsNull() {
        // Arrange
        CustomerAgeStrategy customerAgeStrategy = new CustomerAgeStrategy();
        LicensePlateModel model = mock(LicensePlateModel.class);
        Customer customer = mock(Customer.class);

        when(model.customer()).thenReturn(customer);
        when(customer.getBirthDay()).thenReturn(null);

        // Act
        BigDecimal result = customerAgeStrategy.getValue(model);
        BigDecimal expectedAge = BigDecimal.ZERO;

        // Assert
        assertEquals(expectedAge, result);
    }

    @Test
    void testCalculate_weightedValueIsCorrect() {
        // Arrange
        CustomerAgeStrategy customerAgeStrategy = new CustomerAgeStrategy();
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        LicensePlateModel model = mock(LicensePlateModel.class);
        Customer customer = mock(Customer.class);
        Weights parameter = mock(Weights.class);
        BigDecimal weight = BigDecimal.valueOf(2);

        when(model.customer()).thenReturn(customer);
        when(customer.getBirthDay()).thenReturn(birthday);
        when(parameter.getWeight()).thenReturn(weight);

        // Act
        BigDecimal result = customerAgeStrategy.calculate(model, parameter);
        BigDecimal expectedValue = weight.multiply(BigDecimal.valueOf(Period.between(birthday, LocalDate.now()).getYears()));

        // Assert
        assertEquals(expectedValue, result);
    }
}