package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HealthPolicyCustomerGender_StrategyTest {

    @Test
    public void testCalculate_MaleCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(1); // Assuming 1 represents male

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyWeight mockWeight = mock(HealthPolicyWeight.class);
        when(mockWeight.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.calculate(model, mockWeight);

        // Assert
        assertEquals(BigDecimal.valueOf(1.5), result);
    }

    @Test
    public void testCalculate_FemaleCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(2); // Assuming 2 represents female

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyWeight mockWeight = mock(HealthPolicyWeight.class);
        when(mockWeight.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.calculate(model, mockWeight);

        // Assert
        assertEquals(BigDecimal.valueOf(3.0), result);
    }

    @Test
    public void testCalculate_NonBinaryCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(3); // Assuming 3 represents non-binary

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyWeight mockWeight = mock(HealthPolicyWeight.class);
        when(mockWeight.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.calculate(model, mockWeight);

        // Assert
        assertEquals(BigDecimal.valueOf(4.5), result);
    }

    @Test
    public void testCalculate_UnknownGenderCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(0); // Assuming 0 represents unknown gender

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyWeight mockWeight = mock(HealthPolicyWeight.class);
        when(mockWeight.getWeight()).thenReturn(BigDecimal.valueOf(1.5));

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.calculate(model, mockWeight);

        // Assert
        assertEquals(BigDecimal.valueOf(0.0), result);
    }

    @Test
    public void testGetValue_MaleCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(1); // Assuming 1 represents male

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.getValue(model);

        // Assert
        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    public void testGetValue_FemaleCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(2); // Assuming 2 represents female

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.getValue(model);

        // Assert
        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    public void testGetValue_NonBinaryCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(3); // Assuming 3 represents non-binary

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.getValue(model);

        // Assert
        assertEquals(BigDecimal.valueOf(3), result);
    }

    @Test
    public void testGetValue_UnknownGenderCustomer() {
        // Arrange
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getGender()).thenReturn(0); // Assuming 0 represents unknown gender

        PersonalHealthModel model = mock(PersonalHealthModel.class);
        when(model.customer()).thenReturn(mockCustomer);

        HealthPolicyCustomerGender_Strategy strategy = new HealthPolicyCustomerGender_Strategy();

        // Act
        BigDecimal result = strategy.getValue(model);

        // Assert
        assertEquals(BigDecimal.valueOf(0), result);
    }
}