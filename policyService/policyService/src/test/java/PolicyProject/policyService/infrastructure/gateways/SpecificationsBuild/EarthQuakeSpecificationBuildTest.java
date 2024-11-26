package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.config.Specifications.EarthQuakeSpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class EarthQuakeSpecificationBuildTest {

    @Mock
    Coverage coverage;

    @Mock
    PolicyState state;

    @InjectMocks
    private EarthQuakeSpecificationBuild earthQuakeSpecificationBuild;

    @Test
    void testEarthQuakeBuildWithValidInput() {
        try (MockedStatic<EarthQuakeSpecification> mockedStatic = mockStatic(EarthQuakeSpecification.class)) {
            EarthquakePolicy earthquakePolicy = EarthquakePolicy.builder()
                    .house(House.builder()
                            .number(123)
                            .building(Building.builder()
                                    .apartmentNumber(1)
                                    .address(Address.builder()
                                            .city("Test City")
                                            .district("Test District")
                                            .neighborhood("Test Neighborhood")
                                            .build())
                                    .build())
                            .build())
                    .policyDescription("Test Description")
                    .coverage(coverage)
                    .state(state)
                    .policyStartDate(LocalDate.of(2023, 1, 1))
                    .policyEndDate(LocalDate.of(2023, 12, 31))
                    .policyAmount(1000.0)
                    .build();

            String tckn = "12345678901";

            Specification<EarthquakePolicy> mockSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            mockedStatic.when(() -> EarthQuakeSpecification.build(
                    earthquakePolicy.getHouse().getNumber(),
                    earthquakePolicy.getHouse().getBuilding().getApartmentNumber(),
                    earthquakePolicy.getHouse().getBuilding().getAddress().getCity(),
                    earthquakePolicy.getHouse().getBuilding().getAddress().getDistrict(),
                    earthquakePolicy.getHouse().getBuilding().getAddress().getNeighborhood(),
                    earthquakePolicy.getPolicyDescription(),
                    earthquakePolicy.getCoverage(),
                    earthquakePolicy.getState(),
                    earthquakePolicy.getPolicyStartDate(),
                    earthquakePolicy.getPolicyEndDate(),
                    earthquakePolicy.getPolicyAmount(),
                    tckn)).thenReturn(mockSpecification);

            Specification<EarthquakePolicy> result = earthQuakeSpecificationBuild.EarthQuakeBuild(earthquakePolicy, tckn);

            assertNotNull(result, "The result specification should not be null");
        }
    }
}