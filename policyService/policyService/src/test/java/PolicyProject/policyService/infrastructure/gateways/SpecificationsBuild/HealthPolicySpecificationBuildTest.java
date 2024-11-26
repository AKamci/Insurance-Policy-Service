package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.config.Specifications.HealthPolicySpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class HealthPolicySpecificationBuildTest {

    @Mock
    private Coverage coverage;

    @Mock
    BloodType bloodType;

    @Mock
    private PolicyState policyState;

    @InjectMocks
    private HealthPolicySpecificationBuild healthPolicySpecificationBuild;

    @Test
    void testHealthPolicyBuildWithValidInput() {
        try (MockedStatic<HealthPolicySpecification> mockedStatic = mockStatic(HealthPolicySpecification.class)) {
            HealthPolicy healthPolicy = HealthPolicy.builder()
                    .personalHealth(PersonalHealth.builder()
                            .height(180)
                            .weight(75.0)
                            .bmi(23.1)
                            .alcoholConsumption(true)
                            .smokeConsumption(true)
                            .bloodType(bloodType)
                            .isPregnant(false)
                            .hasDisability(false)
                            .hasPreviousSurgeries(false)
                            .build())
                    .coverage(coverage)
                    .state(policyState)
                    .build();

            String tckn = "12345678901";

            Specification<HealthPolicy> mockSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            mockedStatic.when(() -> HealthPolicySpecification.build(
                    healthPolicy.getPersonalHealth().getHeight(),
                    healthPolicy.getPersonalHealth().getWeight(),
                    healthPolicy.getPersonalHealth().getBmi(),
                    healthPolicy.getPersonalHealth().getAlcoholConsumption(),
                    healthPolicy.getPersonalHealth().getSmokeConsumption(),
                    healthPolicy.getPersonalHealth().getBloodType(),
                    healthPolicy.getPersonalHealth().getIsPregnant(),
                    healthPolicy.getPersonalHealth().getHasDisability(),
                    healthPolicy.getPersonalHealth().getHasPreviousSurgeries(),
                    tckn,
                    healthPolicy.getCoverage(),
                    healthPolicy.getState())).thenReturn(mockSpecification);

            Specification<HealthPolicy> result = healthPolicySpecificationBuild.HealthPolicyBuild(healthPolicy, tckn);

            assertNotNull(result, "The result specification should not be null");
        }
    }
}
