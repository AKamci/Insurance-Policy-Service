package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.AuxiliaryRepositoryGateway.HealthPolicy;

import PolicyProject.policyService.application.gateways.AuxiliaryGateway.HealthPolicy.PersonalHealthGateway;
import PolicyProject.policyService.infrastructure.exception.ExpiredException.ExpiredMedicalReportException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PersonalHealthRepositoryGateway implements PersonalHealthGateway {

    private final PersonalHealthRepository personalHealthRepository;
    private final CustomerRepository customerRepository;


    @Override
    public PersonalHealth getWCustomer(PersonalHealth personalHealth) {
        validateInput(personalHealth);

        Customer customer = fetchCustomerByTckn(personalHealth.getCustomer().getTckn());
        if (customer == null) {
            return null;
        }
        PersonalHealth personalHealthEntity = fetchLatestPersonalHealth(customer.getTckn());
        validatePersonalHealthEntity(personalHealthEntity);
        System.out.println(personalHealthEntity);
        return personalHealthEntity;
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

    private void validateInput(PersonalHealth personalHealth) {
        if (personalHealth == null || personalHealth.getCustomer() == null || personalHealth.getCustomer().getTckn() == null) {
            throw new IllegalArgumentException("Tckn veya personalHealth alanı null olamaz");
        }
    }

    private Customer fetchCustomerByTckn(String tckn) {
        try {
            return customerRepository.findByTckn(tckn);
        } catch (Exception e) {
            throw new RuntimeException("Customer fetch error", e);
        }
    }

    private PersonalHealth fetchLatestPersonalHealth(String tckn) {
        try {
            return personalHealthRepository.findTopByCustomerTcknOrderByCreatedAtDesc(tckn);
        } catch (Exception e) {
            throw new RuntimeException("PersonalHealth fetch error", e);
        }
    }

    private void validatePersonalHealthEntity(PersonalHealth personalHealthEntity) {
        if (personalHealthEntity == null) {
            throw new ExpiredMedicalReportException(null, "İlgili belge bulunamadı.");
        }

        if (personalHealthEntity.getCreatedAt() != null &&
                personalHealthEntity.getCreatedAt().isAfter(LocalDateTime.now().minusMonths(6))) {
            return;
        } else {
            throw new ExpiredMedicalReportException(personalHealthEntity.getId(), "İlgili belgenin tarihi geçmiş.");
        }
    }


}