package PolicyProject.policyService.application.usecases.ScheduledExecute;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.application.gateways.PolicyGateway.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ScheduleCarPolicyExpirationExecuteTest {

    private ScheduleCarPolicyExpirationExecute policyScheduler;

    @Mock
    private CarPolicyGateway carPolicyGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        policyScheduler = new ScheduleCarPolicyExpirationExecute(carPolicyGateway);
    }

    @Test
    void testExpirePolicies() {
        CarPolicy policy1 = CarPolicy.builder()
                .id(1L)
                .state(PolicyState.CREATED)
                .expiryDate(LocalDate.now().minusDays(1))
                .build();
        CarPolicy policy2 = CarPolicy.builder()
                .id(2L)
                .state(PolicyState.CREATED)
                .expiryDate(LocalDate.now().minusDays(2))
                .build();
        List<CarPolicy> expiredPolicies = Arrays.asList(policy1, policy2);

        when(carPolicyGateway.findByStateAndExpiryDateBefore(PolicyState.CREATED, LocalDate.now()))
                .thenReturn(expiredPolicies);

        policyScheduler.expirePolicies();

        verify(carPolicyGateway, times(2)).update(any(CarPolicy.class));

        ArgumentCaptor<CarPolicy> captor = ArgumentCaptor.forClass(CarPolicy.class);
        verify(carPolicyGateway, times(2)).update(captor.capture());

        List<CarPolicy> updatedPolicies = captor.getAllValues();
        assertEquals(PolicyState.EXPIRED, updatedPolicies.get(0).getState());
        assertEquals(LocalDate.now(), updatedPolicies.get(0).getExpiryDate());
        assertEquals(PolicyState.EXPIRED, updatedPolicies.get(1).getState());
        assertEquals(LocalDate.now(), updatedPolicies.get(1).getExpiryDate());
    }


}