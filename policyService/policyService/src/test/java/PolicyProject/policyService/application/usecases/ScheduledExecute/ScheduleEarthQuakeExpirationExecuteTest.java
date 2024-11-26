package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.application.gateways.PolicyGateway.EarthQuakeGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScheduleEarthQuakeExpirationExecuteTest {

    private ScheduleEarthQuakeExpirationExecute policyScheduler;

    @Mock
    private EarthQuakeGateway earthQuakeGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        policyScheduler = new ScheduleEarthQuakeExpirationExecute(earthQuakeGateway);
    }

    @Test
    void testExpirePolicies() {
        EarthquakePolicy policy1 = EarthquakePolicy.builder()
                .id(1L)
                .state(PolicyState.CREATED)
                .expiryDate(LocalDate.now().minusDays(1))
                .build();
        EarthquakePolicy policy2 = EarthquakePolicy.builder()
                .id(2L)
                .state(PolicyState.CREATED)
                .expiryDate(LocalDate.now().minusDays(2))
                .build();
        List<EarthquakePolicy> expiredPolicies = Arrays.asList(policy1, policy2);

        when(earthQuakeGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now()))
                .thenReturn(expiredPolicies);

        policyScheduler.expirePolicies();

        verify(earthQuakeGateway, times(2)).update(any(EarthquakePolicy.class));

        ArgumentCaptor<EarthquakePolicy> captor = ArgumentCaptor.forClass(EarthquakePolicy.class);
        verify(earthQuakeGateway, times(2)).update(captor.capture());

        List<EarthquakePolicy> updatedPolicies = captor.getAllValues();
        assertEquals(PolicyState.EXPIRED, updatedPolicies.get(0).getState());
        assertEquals(LocalDate.now(), updatedPolicies.get(0).getExpiryDate());
        assertEquals(PolicyState.EXPIRED, updatedPolicies.get(1).getState());
        assertEquals(LocalDate.now(), updatedPolicies.get(1).getExpiryDate());
    }




}