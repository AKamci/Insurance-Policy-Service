package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.config.Specifications.CarPolicySpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CarPolicySpecificationBuildTest {

    @Mock
    private Coverage coverage;

    @Mock
    private PolicyState policyState;


    @InjectMocks
    private CarPolicySpecificationBuild carPolicySpecificationBuild;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCarPolicyBuild_withValidData() {
        // Arrange
        CarPolicy carPolicy = new CarPolicy();
        carPolicy.setPolicyDescription("Full Coverage");
        carPolicy.setCoverage(coverage);
        carPolicy.setState(policyState);
        carPolicy.setPolicyStartDate(LocalDate.parse("2023-01-01"));
        carPolicy.setPolicyEndDate(LocalDate.parse("2024-01-01"));
        carPolicy.setPolicyAmount(5000.0);
        String tckn = "12345678901";
        String plate = "34ABC123";

        Specification<CarPolicy> expectedSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        try (MockedStatic<CarPolicySpecification> mockedStatic = mockStatic(CarPolicySpecification.class)) {
            // Stub the static method
            when(CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            )).thenReturn(expectedSpecification);

            // Act
            Specification<CarPolicy> result = carPolicySpecificationBuild.CarPolicyBuild(carPolicy, tckn, plate);

            // Assert
            assertNotNull(result, "Specification should not be null");
            mockedStatic.verify(() -> CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            ), times(1));
        }
    }

    @Test
    public void testCarPolicyBuild_withNullPolicy() {
        // Arrange
        CarPolicy carPolicy = null;
        String tckn = "12345678901";
        String plate = "34ABC123";

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            carPolicySpecificationBuild.CarPolicyBuild(carPolicy, tckn, plate);
        }, "Expected CarPolicyBuild to throw, but it didn't");
    }

    @Test
    public void testCarPolicyBuild_withEmptyStrings() {
        // Arrange
        CarPolicy carPolicy = new CarPolicy();
        carPolicy.setPolicyDescription(null);
        carPolicy.setCoverage(null);
        carPolicy.setState(null);
        carPolicy.setPolicyStartDate(null);
        carPolicy.setPolicyEndDate(null);
        carPolicy.setPolicyAmount((double) 0);
        String tckn = null;
        String plate = null;

        Specification<CarPolicy> expectedSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        try (MockedStatic<CarPolicySpecification> mockedStatic = mockStatic(CarPolicySpecification.class)) {
            // Stub the static method
            when(CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            )).thenReturn(expectedSpecification);

            // Act
            Specification<CarPolicy> result = carPolicySpecificationBuild.CarPolicyBuild(carPolicy, tckn, plate);

            // Assert
            assertNotNull(result, "Specification should not be null");
            mockedStatic.verify(() -> CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            ), times(1));
        }
    }

    @Test
    public void testCarPolicyBuild_withNullTckn() {
        // Arrange
        CarPolicy carPolicy = new CarPolicy();
        carPolicy.setPolicyDescription("Full Coverage");
        carPolicy.setCoverage(coverage);
        carPolicy.setState(policyState);
        carPolicy.setPolicyStartDate(LocalDate.parse("2023-01-01"));
        carPolicy.setPolicyEndDate(LocalDate.parse("2024-01-01"));
        carPolicy.setPolicyAmount(5000.0);
        String tckn = null;
        String plate = "34ABC123";

        Specification<CarPolicy> expectedSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        try (MockedStatic<CarPolicySpecification> mockedStatic = mockStatic(CarPolicySpecification.class)) {
            // Stub the static method
            when(CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            )).thenReturn(expectedSpecification);

            // Act
            Specification<CarPolicy> result = carPolicySpecificationBuild.CarPolicyBuild(carPolicy, tckn, plate);

            // Assert
            assertNotNull(result, "Specification should not be null");
            mockedStatic.verify(() -> CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            ), times(1));
        }
    }

    @Test
    public void testCarPolicyBuild_withSpecialCharacters() {
        // Arrange
        CarPolicy carPolicy = new CarPolicy();
        carPolicy.setPolicyDescription("Full Coverage@!");
        carPolicy.setCoverage(coverage);
        carPolicy.setState(policyState);
        carPolicy.setPolicyStartDate(LocalDate.parse("2023-01-01"));
        carPolicy.setPolicyEndDate(LocalDate.parse("2024-01-01"));
        carPolicy.setPolicyAmount(5000.0);
        String tckn = "12345@@@";
        String plate = "34ABC###";

        Specification<CarPolicy> expectedSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        try (MockedStatic<CarPolicySpecification> mockedStatic = mockStatic(CarPolicySpecification.class)) {
            // Stub the static method
            when(CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            )).thenReturn(expectedSpecification);

            // Act
            Specification<CarPolicy> result = carPolicySpecificationBuild.CarPolicyBuild(carPolicy, tckn, plate);

            // Assert
            assertNotNull(result, "Specification should not be null");
            mockedStatic.verify(() -> CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            ), times(1));
        }
    }

    @Test
    public void testCarPolicyBuild_withInvalidAmount() {
        // Arrange
        CarPolicy carPolicy = new CarPolicy();
        carPolicy.setPolicyDescription("Full Coverage");
        carPolicy.setCoverage(coverage);
        carPolicy.setState(policyState);
        carPolicy.setPolicyStartDate(LocalDate.parse("2023-01-01"));
        carPolicy.setPolicyEndDate(LocalDate.parse("2024-01-01"));
        // Negative amount to simulate an error case
        carPolicy.setPolicyAmount(-5000.0);
        String tckn = "12345678901";
        String plate = "34ABC123";

        Specification<CarPolicy> expectedSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        try (MockedStatic<CarPolicySpecification> mockedStatic = mockStatic(CarPolicySpecification.class)) {
            // Stub the static method
            when(CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            )).thenReturn(expectedSpecification);

            // Act
            Specification<CarPolicy> result = carPolicySpecificationBuild.CarPolicyBuild(carPolicy, tckn, plate);

            // Assert
            assertNotNull(result, "Specification should not be null");
            mockedStatic.verify(() -> CarPolicySpecification.build(
                    eq(carPolicy.getPolicyDescription()),
                    eq(carPolicy.getCoverage()),
                    eq(carPolicy.getState()),
                    eq(carPolicy.getPolicyStartDate()),
                    eq(carPolicy.getPolicyEndDate()),
                    eq(carPolicy.getPolicyAmount()),
                    eq(plate),
                    eq(tckn)
            ), times(1));
        }
    }
}