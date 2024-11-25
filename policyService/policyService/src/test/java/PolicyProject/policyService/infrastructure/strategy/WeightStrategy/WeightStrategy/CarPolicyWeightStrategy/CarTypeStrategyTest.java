package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.anyInt;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTypeStrategyTest {

    @Test
    public void testGetValue_WithValidCarType() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(5.0);

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(5.0), result);
    }

    @Test
    public void testCalculate_WithValidParameters() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(3.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(2));

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(6.0), result);
    }

    @Test
    public void testGetValue_WithZeroCarType() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(Double.valueOf(Integer.valueOf(0)));

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(0.0), result);
    }

    @Test
    public void testCalculate_WithZeroWeight() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(2.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.ZERO);

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(0.0), result);
    }

    @Test
    public void testCalculate_WithNegativeWeight() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(2.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(-3));

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(-6.0), result);
    }

    @Test
    public void testCalculate_WithNegativeCarType() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(-4.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(2));

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(-8.0), result);
    }

    @Test
    public void testCalculate_WithBothNegative() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(-2.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(-3));

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(6.0), result);
    }

    @Test
    public void testCalculate_WithZeroCarTypeAndPositiveWeight() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(0.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.valueOf(5));

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(0.0), result);
    }

    @Test
    public void testCalculate_WithZeroWeightAndPositiveCarType() {
        LicensePlateModel model = Mockito.mock(LicensePlateModel.class);
        Car car = Mockito.mock(Car.class);
        Mockito.when(model.car()).thenReturn(car);
        Mockito.when(car.getType()).thenReturn(5.0);

        Weights parameter = Mockito.mock(Weights.class);
        Mockito.when(parameter.getWeight()).thenReturn(BigDecimal.ZERO);

        CarTypeStrategy strategy = new CarTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(BigDecimal.valueOf(0.0), result);
    }
}