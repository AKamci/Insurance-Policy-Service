package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.PolicyGateway.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ScheduleHealthPolicyExpirationExecuteTest {

    private ScheduleHealthPolicyExpirationExecute policyScheduler;

    @Mock
    private HealthPolicyGateway healthPolicyGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        policyScheduler = new ScheduleHealthPolicyExpirationExecute(healthPolicyGateway);
    }

    @Test
    void testExpirePolicies() {
        // Mock veri
        HealthPolicy policy1 = HealthPolicy.builder()
                .id(1L)
                .state(PolicyState.CREATED)
                .expiryDate(LocalDate.now().minusDays(1))
                .build();
        HealthPolicy policy2 = HealthPolicy.builder()
                .id(2L)
                .state(PolicyState.CREATED)
                .expiryDate(LocalDate.now().minusDays(2))
                .build();
        List<HealthPolicy> expiredPolicies = Arrays.asList(policy1, policy2);

        when(healthPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now()))
                .thenReturn(expiredPolicies);

        policyScheduler.expirePolicies();

        verify(healthPolicyGateway, times(2)).update(any(HealthPolicy.class));

        ArgumentCaptor<HealthPolicy> captor = ArgumentCaptor.forClass(HealthPolicy.class);
        verify(healthPolicyGateway, times(2)).update(captor.capture());

        List<HealthPolicy> updatedPolicies = captor.getAllValues();
        assertEquals(PolicyState.EXPIRED, updatedPolicies.get(0).getState());
        assertEquals(LocalDate.now(), updatedPolicies.get(0).getExpiryDate());
        assertEquals(PolicyState.EXPIRED, updatedPolicies.get(1).getState());
        assertEquals(LocalDate.now(), updatedPolicies.get(1).getExpiryDate());
    }
}


