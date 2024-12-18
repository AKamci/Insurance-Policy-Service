package PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild;

import PolicyProject.policyService.infrastructure.config.Specifications.EarthQuakeSpecification;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Data
@RequiredArgsConstructor
@Component
public class EarthQuakeSpecificationBuild {

    private Specification<EarthquakePolicy> spec;

    public Specification<EarthquakePolicy> EarthQuakeBuild(EarthquakePolicy earthquakePolicy, String tckn) {
        setSpec(
                EarthQuakeSpecification.build(
                        earthquakePolicy.getHouse().getNumber(),
                        earthquakePolicy.getHouse().getBuilding().getApartmentNumber(),
                        earthquakePolicy.getHouse().getBuilding().getAddress().getCity(),
                        earthquakePolicy.getHouse().getBuilding().getAddress().getDistrict(),
                        earthquakePolicy.getHouse().getBuilding().getAddress().getNeighborhood(),
                        earthquakePolicy.getPolicyDescription(),
                        earthquakePolicy.getCoverage(),
                        earthquakePolicy.getState(),
                        earthquakePolicy.getPolicyStartDate(),
                        earthquakePolicy.getPolicyEndDate(),
                        earthquakePolicy.getPolicyAmount(),
                        tckn));
        return spec;

    }
}
