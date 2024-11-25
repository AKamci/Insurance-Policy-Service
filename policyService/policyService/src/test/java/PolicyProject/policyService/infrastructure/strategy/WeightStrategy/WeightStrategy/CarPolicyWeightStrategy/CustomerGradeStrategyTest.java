package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.junit.jupiter.api.Test;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;

import java.math.BigDecimal;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerGradeStrategyTest {

    @Test
    void testGetValueWithHighGrade() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setGrade(5);
        LicensePlateModel model = createModel(customer);

        CustomerGradeStrategy strategy = new CustomerGradeStrategy();
        BigDecimal value = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(5), value);
    }

    @Test
    void testGetValueWithLowGrade() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setGrade(1);
        LicensePlateModel model = createModel(customer);

        CustomerGradeStrategy strategy = new CustomerGradeStrategy();
        BigDecimal value = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(1), value);
    }

    @Test
    void testGetValueWithZeroGrade() {
        Customer customer = new Customer();
        customer.setName("Bob Smith");
        customer.setGrade(0);
        LicensePlateModel model = createModel(customer);

        CustomerGradeStrategy strategy = new CustomerGradeStrategy();
        BigDecimal value = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(0), value);
    }

    private LicensePlateModel createModel(Customer customer) {
        return new LicensePlateModel(
                1L, "ABC123", Car.builder().make("Tesla").model("Model S").year(2020).build(),
                customer, 100, LocalDate.now(), LocalDate.now().plusYears(1),
                10000L);
    }

    @Test
    void testCalculateWithHighGrade() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setGrade(5);
        LicensePlateModel model = createModel(customer);

        Weights weights = new Weights();
        weights.setWeight(BigDecimal.valueOf(2));

        CustomerGradeStrategy strategy = new CustomerGradeStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.valueOf(10), result);
    }

    @Test
    void testCalculateWithLowGrade() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setGrade(1);
        LicensePlateModel model = createModel(customer);

        Weights weights = new Weights();
        weights.setWeight(BigDecimal.valueOf(2));

        CustomerGradeStrategy strategy = new CustomerGradeStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    void testCalculateWithZeroGrade() {
        Customer customer = new Customer();
        customer.setName("Bob Smith");
        customer.setGrade(0);
        LicensePlateModel model = createModel(customer);

        Weights weights = new Weights();
        weights.setWeight(BigDecimal.valueOf(2));

        CustomerGradeStrategy strategy = new CustomerGradeStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.valueOf(0), result);
    }
}