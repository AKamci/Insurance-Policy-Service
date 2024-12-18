package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.WeightsRepositoryGateway;


import PolicyProject.policyService.application.gateways.WeightsGateway.CarPolicyWeightGateway;
import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEnum.CarPolicyNonValuesStrategyType;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.CarPolicyWeightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class CarPolicyWeightRepositoryGateway implements CarPolicyWeightGateway {

    private final CarPolicyWeightsRepository weightsRepository;

    @Override
    public Weights get(String key) {
        Weights entity = weightsRepository.findByKey(key);
        return entity;
    }

    @Override
    public Weights create(Weights weights) {
        try {
            var entity = weightsRepository.save(weights);
            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateWeightKeyException("Key is Duplicate", weights.getKey());
        }
    }

    @Transactional
    @Override
    public List<Weights> updateOrSave(List<Weights> weights) {
        List<String> typesToNullify = Arrays.stream(CarPolicyNonValuesStrategyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        for (Weights weight : weights) {
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
    public Weights update(Weights weights) {
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
    public Weights delete(String key) {
        System.out.println(key);
        Weights existingWeight = get(key);
        if (existingWeight == null) {
            return null;
        }
        weightsRepository.delete(existingWeight);
        return existingWeight;
    }

    @Override
    public List<Weights> list() {
        return StreamSupport
                .stream(weightsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public List<Weights> listFilter() {
        List<Weights> weightsList = StreamSupport
                .stream(weightsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<String> typesToNullify = Arrays.stream(CarPolicyNonValuesStrategyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        weightsList.forEach(weight -> weight.nullifyValuesIfNeeded(typesToNullify));
        return weightsList;
    }


}
