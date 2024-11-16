package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.*;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public interface EarthQuakeGateway {

    EarthquakePolicy create(EarthquakePolicy earthquakePolicy, Customer customer, House house);
    EarthquakePolicy get(EarthquakePolicy earthquakePolicy);
    EarthquakePolicy update(EarthquakePolicy earthquakePolicy);
    EarthquakePolicy delete(EarthquakePolicy earthquakePolicy);
    List<EarthquakePolicy> getList(Specification<EarthquakePolicy> earthquakePolicy, int page, int size);
    List<EarthquakePolicy> getCarPoliciesByCustomer(String tckn);
    List<EarthquakePolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);
    EarthquakePolicy SetStateCarPolicy(EarthquakePolicy earthquakePolicy, PolicyState policyState);
    int getTotal();

}
