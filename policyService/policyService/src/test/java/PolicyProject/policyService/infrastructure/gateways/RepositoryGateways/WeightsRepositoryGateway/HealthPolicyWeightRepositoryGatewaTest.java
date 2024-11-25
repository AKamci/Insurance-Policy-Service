package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEnum.CarPolicyNonValuesStrategyType;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.CarPolicyWeightsRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class HealthPolicyWeightRepositoryGatewaTest {

    @Mock
    private HealthPolicyWeightRepository weightsRepository;

    @InjectMocks
    private HealthPolicyWeightRepositoryGateway carPolicyWeightRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() {
        HealthPolicyWeight weights = new HealthPolicyWeight();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);

        HealthPolicyWeight result = carPolicyWeightRepositoryGateway.get("key1");
        assertEquals(weights, result);
    }

    @Test
    public void testCreate() {
        HealthPolicyWeight weights = new HealthPolicyWeight();
        weights.setKey("key1");

        when(weightsRepository.save(weights)).thenReturn(weights);

        HealthPolicyWeight result = carPolicyWeightRepositoryGateway.create(weights);
        assertEquals(weights, result);
    }

    @Test
    public void testCreate_DuplicateKey() {
        HealthPolicyWeight weights = new HealthPolicyWeight();
        weights.setKey("key1");

        when(weightsRepository.save(weights)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DuplicateWeightKeyException.class, () -> carPolicyWeightRepositoryGateway.create(weights));
    }

    @Test
    public void testUpdateOrSave() {
        HealthPolicyWeight weights1 = new HealthPolicyWeight();
        weights1.setId(1L);
        weights1.setKey("key1");

        HealthPolicyWeight weights2 = new HealthPolicyWeight();
        weights2.setId(2L);
        weights2.setKey("key2");

        when(weightsRepository.findById(1L)).thenReturn(Optional.of(weights1));
        when(weightsRepository.findById(2L)).thenReturn(Optional.empty());
        when(weightsRepository.save(any(HealthPolicyWeight.class))).thenAnswer(i -> i.getArguments()[0]);
        when(weightsRepository.findAll()).thenReturn(Arrays.asList(weights1, weights2));

        List<HealthPolicyWeight> weightsList = Arrays.asList(weights1, weights2);

        List<HealthPolicyWeight> result = carPolicyWeightRepositoryGateway.updateOrSave(weightsList);
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdate() {
        HealthPolicyWeight weights = new HealthPolicyWeight();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);
        when(weightsRepository.save(weights)).thenReturn(weights);

        HealthPolicyWeight result = carPolicyWeightRepositoryGateway.update(weights);
        assertEquals(weights, result);
    }

    @Test
    public void testUpdate_DuplicateKey() {
        HealthPolicyWeight weights = new HealthPolicyWeight();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);
        when(weightsRepository.save(weights)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DuplicateWeightKeyException.class, () -> carPolicyWeightRepositoryGateway.update(weights));
    }

    @Test
    public void testDelete() {
        HealthPolicyWeight weights = new HealthPolicyWeight();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);
        doNothing().when(weightsRepository).delete(weights);

        HealthPolicyWeight result = carPolicyWeightRepositoryGateway.delete("key1");
        assertEquals(weights, result);
    }

    @Test
    public void testDelete_NonExistentKey() {
        when(weightsRepository.findByKey("key1")).thenReturn(null);

        HealthPolicyWeight result = carPolicyWeightRepositoryGateway.delete("key1");
        assertNull(result);
    }

    @Test
    public void testList() {
        HealthPolicyWeight weights1 = new HealthPolicyWeight();
        HealthPolicyWeight weights2 = new HealthPolicyWeight();

        when(weightsRepository.findAll()).thenReturn(Arrays.asList(weights1, weights2));

        List<HealthPolicyWeight> result = carPolicyWeightRepositoryGateway.list();
        assertEquals(2, result.size());
    }

    @Test
    public void testListFilter() {
        HealthPolicyWeight weights1 = new HealthPolicyWeight();
        weights1.setType(CarPolicyNonValuesStrategyType.POLICY_TYPE.toString());
        HealthPolicyWeight weights2 = new HealthPolicyWeight();
        weights2.setType("OTHER");

        when(weightsRepository.findAll()).thenReturn(Arrays.asList(weights1, weights2));

        List<HealthPolicyWeight> result = carPolicyWeightRepositoryGateway.listFilter();
        assertNull(weights1.getMinValue());
        assertEquals(weights2.getMinValue(), weights2.getMinValue());
    }

}

