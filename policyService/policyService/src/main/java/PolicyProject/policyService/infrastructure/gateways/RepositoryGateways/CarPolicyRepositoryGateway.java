package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.infrastructure.config.Specifications.CarPolicySpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.CarPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class CarPolicyRepositoryGateway implements CarPolicyGateway
{

    private final CarPolicyRepository carPolicyRepository;


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
   public List<CarPolicy> getList(Specification<CarPolicy> specification, int page, int size) {
       Pageable pageable = PageRequest.of(page, size);
       Page<CarPolicy> carPolicyPage = carPolicyRepository.findAll(specification, pageable);
       return carPolicyPage.getContent();
   }



    @Override
    public List<CarPolicy> getCarPoliciesByCustomer(String tckn) {
        var PolicyList = carPolicyRepository.findByCustomerTckn(tckn).stream().toList();
        if (PolicyList.isEmpty() )
        {
            return null;
        }
        return PolicyList;
    }

    @Override
    public List<CarPolicy> getCarPoliciesByPlateAndTckn(String plate, String tckn) {
        var PolicyList = carPolicyRepository.findByCustomerTcknAndLicensePlatePlate(tckn, plate);
        if (PolicyList.isEmpty() )
        {
            return null;
        }
        return PolicyList;
    }

    @Override
    public List<CarPolicy> getCarPoliciesBetweenDate(LocalDate startDate, LocalDate endDate) {
        var PolicyList = carPolicyRepository.findByPolicyStartDateBetween(startDate, endDate);
        if (PolicyList.isEmpty() )
        {
            return null;
        }
        return PolicyList;
    }

    public int getTotal() {
        return (int) carPolicyRepository.count();
    }


}
