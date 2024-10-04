package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.application.gateways.carPolicyGateway;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import PolicyProject.policyService.interfaces.mappers.Mapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class carPolicyRepositoryGateway implements carPolicyGateway
{

    private final carPolicyRepository carPolicyRepository;


    @Override
    public carPolicy create(carPolicy carPolicy, double Amount) {
         carPolicy.setPolicyAmount(Amount);

         return carPolicyRepository.save(carPolicy);
    }

    @Override
    public carPolicy get(carPolicy carPolicy) {
        var EntityObject = carPolicyRepository.findById(carPolicy.getId());
        return EntityObject.orElse(null);
    }

    @Override
    public carPolicy update(carPolicy carPolicy) {
       var EntityObject = get(carPolicy);
       if (EntityObject != null) {
        return carPolicyRepository.save(carPolicy);
       }
       return null;
    }

    @Override
    public carPolicy delete(carPolicy carPolicy) {

        var EntityObject = get(carPolicy);
        if (EntityObject != null) {
            carPolicyRepository.delete(carPolicy);
        }
        return null;
    }

    @Override
    public carPolicy getList(carPolicy carPolicy) {
        return null;
    }
}
