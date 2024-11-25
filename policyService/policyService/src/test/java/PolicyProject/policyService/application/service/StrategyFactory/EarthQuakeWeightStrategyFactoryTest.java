package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.EarthquakePolicyEnum.EarthquakePolicyStrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.*;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



@Service
public class EarthQuakeWeightStrategyFactoryTest {

    private EarthQuakeWeightStrategyFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new EarthQuakeWeightStrategyFactory();
    }

    @Test
    public void testGetStrategy_SquareMeters() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.SQUARE_METERS.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof SquareMeter_Strategy);
    }

    @Test
    public void testGetStrategy_EarthquakeRisk() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.EARTHQUAKERISK.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof EarthQuakeRisk_Strategy);
    }

    @Test
    public void testGetStrategy_ConstructionStyle() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.CONSTRUCTION_STYLE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof ConstructionStyle_Strategy);
    }

    @Test
    public void testGetStrategy_TotalFloors() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.TOTAL_FLOORS.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof TotalFloors_Strategy);
    }

    @Test
    public void testGetStrategy_ConstructionYear() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.CONSTRUCTION_YEAR.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof ConstructionYear_Strategy);
    }

    @Test
    public void testGetStrategy_Constant() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.CONSTANT.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof EarthQuakeConstantStrategy);
    }

    @Test
    public void testGetStrategy_PolicyType() {
        IWeightStrategy strategy = factory.getStrategy(EarthquakePolicyStrategyType.POLICY_TYPE.name());
        assertNotNull(strategy);
        assertTrue(strategy instanceof EarthquakePolicyType_Strategy);
    }

    @Test
    public void testGetStrategy_UnknownType() {
        IWeightStrategy strategy = factory.getStrategy("UNKNOWN_TYPE");
        assertNull(strategy);
    }


}
