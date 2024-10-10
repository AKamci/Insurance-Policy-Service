package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.application.gateways.carPolicyGateway;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import PolicyProject.policyService.interfaces.mappers.Mapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
public class carPolicyRepositoryGateway implements carPolicyGateway
{

    private final carPolicyRepository carPolicyRepository;


    @Override
    public CarPolicy create(CarPolicy carPolicy, double Amount, Customer customer) {

         carPolicy.setCustomer(customer);
         carPolicy.setPolicyAmount(Amount);
         return carPolicyRepository.save(carPolicy);
    }

    @Override
    public CarPolicy get(CarPolicy carPolicy) {
        var EntityObject = carPolicyRepository.findById(carPolicy.getId());
        return EntityObject.orElse(null);
    }

    @Override
    public CarPolicy update(CarPolicy carPolicy) {
       var EntityObject = get(carPolicy);
       if (EntityObject != null) {
        return carPolicyRepository.save(carPolicy);
       }
       return null;
    }

    @Override
    public CarPolicy delete(CarPolicy carPolicy) {

        var EntityObject = get(carPolicy);
        if (EntityObject != null) {
            carPolicyRepository.delete(carPolicy);
            return EntityObject;
        }
        return null;
    }

    @Override
    public List<CarPolicy> getList() {
        Iterable<CarPolicy> iterable = carPolicyRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarPolicy> getCarPoliciesByCustomer(Long customerId) {
        var PolicyList = carPolicyRepository.findByCustomerId(customerId).stream().toList();
        if (PolicyList.isEmpty() )
        {
            return null;
        }
            return PolicyList;
    }
}
