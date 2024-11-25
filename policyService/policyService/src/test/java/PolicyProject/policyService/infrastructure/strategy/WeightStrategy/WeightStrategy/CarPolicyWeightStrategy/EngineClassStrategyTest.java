package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineClassStrategyTest {

    private final EngineClassStrategy engineClassStrategy = new EngineClassStrategy();


    @Test
    public void testGetValue() {
        LicensePlateModel[] models = {
                createLicensePlateModel(1L, "ABC123", 1500),
                createLicensePlateModel(2L, "DEF456", 2000),
                createLicensePlateModel(3L, "GHI789", 2500)
        };

        BigDecimal[] expectedResults = {
                BigDecimal.valueOf(1500),
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(2500)
        };

        for (int i = 0; i < models.length; i++) {
            assertEquals(0, expectedResults[i].compareTo(engineClassStrategy.getValue(models[i])));        }
    }

    @Test
    public void testCalculate() {
        LicensePlateModel model1 = createLicensePlateModel(1L, "ABC123", 1500);
        Weights weights1 = createWeights(BigDecimal.valueOf(2.5));

        LicensePlateModel model2 = createLicensePlateModel(2L, "DEF456", 2000);
        Weights weights2 = createWeights(BigDecimal.valueOf(3.0));

        LicensePlateModel model3 = createLicensePlateModel(3L, "GHI789", 2500);
        Weights weights3 = createWeights(BigDecimal.valueOf(1.5));

        assertEquals(0, BigDecimal.valueOf(3750).compareTo(engineClassStrategy.calculate(model1, weights1)));
        assertEquals(0, BigDecimal.valueOf(6000).compareTo(engineClassStrategy.calculate(model2, weights2)));
        assertEquals(0, BigDecimal.valueOf(3750).compareTo(engineClassStrategy.calculate(model3, weights3)));
    }

    private Weights createWeights(BigDecimal weight) {
        Weights weights = new Weights();
        weights.setWeight(weight);
        return weights;
    }

    private LicensePlateModel createLicensePlateModel(Long id, String plate, int engineSize) {
        Car car = Car.builder().engine(engineSize).build();
        return new LicensePlateModel(id, plate, car, null, null, null, null, null);
    }
}