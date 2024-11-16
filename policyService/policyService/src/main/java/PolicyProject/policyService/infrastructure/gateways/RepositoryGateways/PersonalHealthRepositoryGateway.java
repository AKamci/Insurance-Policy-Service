package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonalHealthRepositoryGateway implements PersonalHealthGateway {
    PersonalHealthRepository personalHealthRepository;

    @Override
    public PersonalHealth getWCustomer(PersonalHealth personalHealth) {
        return null;
    }

    @Override
    public PersonalHealth get(String tckn) {
        return null;
    }
}
