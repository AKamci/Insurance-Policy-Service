package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolicyTypeStrategyTest {

    @Test
    public void testCalculate_ShouldReturnWeightBasedOnParameter() {
        LicensePlateModel model = new LicensePlateModel(
                1L,
                "ABC123",
                null,
                null,
                12345,
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                1000L
        );

        Weights parameter = new Weights();
        parameter.setWeight(new BigDecimal("2500"));

        PolicyTypeStrategy strategy = new PolicyTypeStrategy();
        BigDecimal result = strategy.calculate(model, parameter);

        assertEquals(new BigDecimal("2500"), result);
    }

    @Test
    public void testGetValue_ShouldReturnValueBasedOnCoverageCode() {
        LicensePlateModel model = new LicensePlateModel(
                1L,
                "ABC123",
                null,
                null,
                12345,
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                1000L
        );

        PolicyTypeStrategy strategy = new PolicyTypeStrategy();
        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(12345), result);
    }
}