package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway;

import PolicyProject.policyService.domain.Enums.Enums.EarthquakePolicyEnum.EarthquakePolicyNonValueStrategyType;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;

import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EarthQuakeWeightRepositoryGatewayTest {

    private final EarthQuakeWeightsRepository weightsRepository = mock(EarthQuakeWeightsRepository.class);
    private final EarthQuakeWeightRepositoryGateway gateway = new EarthQuakeWeightRepositoryGateway(weightsRepository);


    public EarthQaukeWeights get(String key) {
        EarthQaukeWeights entity = weightsRepository.findByKey(key);
        return entity;
    }
    public EarthQaukeWeights create(EarthQaukeWeights weights) {
        try {
            var entity = weightsRepository.save(weights);
            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Key is Duplicate", weights.getKey());
        }
    }
    @Transactional
    public List<EarthQaukeWeights> updateOrSave(List<EarthQaukeWeights> weights) {
        List<String> typesToNullify = Arrays.stream(EarthquakePolicyNonValueStrategyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        for (EarthQaukeWeights weight : weights) {
            weightsRepository.findById(weight.getId()).ifPresentOrElse(existingWeight -> {
                if (!typesToNullify.contains(weight.getType()))
                {
                    existingWeight.setMinValue(weight.getMinValue());
                    existingWeight.setMaxValue(weight.getMaxValue());
                    existingWeight.setType(weight.getType());
                }
                existingWeight.setWeight(weight.getWeight());
                existingWeight.setKey(weight.getKey());
                weightsRepository.save(existingWeight);
            }, () -> {
                weightsRepository.save(weight);
            });
        }
        return weightsRepository.findAll();
    }
    public EarthQaukeWeights update(EarthQaukeWeights weights) {
        try {
            var entity = get(weights.getKey());
            if (entity == null) {
                return null;
            }

            weightsRepository.save(weights);
            return weights;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Key is Duplicate", weights.getKey());
        }
    }
    public EarthQaukeWeights delete(String key) {
        System.out.println(key);
        EarthQaukeWeights existingWeight = get(key);
        if (existingWeight == null) {
            return null;
        }
        weightsRepository.delete(existingWeight);
        return existingWeight;
    }
    public List<EarthQaukeWeights> list() {
        return StreamSupport
                .stream(weightsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Test
    public void testUpdateExistingEarthQuakeWeights() {
        EarthQaukeWeights existingWeight = new EarthQaukeWeights();
        existingWeight.setId(1L);
        existingWeight.setKey("existingKey");
        existingWeight.setWeight(BigDecimal.valueOf(5.0));

        when(weightsRepository.findById(existingWeight.getId())).thenReturn(java.util.Optional.of(existingWeight));
        when(weightsRepository.save(existingWeight)).thenReturn(existingWeight);

        EarthQaukeWeights updatedWeight = new EarthQaukeWeights();
        updatedWeight.setId(1L);
        updatedWeight.setKey("existingKey");
        updatedWeight.setWeight(BigDecimal.valueOf(15.0));

        EarthQaukeWeights result = gateway.update(updatedWeight);
        assertNotNull(result);
        assertEquals(15.0, result.getWeight());
        verify(weightsRepository).save(existingWeight);
    }

    @Test
    public void testUpdateNonExistingEarthQuakeWeights() {
        EarthQaukeWeights nonExistingWeight = new EarthQaukeWeights();
        nonExistingWeight.setId(99L);
        nonExistingWeight.setKey("nonExistingKey");
        nonExistingWeight.setWeight(BigDecimal.valueOf(5.0));

        when(weightsRepository.findById(nonExistingWeight.getId())).thenReturn(java.util.Optional.empty());

        EarthQaukeWeights result = gateway.update(nonExistingWeight);
        assertNull(result);
        verify(weightsRepository, never()).save(any(EarthQaukeWeights.class));
    }

    @Test
    public void testUpdateOrSaveEarthQuakeWeights() {
        EarthQaukeWeights existingWeight = new EarthQaukeWeights();
        existingWeight.setId(1L);
        existingWeight.setKey("existingKey");
        existingWeight.setWeight(BigDecimal.valueOf(5.0));

        EarthQaukeWeights newWeight = new EarthQaukeWeights();
        newWeight.setId(2L);
        newWeight.setKey("newKey");
        newWeight.setWeight(BigDecimal.valueOf(10.0));

        List<EarthQaukeWeights> weights = Arrays.asList(existingWeight, newWeight);

        when(weightsRepository.findById(existingWeight.getId())).thenReturn(java.util.Optional.of(existingWeight));
        when(weightsRepository.save(existingWeight)).thenReturn(existingWeight);
        when(weightsRepository.save(newWeight)).thenReturn(newWeight);
        when(weightsRepository.findAll()).thenReturn(weights);

        List<EarthQaukeWeights> result = gateway.updateOrSave(weights);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(weightsRepository).save(existingWeight);
        verify(weightsRepository).save(newWeight);
    }


    @Test
    public void testGetEarthQaukeWeightsFound() {
        EarthQaukeWeights weight = new EarthQaukeWeights();
        String key = "key1";
        weight.setKey(key);

        when(weightsRepository.findByKey(key)).thenReturn(weight);

        EarthQaukeWeights result = gateway.get(key);
        assertNotNull(result);
        assertEquals(key, result.getKey());
    }

    @Test
    public void testCreateEarthQuakeWeights() {
        EarthQaukeWeights weight = new EarthQaukeWeights();
        String key = "key1";
        weight.setKey(key);
        weight.setWeight(BigDecimal.valueOf(10.0));

        when(weightsRepository.save(weight)).thenReturn(weight);

        EarthQaukeWeights result = gateway.create(weight);
        assertNotNull(result);
        assertEquals(key, result.getKey());
        assertEquals(10.0, result.getWeight());
    }

    @Test
    public void testGetEarthQaukeWeightsNotFound() {
        String key = "key1";

        when(weightsRepository.findByKey(key)).thenReturn(null);

        EarthQaukeWeights result = gateway.get(key);
        assertNull(result);
    }
    @Test
    public void testDeleteEarthQuakeWeights() {
        EarthQaukeWeights weight = new EarthQaukeWeights();
        String key = "key1";
        weight.setKey(key);

        when(weightsRepository.findByKey(key)).thenReturn(weight);
        doNothing().when(weightsRepository).delete(weight);

        EarthQaukeWeights result = gateway.delete(key);
        assertNotNull(result);
        assertEquals(key, result.getKey());
        verify(weightsRepository).delete(weight);
    }

    @Test
    public void testDeleteNonExistingEarthQuakeWeights() {
        String key = "nonExistingKey";

        when(weightsRepository.findByKey(key)).thenReturn(null);

        EarthQaukeWeights result = gateway.delete(key);
        assertNull(result);
        verify(weightsRepository, never()).delete(any(EarthQaukeWeights.class));
    }


    @Test
    public void testListEarthQuakeWeights() {
        EarthQaukeWeights weight1 = new EarthQaukeWeights();
        weight1.setId(1L);
        weight1.setKey("key1");
        weight1.setWeight(BigDecimal.valueOf(5.0));

        EarthQaukeWeights weight2 = new EarthQaukeWeights();
        weight2.setId(2L);
        weight2.setKey("key2");
        weight2.setWeight(BigDecimal.valueOf(15.0));

        List<EarthQaukeWeights> weights = Arrays.asList(weight1, weight2);

        when(weightsRepository.findAll()).thenReturn(weights);

        List<EarthQaukeWeights> result = gateway.list();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("key1", result.get(0).getKey());
        assertEquals("key2", result.get(1).getKey());
    }
}
