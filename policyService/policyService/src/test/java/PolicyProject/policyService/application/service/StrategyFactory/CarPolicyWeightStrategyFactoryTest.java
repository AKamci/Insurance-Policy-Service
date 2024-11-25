package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEnum.CarPolicyStrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@Service
public class CarPolicyWeightStrategyFactoryTest {

    private CarPolicyWeightStrategyFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new CarPolicyWeightStrategyFactory();
    }

    @Test
    public void testGetStrategy_CustomerAgeStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.CUSTOMER_AGE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof CustomerAgeStrategy);
    }

    @Test
    public void testGetStrategy_CarAgeStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.CAR_AGE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof CarAgeStrategy);
    }

    @Test
    public void testGetStrategy_CarPriceStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.CAR_PRICE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof CarPriceStrategy);
    }

    @Test
    public void testGetStrategy_CarTypeStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.CAR_TYPE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof CarTypeStrategy);
    }

    @Test
    public void testGetStrategy_ConstantStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.CONSTANT.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof ConstantStrategy);
    }

    @Test
    public void testGetStrategy_CustomerGradeStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.CUSTOMER_GRADE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof CustomerGradeStrategy);
    }

    @Test
    public void testGetStrategy_EngineClassStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.ENGINE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof EngineClassStrategy);
    }

    @Test
    public void testGetStrategy_GenderStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.GENDER.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof GenderStrategy);
    }

    @Test
    public void testGetStrategy_PolicyTypeStrategy() {
        IWeightStrategy strategy = factory.getStrategy(CarPolicyStrategyType.POLICY_TYPE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof PolicyTypeStrategy);
    }

    @Test
    public void testGetStrategy_UnknownStrategy() {
        IWeightStrategy strategy = factory.getStrategy("UNKNOWN");
        assertNull(strategy);
    }
}
