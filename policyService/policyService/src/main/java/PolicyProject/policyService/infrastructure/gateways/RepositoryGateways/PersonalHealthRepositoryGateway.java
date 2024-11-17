package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.infrastructure.exception.ExpiredMedicalReportException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PersonalHealthRepositoryGateway implements PersonalHealthGateway {

    private final PersonalHealthRepository personalHealthRepository;

    @Override
    public PersonalHealth getWCustomer(PersonalHealth personalHealth) {
        if (personalHealth == null || personalHealth.getCustomer().getTckn() == null) {
            throw new IllegalArgumentException("Tckn veya personalHealth alanı null olamaz");
        }
        try {
            PersonalHealth personalHealthEntity = personalHealthRepository.findTopByCustomerTcknOrderByCreatedAtDesc(personalHealth.getCustomer().getTckn());
            if (personalHealthEntity == null) {
                return null;
            }
            if (personalHealthEntity.getCreatedAt() != null &&
                    personalHealthEntity.getCreatedAt().isAfter(LocalDateTime.now().minusMonths(6))) {
                return personalHealthEntity;
            } else {
                throw new ExpiredMedicalReportException(personalHealthEntity.getId(),"İlgili belgenin tarihi geçmiş.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public PersonalHealth get(String tckn) {
        return null;
    }
}
