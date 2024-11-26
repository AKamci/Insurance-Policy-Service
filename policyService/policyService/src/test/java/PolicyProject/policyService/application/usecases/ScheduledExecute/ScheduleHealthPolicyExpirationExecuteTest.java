package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.PolicyGateway.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScheduleHealthPolicyExpirationExecuteTest {

    @Autowired
    private ScheduleHealthPolicyExpirationExecute scheduleHealthPolicyExpirationExecute;

    @MockBean
    private HealthPolicyGateway healthPolicyGateway;

    @Test
    void testExpirePolicies() {
        // Given
        HealthPolicy policy1 = new HealthPolicy();
        policy1.setState(PolicyState.CREATED);
        policy1.setExpiryDate(LocalDate.now().minusDays(1));

        HealthPolicy policy2 = new HealthPolicy();
        policy2.setState(PolicyState.CREATED);
        policy2.setExpiryDate(LocalDate.now().minusDays(1));

        List<HealthPolicy> expiredPolicies = Arrays.asList(policy1, policy2);

        when(healthPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now()))
                .thenReturn(expiredPolicies);

        // When
        scheduleHealthPolicyExpirationExecute.expirePolicies();

        // Then
        ArgumentCaptor<HealthPolicy> policyCaptor = ArgumentCaptor.forClass(HealthPolicy.class);
        verify(healthPolicyGateway, times(2)).update(policyCaptor.capture());
        List<HealthPolicy> updatedPolicies = policyCaptor.getAllValues();

        for (HealthPolicy updatedPolicy : updatedPolicies) {
            assertEquals(PolicyState.EXPIRED, updatedPolicy.getState());
            assertEquals(LocalDate.now(), updatedPolicy.getExpiryDate());
        }
    }

    @Test
    void testExpirePoliciesNoExpiredPolicies() {
        // Given
        when(healthPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now()))
                .thenReturn(List.of());

        // When
        scheduleHealthPolicyExpirationExecute.expirePolicies();

        // Then
        verify(healthPolicyGateway, never()).update(any(HealthPolicy.class));
    }

    @Test
    void testExpirePoliciesWithMixedStatePolicies() {
        // Given
        HealthPolicy policy1 = new HealthPolicy();
        policy1.setState(PolicyState.CREATED);
        policy1.setExpiryDate(LocalDate.now().minusDays(1));

        EarthquakePolicy policy2 = new EarthquakePolicy();
        policy2.setState(PolicyState.EXPIRED);
        policy2.setExpiryDate(LocalDate.now().minusDays(1));

        List<HealthPolicy> expiredPolicies = Arrays.asList(policy1);

        when(healthPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now()))
                .thenReturn(expiredPolicies);

        // When
        scheduleHealthPolicyExpirationExecute.expirePolicies();

        // Then
        ArgumentCaptor<HealthPolicy> policyCaptor = ArgumentCaptor.forClass(HealthPolicy.class);
        verify(healthPolicyGateway, times(1)).update(policyCaptor.capture());
        List<HealthPolicy> updatedPolicies = policyCaptor.getAllValues();

        for (HealthPolicy updatedPolicy : updatedPolicies) {
            assertEquals(PolicyState.EXPIRED, updatedPolicy.getState());
            assertEquals(LocalDate.now(), updatedPolicy.getExpiryDate());
        }
    }
}