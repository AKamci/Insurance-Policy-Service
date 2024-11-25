package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarPriceStrategyTest {

    @Test
    public void testGetValue_ShouldReturnCorrectValue_WhenModelHasValidPrice() {
        Car car = Car.builder().id(1L).make("BMW").year(2022).model("Sedan").price(50000).build();
        LicensePlateModel model = new LicensePlateModel(1L, "ABC123", car, null, 100, null, null, 1000L);

        CarPriceStrategy strategy = new CarPriceStrategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(50000), result);
    }

    @Test
    public void testGetValue_ShouldReturnZero_WhenModelHasZeroPrice() {
        Car car = Car.builder().id(2L).make("Toyota").year(2021).model("Sedan").price(0).build();
        LicensePlateModel model = new LicensePlateModel(2L, "XYZ456", car, null, 101, null, null, 2000L);

        CarPriceStrategy strategy = new CarPriceStrategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(0), result);
    }

    @Test
    public void testGetValue_ShouldReturnCorrectValue_WhenModelHasBasePrice() {
        Car car = Car.builder().id(3L).make("Audi").year(2020).model("Coupe").price(35000).build();
        LicensePlateModel model = new LicensePlateModel(3L, "LMN789", car, null, 102, null, null, 3000L);

        CarPriceStrategy strategy = new CarPriceStrategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(35000), result);
    }


    @Test
    public void testCalculate_ShouldReturnCorrectCalculatedValue_WhenGivenValidInputs() {
        Car car = Car.builder().id(1L).make("BMW").year(2022).model("Sedan").price(50000).build();
        LicensePlateModel model = new LicensePlateModel(1L, "ABC123", car, null, 100, null, null, 1000L);
        Weights weights = Weights.builder().id(1L).weight(BigDecimal.valueOf(2)).build();

        CarPriceStrategy strategy = new CarPriceStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.valueOf(100000), result);
    }

    @Test
    public void testCalculate_ShouldReturnZero_WhenPriceIsZero() {
        Car car = Car.builder().id(2L).make("Toyota").year(2021).model("Sedan").price(0).build();
        LicensePlateModel model = new LicensePlateModel(2L, "XYZ456", car, null, 101, null, null, 2000L);
        Weights weights = Weights.builder().id(2L).weight(BigDecimal.valueOf(2)).build();

        CarPriceStrategy strategy = new CarPriceStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.valueOf(0), result);
    }

    @Test
    public void testCalculate_ShouldReturnCorrectCalculatedValue_WhenWeightIsOne() {
        Car car = Car.builder().id(3L).make("Audi").year(2020).model("Coupe").price(35000).build();
        LicensePlateModel model = new LicensePlateModel(3L, "LMN789", car, null, 102, null, null, 3000L);
        Weights weights = Weights.builder().id(3L).weight(BigDecimal.valueOf(1)).build();

        CarPriceStrategy strategy = new CarPriceStrategy();
        BigDecimal result = strategy.calculate(model, weights);

        assertEquals(BigDecimal.valueOf(35000), result);
    }
}