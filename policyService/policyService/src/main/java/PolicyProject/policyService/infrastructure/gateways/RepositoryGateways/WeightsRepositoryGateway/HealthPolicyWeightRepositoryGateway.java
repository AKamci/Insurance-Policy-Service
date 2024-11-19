package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway;

import PolicyProject.policyService.application.gateways.WeightsGateway.HealthPolicyWeightGateway;
import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.HealthPolicyNonValueStrategyType;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class HealthPolicyWeightRepositoryGateway implements HealthPolicyWeightGateway {

    private final HealthPolicyWeightRepository healthPolicyWeightRepository;

    @Override
    public HealthPolicyWeight get(String key) {
        HealthPolicyWeight entity = healthPolicyWeightRepository.findByKey(key);
        return entity;
    }

    @Override
    public HealthPolicyWeight create(HealthPolicyWeight weights) {
        try {
            var entity = healthPolicyWeightRepository.save(weights);
            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Key is Duplicate", weights.getKey());
        }
    }

    @Transactional
    @Override
    public List<HealthPolicyWeight> updateOrSave(List<HealthPolicyWeight> weights) {
        List<String> typesToNullify = Arrays.stream(HealthPolicyNonValueStrategyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        for (HealthPolicyWeight weight : weights) {
            healthPolicyWeightRepository.findById(weight.getId()).ifPresentOrElse(existingWeight -> {
                if (!typesToNullify.contains(weight.getType()))
                {
                    existingWeight.setMinValue(weight.getMinValue());
                    existingWeight.setMaxValue(weight.getMaxValue());
                    existingWeight.setType(weight.getType());
                }
                existingWeight.setWeight(weight.getWeight());
                existingWeight.setKey(weight.getKey());
                healthPolicyWeightRepository.save(existingWeight);
            }, () -> {
                healthPolicyWeightRepository.save(weight);
            });
        }
        return healthPolicyWeightRepository.findAll();
    }

    @Override
    public HealthPolicyWeight update(HealthPolicyWeight weights) {
        try {
            var entity = get(weights.getKey());
            if (entity == null) {
                return null;
            }

            healthPolicyWeightRepository.save(weights);
            return weights;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Key is Duplicate", weights.getKey());
        }
    }

    @Override
    public HealthPolicyWeight delete(String key) {
        System.out.println(key);
        HealthPolicyWeight existingWeight = get(key);
        if (existingWeight == null) {
            return null;
        }
        healthPolicyWeightRepository.delete(existingWeight);
        return existingWeight;
    }

    @Override
    public List<HealthPolicyWeight> list() {
        return StreamSupport
                .stream(healthPolicyWeightRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public List<HealthPolicyWeight> listFilter() {
        List<HealthPolicyWeight> weightsList = StreamSupport
                .stream(healthPolicyWeightRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<String> typesToNullify = Arrays.stream(HealthPolicyNonValueStrategyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        weightsList.forEach(weight -> weight.nullifyValuesIfNeeded(typesToNullify));
        return weightsList;
    }
}
