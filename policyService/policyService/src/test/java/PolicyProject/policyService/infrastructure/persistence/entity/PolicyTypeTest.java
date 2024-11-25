package PolicyProject.policyService.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolicyTypeTest {

    @Test
    void testGetId() {
        // Arrange
        Long expectedId = null;
        PolicyType policyType = new PolicyType();
        policyType.setId(expectedId);

        // Act
        Long actualNullId = policyType.getId();

        // Assert
        assertNull(actualNullId);

        // Arrange
        expectedId = 1L;
        policyType.setId(expectedId);

        // Act
        Long actualId = policyType.getId();

        // Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetName() {
        // Arrange
        String expectedName = null;
        PolicyType policyType = new PolicyType();
        policyType.setName(expectedName);

        // Act
        String actualNullName = policyType.getName();

        // Assert
        assertNull(actualNullName);

        // Arrange
        expectedName = "Test Policy";
        policyType.setName(expectedName);

        // Act
        String actualName = policyType.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Long expectedId = null;
        String expectedName = null;

        // Act
        PolicyType policyType = new PolicyType(expectedId, expectedName);

        // Assert
        assertNull(policyType.getId());
        assertNull(policyType.getName());

        // Arrange
        expectedId = 1L;
        expectedName = "Test Policy";

        // Act
        policyType = new PolicyType(expectedId, expectedName);

        // Assert
        assertEquals(expectedId, policyType.getId());
        assertEquals(expectedName, policyType.getName());
    }

    @Test
    void testBuilder() {
        // Act
        PolicyType policyType = PolicyType.builder()
                .build();

        // Assert
        assertNull(policyType.getId());
        assertNull(policyType.getName());

        // Arrange
        Long expectedId = 1L;
        String expectedName = "Test Policy";

        // Act
        policyType = PolicyType.builder()
                .id(expectedId)
                .name(expectedName)
                .build();

        // Assert
        assertEquals(expectedId, policyType.getId());
        assertEquals(expectedName, policyType.getName());
    }
    
    
}