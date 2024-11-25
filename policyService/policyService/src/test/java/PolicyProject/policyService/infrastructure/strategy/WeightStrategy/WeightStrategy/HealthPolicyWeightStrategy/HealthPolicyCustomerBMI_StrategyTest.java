package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;

public class HealthPolicyCustomerBMI_StrategyTest {

    @Test
    void testGetValue_withNormalBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                1L, "12345678901", null, null, null, 180, 70.0, 21.6, null, false, false, false, false, false,null
        );

        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(21.6), result);
    }

    @Test
    void testGetValue_withUnderweightBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                2L, "09876543210", null, null, null, 175, 50.0, 16.3, null, false, false, false, false, false,null
        );

        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(16.3), result);
    }

    @Test
    void testGetValue_withOverweightBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                3L, "11223344556", null, null, null, 160, 80.0, 31.2, null, false, false, false, false, false,null
        );

        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(31.2), result);
    }

    @Test
    void testGetValue_withObeseBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                4L, "66778899001", null, null, null, 170, 100.0, 34.6, null, false, false, false, false, false,null
        );

        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(34.6), result);
    }

    @Test
    void testGetValue_withEdgeCaseBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                5L, "33445566778", null, null, null, 165, 65.0, 24.0, null, false, false, false, false, false,null
        );

        BigDecimal result = strategy.getValue(model);

        assertEquals(BigDecimal.valueOf(24.0), result);
    }

    @Test
    void testCalculate_withNormalBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                1L, "12345678901", null, null, null, 180, 70.0, 21.6, null, false, false, false, false, false,null
        );
        HealthPolicyWeight weight = HealthPolicyWeight.builder().weight(BigDecimal.valueOf(10)).build();

        BigDecimal result = strategy.calculate(model, weight);

        assertEquals(BigDecimal.valueOf(216.0), result);
    }

    @Test
    void testCalculate_withUnderweightBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                2L, "09876543210", null, null, null, 175, 50.0, 16.3, null, false, false, false, false, false,null
        );
        HealthPolicyWeight weight = HealthPolicyWeight.builder().weight(BigDecimal.valueOf(10)).build();

        BigDecimal result = strategy.calculate(model, weight);

        assertEquals(BigDecimal.valueOf(163.0), result);
    }

    @Test
    void testCalculate_withOverweightBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                3L, "11223344556", null, null, null, 160, 80.0, 31.2, null, false, false, false, false, false,null
        );
        HealthPolicyWeight weight = HealthPolicyWeight.builder().weight(BigDecimal.valueOf(10)).build();

        BigDecimal result = strategy.calculate(model, weight);

        assertEquals(BigDecimal.valueOf(312.0), result);
    }

    @Test
    void testCalculate_withObeseBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                4L, "66778899001", null, null, null, 170, 100.0, 34.6, null, false, false, false, false, false,null
        );
        HealthPolicyWeight weight = HealthPolicyWeight.builder().weight(BigDecimal.valueOf(10)).build();

        BigDecimal result = strategy.calculate(model, weight);

        assertEquals(BigDecimal.valueOf(346.0), result);
    }

    @Test
    void testCalculate_withEdgeCaseBMI() {
        HealthPolicyCustomerBMI_Strategy strategy = new HealthPolicyCustomerBMI_Strategy();
        PersonalHealthModel model = new PersonalHealthModel(
                5L, "33445566778", null, null, null, 165, 65.0, 24.0, null, false, false, false, false, false,null
        );
        HealthPolicyWeight weight = HealthPolicyWeight.builder().weight(BigDecimal.valueOf(10)).build();

        BigDecimal result = strategy.calculate(model, weight);

        assertEquals(BigDecimal.valueOf(240.0), result);
    }
}