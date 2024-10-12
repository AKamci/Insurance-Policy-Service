package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.CarPolicyRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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
        //Customer ı çek eşitle.
        var Tckn = carPolicy.getCustomer().getTckn();
        var Plate = carPolicy.getLicensePlate().getPlate();

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
    public List<CarPolicy> getCarPoliciesBetweenDate(Date startDate, Date endDate) {
        var PolicyList = carPolicyRepository.findByPolicyDateBetween(startDate, endDate);
        if (PolicyList.isEmpty() )
        {
            return null;
        }
        return PolicyList;
    }



}
