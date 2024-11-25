package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.HealthPolicyBooleanStrategyType;
import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.HealthPolicyStrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HealthPolicyWeightStrategyFactoryTest {

    @Mock
    private HealthPolicyWeightStrategyFactory factory;

    @BeforeEach
    void setUp() {
        factory = new HealthPolicyWeightStrategyFactory();
    }

    @Test
    void testGetStrategyForAge() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.AGE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyCustomerAge_Strategy);
    }

    @Test
    void testGetStrategyForWeight() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.WEIGHT.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyCustomerWeight_Strategy);
    }

    @Test
    void testGetStrategyForHeight() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.HEIGHT.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyCustomerHeight_Strategy);
    }

    @Test
    void testGetStrategyForBMI() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.BMI.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyCustomerBMI_Strategy);
    }

    @Test
    void testGetStrategyForBloodType() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.BLOODTYPE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyCustomerBloodType_Strategy);
    }

    @Test
    void testGetStrategyForGender() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.GENDER.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyCustomerGender_Strategy);
    }

    @Test
    void testGetStrategyForPolicyType() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.POLICY_TYPE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyType_Strategy);
    }

    @Test
    void testGetStrategyForConstant() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.CONSTANT.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealthPolicyConstant_Strategy);
    }

    @Test
    void testGetStrategyForBooleanType() {
        IWeightStrategy strategy = factory.getStrategy(HealthPolicyStrategyType.BOOLEAN_TYPE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof HealtPolicyBoolen_Strategy);
    }

    @Test
    void testGetStrategyForInvalidType() {
        IWeightStrategy strategy = factory.getStrategy("INVALID_TYPE");
        assertNull(strategy);
    }

    @Test
    void testGetStrategyForNullType() {
        IWeightStrategy strategy = factory.getStrategy(null);
        assertNull(strategy);
    }

}
