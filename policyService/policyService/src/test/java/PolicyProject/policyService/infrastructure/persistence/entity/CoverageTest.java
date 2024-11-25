package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.Enums.Enums.SharedEnum.CoverageType;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.Policies;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoverageTest {

    @Mock
    private Policies policies;

    @Mock
    private Policies policies2;


    @Test
    void testCoverageGettersAndSetters() {
        Coverage coverage = new Coverage();
        coverage.setId(1L);
        coverage.setCoverageDescription("Test Coverage Description");
        assertEquals(1L, coverage.getId());
        assertEquals("Test Coverage Description", coverage.getCoverageDescription());
    }

    @Test
    void testCoverageBuilder() {
        Coverage coverage = Coverage.builder()
                .id(1L)
                .coverageDescription("Builder Coverage Description")
                .build();

        assertEquals(1L, coverage.getId());
        assertEquals("Builder Coverage Description", coverage.getCoverageDescription());
    }

    @Test
    void testCoverageTypeConversion() {
        Coverage coverage = new Coverage();
        coverage.setCoverageType(CoverageType.TAM_KAPSAM);
        assertEquals(CoverageType.TAM_KAPSAM, coverage.getCoverageType());
    }

    @Test
    void testPoliciesListAssociation() {
        Coverage coverage = new Coverage();
        List<Policies> policiesList = new ArrayList<>();

        policiesList.add(policies);
        policiesList.add(policies2);

        coverage.setPoliciesList(policiesList);

        assertEquals(2, coverage.getPoliciesList().size());
        assertEquals("Policy 1", coverage.getPoliciesList().get(0).getPolicyDescription());
        assertEquals("Policy 2", coverage.getPoliciesList().get(1).getPolicyDescription());
    }

    @Test
    void testNoArgsConstructor() {
        Coverage coverage = new Coverage();
        assertNotNull(coverage);
        assertNull(coverage.getId());
        assertNull(coverage.getCoverageDescription());
        assertNull(coverage.getCoverageType());
    }

    @Test
    void testAllArgsConstructor() {
        List<Policies> policies = new ArrayList<>();
        Coverage coverage = new Coverage(1L, "Test Coverage", CoverageType.TAM_KAPSAM, policies);

        assertEquals(1L, coverage.getId());
        assertEquals("Test Coverage", coverage.getCoverageDescription());
        assertEquals(CoverageType.TAM_KAPSAM, coverage.getCoverageType());
        assertEquals(policies, coverage.getPoliciesList());
    }

    @Test
    void testOrphanRemovalCascade() {
        Coverage coverage = new Coverage();
        List<Policies> policiesList = new ArrayList<>();
        policiesList.add(policies);
        coverage.setPoliciesList(policiesList);

        assertEquals(1, coverage.getPoliciesList().size());

        policiesList.remove(policies);
        assertEquals(0, coverage.getPoliciesList().size());
    }

        @Test
        void setId_shouldSetId() {
            Coverage coverage = new Coverage();
            Long expectedId = 1L;

            coverage.setId(expectedId);

            assertEquals(expectedId, coverage.getId());
        }

        @Test
        void setId_shouldHandleNull() {
            Coverage coverage = new Coverage();

            coverage.setId(null);

            assertNull(coverage.getId());
        }

        @Test
        void setId_shouldOverrideExistingId() {
            Coverage coverage = new Coverage();
            Long initialId = 1L;
            Long newId = 2L;

            coverage.setId(initialId);
            coverage.setId(newId);

            assertEquals(newId, coverage.getId());
        }

        @Test
        void setCoverageDescription_shouldSetDescription() {
            Coverage coverage = new Coverage();
            String expectedDescription = "Test Description";

            coverage.setCoverageDescription(expectedDescription);

            assertEquals(expectedDescription, coverage.getCoverageDescription());
        }

        @Test
        void setCoverageDescription_shouldHandleNull() {
            Coverage coverage = new Coverage();

            coverage.setCoverageDescription(null);

            assertNull(coverage.getCoverageDescription());
        }

        @Test
        void setCoverageType_shouldSetType() {
            Coverage coverage = new Coverage();
            CoverageType expectedType = CoverageType.TAM_KAPSAM;

            coverage.setCoverageType(expectedType);

            assertEquals(expectedType, coverage.getCoverageType());
        }

        @Test
        void setCoverageType_shouldHandleNull() {
            Coverage coverage = new Coverage();

            coverage.setCoverageType(null);

            assertNull(coverage.getCoverageType());
        }

        @Test
        void setPoliciesList_shouldSetPolicies() {

            Coverage coverage = new Coverage();
            List<Policies> expectedPoliciesList = Arrays.asList(policies, policies2);

            coverage.setPoliciesList(expectedPoliciesList);

            assertEquals(expectedPoliciesList, coverage.getPoliciesList());
        }

        @Test
        void setPoliciesList_shouldHandleNull() {
            Coverage coverage = new Coverage();

            coverage.setPoliciesList(null);

            assertNull(coverage.getPoliciesList());
        }
}

    
