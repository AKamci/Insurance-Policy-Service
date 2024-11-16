package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.EarthQuakeWeightGateway;
import PolicyProject.policyService.domain.Enums.Enums.NonValuesStrategyType;
import PolicyProject.policyService.infrastructure.exception.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;

import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
public class EarthQuakeWeightRepositoryGateway implements EarthQuakeWeightGateway {

    private final EarthQuakeWeightsRepository weightsRepository;

    @Override
    public EarthQaukeWeights get(String key) {
        EarthQaukeWeights entity = weightsRepository.findByKey(key);
        return entity;
    }

    @Override
    public EarthQaukeWeights create(EarthQaukeWeights weights) {
        try {
            var entity = weightsRepository.save(weights);
            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Key is Duplicate", weights.getKey());
        }
    }

    @Transactional
    @Override
    public List<EarthQaukeWeights> updateOrSave(List<EarthQaukeWeights> weights) {
        List<String> typesToNullify = Arrays.stream(NonValuesStrategyType.values())
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

    @Override
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

    @Override
    public EarthQaukeWeights delete(String key) {
        System.out.println(key);
        EarthQaukeWeights existingWeight = get(key);
        if (existingWeight == null) {
            return null;
        }
        weightsRepository.delete(existingWeight);
        return existingWeight;
    }

    @Override
    public List<EarthQaukeWeights> list() {
        return StreamSupport
                .stream(weightsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public List<EarthQaukeWeights> listFilter() {
        List<EarthQaukeWeights> weightsList = StreamSupport
                .stream(weightsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<String> typesToNullify = Arrays.stream(NonValuesStrategyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        weightsList.forEach(weight -> weight.nullifyValuesIfNeeded(typesToNullify));
        return weightsList;
    }
}
