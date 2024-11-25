package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car.CarBuilder;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CarAgeStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

class CarAgeStrategyTest {

    @Test
    void testGetValue_withValidBirthYear() {
        Car car = Car.builder().make("Toyota").model("Corolla").year(2015).build();
        LicensePlateModel model = new LicensePlateModel(1L, "ABC123", car, null, 100, LocalDate.now(), LocalDate.now().plusYears(1), 200L);

        CarAgeStrategy strategy = new CarAgeStrategy();
        BigDecimal value = strategy.getValue(model);

        int expectedAge = Period.between(LocalDate.of(2015, 1, 1), LocalDate.now()).getYears();
        assertEquals(BigDecimal.valueOf(expectedAge), value);
    }

    @Test
    void testGetValue_withNullBirthYear() {
        Car car = Car.builder().make("Honda").model("Civic").year(2000).build();
        LicensePlateModel model = new LicensePlateModel(2L, "XYZ789", car, null, 150, LocalDate.now(), LocalDate.now().plusYears(1), 300L);

        CarAgeStrategy strategy = new CarAgeStrategy();
        BigDecimal value = strategy.getValue(model);

        assertEquals(BigDecimal.ZERO, value);
    }

    @Test
    void testCalculate_withValidWeight() {
        Car car = Car.builder().make("Toyota").model("Corolla").year(2015).build();
        LicensePlateModel model = new LicensePlateModel(3L, "DEF456", car, null, 100, LocalDate.now(), LocalDate.now().plusYears(1), 200L);
        Weights weights = new Weights();
        weights.setWeight(BigDecimal.valueOf(1.5));

        CarAgeStrategy strategy = new CarAgeStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        int age = Period.between(LocalDate.of(2015, 1, 1), LocalDate.now()).getYears();
        assertEquals(BigDecimal.valueOf(age).multiply(BigDecimal.valueOf(1.5)), result);
    }

    @Test
    void testCalculate_withZeroWeight() {
        Car car = Car.builder().make("Nissan").model("Altima").year(2010).build();
        LicensePlateModel model = new LicensePlateModel(4L, "GHI789", car, null, 150, LocalDate.now(), LocalDate.now().plusYears(1), 300L);
        Weights weights = new Weights();
        weights.setWeight(BigDecimal.ZERO);

        CarAgeStrategy strategy = new CarAgeStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.ZERO, result);
    }
}