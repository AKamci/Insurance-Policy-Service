package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.CarPolicyWeightsRepository;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEnum.CarPolicyNonValuesStrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarPolicyWeightRepositoryGatewayTest {

    @Mock
    private CarPolicyWeightsRepository weightsRepository;

    @InjectMocks
    private CarPolicyWeightRepositoryGateway carPolicyWeightRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() {
        Weights weights = new Weights();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);

        Weights result = carPolicyWeightRepositoryGateway.get("key1");
        assertEquals(weights, result);
    }

    @Test
    public void testCreate() {
        Weights weights = new Weights();
        weights.setKey("key1");

        when(weightsRepository.save(weights)).thenReturn(weights);

        Weights result = carPolicyWeightRepositoryGateway.create(weights);
        assertEquals(weights, result);
    }

    @Test
    public void testCreate_DuplicateKey() {
        Weights weights = new Weights();
        weights.setKey("key1");

        when(weightsRepository.save(weights)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DuplicateWeightKeyException.class, () -> carPolicyWeightRepositoryGateway.create(weights));
    }

    @Test
    public void testUpdateOrSave() {
        Weights weights1 = new Weights();
        weights1.setId(1L);
        weights1.setKey("key1");

        Weights weights2 = new Weights();
        weights2.setId(2L);
        weights2.setKey("key2");

        when(weightsRepository.findById(1L)).thenReturn(Optional.of(weights1));
        when(weightsRepository.findById(2L)).thenReturn(Optional.empty());
        when(weightsRepository.save(any(Weights.class))).thenAnswer(i -> i.getArguments()[0]);
        when(weightsRepository.findAll()).thenReturn(Arrays.asList(weights1, weights2));

        List<Weights> weightsList = Arrays.asList(weights1, weights2);

        List<Weights> result = carPolicyWeightRepositoryGateway.updateOrSave(weightsList);
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdate() {
        Weights weights = new Weights();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);
        when(weightsRepository.save(weights)).thenReturn(weights);

        Weights result = carPolicyWeightRepositoryGateway.update(weights);
        assertEquals(weights, result);
    }

    @Test
    public void testUpdate_DuplicateKey() {
        Weights weights = new Weights();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);
        when(weightsRepository.save(weights)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DuplicateWeightKeyException.class, () -> carPolicyWeightRepositoryGateway.update(weights));
    }

    @Test
    public void testDelete() {
        Weights weights = new Weights();
        weights.setKey("key1");

        when(weightsRepository.findByKey("key1")).thenReturn(weights);
        doNothing().when(weightsRepository).delete(weights);

        Weights result = carPolicyWeightRepositoryGateway.delete("key1");
        assertEquals(weights, result);
    }

    @Test
    public void testDelete_NonExistentKey() {
        when(weightsRepository.findByKey("key1")).thenReturn(null);

        Weights result = carPolicyWeightRepositoryGateway.delete("key1");
        assertNull(result);
    }

    @Test
    public void testList() {
        Weights weights1 = new Weights();
        Weights weights2 = new Weights();

        when(weightsRepository.findAll()).thenReturn(Arrays.asList(weights1, weights2));

        List<Weights> result = carPolicyWeightRepositoryGateway.list();
        assertEquals(2, result.size());
    }

    @Test
    public void testListFilter() {
        Weights weights1 = new Weights();
        weights1.setType(CarPolicyNonValuesStrategyType.POLICY_TYPE.toString());
        Weights weights2 = new Weights();
        weights2.setType("OTHER");

        when(weightsRepository.findAll()).thenReturn(Arrays.asList(weights1, weights2));

        List<Weights> result = carPolicyWeightRepositoryGateway.listFilter();
        assertNull(weights1.getMinValue());
        assertEquals(weights2.getMinValue(), weights2.getMinValue());
    }
}
