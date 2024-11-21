package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway;

import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class HealthPolicyWeightRepositoryGatewaTest {

    @Mock
    private HealthPolicyWeightRepository healthPolicyWeightRepository;
    public HealthPolicyWeight get(String key) {
        return healthPolicyWeightRepository.findByKey(key);
    }
    public HealthPolicyWeight create(HealthPolicyWeight weight) {
        try {
            return healthPolicyWeightRepository.save(weight);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Weight key already exists", weight.getKey());
        }
    }
    public HealthPolicyWeight update(HealthPolicyWeight weight) {
        return healthPolicyWeightRepository.save(weight);
    }
    public HealthPolicyWeight delete(String key) {
        HealthPolicyWeight weight = healthPolicyWeightRepository.findByKey(key);
        healthPolicyWeightRepository.delete(weight);
        return weight;
    }
    public List<HealthPolicyWeight> list() {
        return healthPolicyWeightRepository.findAll();
    }
    public List<HealthPolicyWeight> updateOrSave(List<HealthPolicyWeight> weights) {
        for (HealthPolicyWeight weight : weights) {
            healthPolicyWeightRepository.save(weight);
        }
        return weights;
    }

    @Test
    public void testListHealthPolicyWeights() {
        // Creating sample HealthPolicyWeight instances
        HealthPolicyWeight weight1 = new HealthPolicyWeight();
        weight1.setKey("weight1Key");
        weight1.setWeight(new BigDecimal("10.0"));
        weight1.setMinValue(new BigDecimal("1.0"));
        weight1.setMaxValue(new BigDecimal("20.0"));
        weight1.setType("Type1");

        HealthPolicyWeight weight2 = new HealthPolicyWeight();
        weight2.setKey("weight2Key");
        weight2.setWeight(new BigDecimal("15.0"));
        weight2.setMinValue(new BigDecimal("5.0"));
        weight2.setMaxValue(new BigDecimal("25.0"));
        weight2.setType("Type2");

        List<HealthPolicyWeight> weightsList = Arrays.asList(weight1, weight2);

        when(healthPolicyWeightRepository.findAll()).thenReturn(weightsList);

        // Fetching the list using the method to be tested
        List<HealthPolicyWeight> result = this.list();

        // Assertions to verify the list fetching operation
        assertNotNull(result);
        assertEquals(result.size(), 2);

        // Verifying that the findAll method was called once
        verify(healthPolicyWeightRepository, times(1)).findAll();
    }
    @Test
    public void testFindHealthPolicyWeightByKey() {

        String testKey = "testKey";
        HealthPolicyWeight testWeight = new HealthPolicyWeight();
        testWeight.setKey(testKey);

        when(healthPolicyWeightRepository.findByKey(testKey)).thenReturn(testWeight);

        HealthPolicyWeight result = this.get(testKey);

        assertNotNull(result);
        assertEquals(result.getKey(), testKey);

        verify(healthPolicyWeightRepository, times(1)).findByKey(testKey);
    }
    @Test
    public void testCreateHealthPolicyWeight() {

        HealthPolicyWeight newWeight = new HealthPolicyWeight();
        newWeight.setKey("newTestKey");
        newWeight.setWeight(new BigDecimal("10.5"));
        newWeight.setMinValue(new BigDecimal("1.0"));
        newWeight.setMaxValue(new BigDecimal("20.0"));
        newWeight.setType("WeightType");

        when(healthPolicyWeightRepository.save(newWeight)).thenReturn(newWeight);

        HealthPolicyWeight result = this.create(newWeight);

        assertNotNull(result);
        assertEquals(result.getKey(), "newTestKey");
        assertEquals(result.getWeight(), new BigDecimal("10.5"));

        verify(healthPolicyWeightRepository, times(1)).save(newWeight);
    }


    @Test
    public void testUpdateHealthPolicyWeight() {

        // Creating an existing weight instance and setting its properties
        HealthPolicyWeight existingWeight = new HealthPolicyWeight();
        existingWeight.setId(1L);
        existingWeight.setKey("existingKey");
        existingWeight.setWeight(new BigDecimal("15.0"));
        existingWeight.setMinValue(new BigDecimal("5.0"));
        existingWeight.setMaxValue(new BigDecimal("25.0"));
        existingWeight.setType("ExistingType");

        // Mocking the repository to return the existing weight when the id is searched
        when(healthPolicyWeightRepository.findById(existingWeight.getId())).thenReturn(Optional.of(existingWeight));

        // Updating the weight properties
        HealthPolicyWeight updatedWeight = existingWeight;
        updatedWeight.setWeight(new BigDecimal("20.0"));
        updatedWeight.setMinValue(new BigDecimal("10.0"));
        updatedWeight.setMaxValue(new BigDecimal("30.0"));

        when(healthPolicyWeightRepository.save(existingWeight)).thenReturn(updatedWeight);

        // Calling the update method with the updated weight
        HealthPolicyWeight result = this.update(updatedWeight);

        // Assertions to verify the update operation
        assertNotNull(result);
        assertEquals(result.getWeight(), new BigDecimal("20.0"));
        assertEquals(result.getMinValue(), new BigDecimal("10.0"));
        assertEquals(result.getMaxValue(), new BigDecimal("30.0"));

        // Verifying that the save method was called once with the updated weight
        verify(healthPolicyWeightRepository, times(1)).save(updatedWeight);
    }
    @Test
    public void testUpdateOrSaveHealthPolicyWeight() {

        HealthPolicyWeight existingWeight = new HealthPolicyWeight();
        existingWeight.setId(1L);
        existingWeight.setKey("existingKey");
        existingWeight.setWeight(new BigDecimal("15.0"));
        existingWeight.setMinValue(new BigDecimal("5.0"));
        existingWeight.setMaxValue(new BigDecimal("25.0"));
        existingWeight.setType("ExistingType");

        HealthPolicyWeight newWeight = new HealthPolicyWeight();
        newWeight.setKey("newKey");
        newWeight.setWeight(new BigDecimal("20.0"));
        newWeight.setMinValue(new BigDecimal("4.0"));
        newWeight.setMaxValue(new BigDecimal("30.0"));
        newWeight.setType("NewType");

        List<HealthPolicyWeight> weights = Arrays.asList(existingWeight, newWeight);

        doReturn(Optional.of(existingWeight)).when(healthPolicyWeightRepository).findById(existingWeight.getId());
        doReturn(existingWeight).when(healthPolicyWeightRepository).save(existingWeight);
        doReturn(newWeight).when(healthPolicyWeightRepository).save(newWeight);
        doReturn(weights).when(healthPolicyWeightRepository).findAll();
//        when(healthPolicyWeightRepository.findById(existingWeight.getId())).thenReturn(Optional.of(existingWeight));
//        when(healthPolicyWeightRepository.save(existingWeight)).thenReturn(existingWeight);
//        when(healthPolicyWeightRepository.save(newWeight)).thenReturn(newWeight);
        //when(healthPolicyWeightRepository.findAll()).thenReturn(weights);

        List<HealthPolicyWeight> result = this.updateOrSave(weights);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(healthPolicyWeightRepository, times(2)).save(any(HealthPolicyWeight.class));
        verify(healthPolicyWeightRepository, times(1)).findById(existingWeight.getId());
    }
    @Test
    public void testDeleteHealthPolicyWeight() {
        // Setting up a mock HealthPolicyWeight instance to be deleted
        HealthPolicyWeight weightToDelete = new HealthPolicyWeight();
        weightToDelete.setKey("deleteKey");

        // Mocking repository to return the weight instance when searched by key
        when(healthPolicyWeightRepository.findByKey(weightToDelete.getKey())).thenReturn(weightToDelete);

        // Calling the delete method
        HealthPolicyWeight result = this.delete(weightToDelete.getKey());

        // Assertions to verify the delete operation
        assertNotNull(result);
        assertEquals(result.getKey(), weightToDelete.getKey());

        // Verifying that the delete method was called once with the weight instance
        verify(healthPolicyWeightRepository, times(1)).delete(weightToDelete);
    }

}

