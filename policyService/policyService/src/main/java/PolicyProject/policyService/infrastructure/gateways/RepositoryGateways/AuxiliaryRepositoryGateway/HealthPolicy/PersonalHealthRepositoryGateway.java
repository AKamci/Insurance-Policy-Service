package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.infrastructure.exception.ExpiredMedicalReportException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

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
                System.out.println(personalHealthEntity);
                return personalHealthEntity;
            } else {
                System.out.println(personalHealthEntity);
                throw new ExpiredMedicalReportException(personalHealthEntity.getId(),"İlgili belgenin tarihi geçmiş.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public PersonalHealth get(PersonalHealth personalHealth) {
        if (personalHealth == null) {
            return null;
        }
        return personalHealthRepository.findById(personalHealth.getId()).orElse(null);
    }


    @Override
    public PersonalHealth create(PersonalHealth personalHealth, Customer customer) {
        try {
            if (personalHealth == null) {
                return null;
            }
            personalHealth.setCustomer(customer);
            personalHealth.setCreatedAt(LocalDateTime.now());
            var entity = personalHealthRepository.save(personalHealth);
            personalHealth.setCustomer(entity.getCustomer());
            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Beklenmeyen Hata. Oluşturulamadı.");
        }
    }


}