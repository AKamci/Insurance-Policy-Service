package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.infrastructure.exception.DuplicateTcknException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    @CachePut(value = "customerCache", key = "#customer.tckn")
    public Customer create(Customer customer) {
        try {
            if (customer == null) {
                return null;
            }
            var entity = customerRepository.save(customer);
            return entity;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateTcknException("TCKN is Duplicate", customer.getTckn());
        }
    }

    @Override
    @Cacheable(value = "customerCache", key = "#customer.tckn")
    public Customer get(Customer customer) {

        var entityCustomer = customerRepository.findByTckn(customer.getTckn());
        return entityCustomer;
    }

    @Override
    @Transactional
    @CachePut(value = "customerCache", key = "#newCustomer.tckn")
    public Customer update(Customer newCustomer) {
        Customer existingCustomer = customerRepository.findByTckn(newCustomer.getTckn());
        if (existingCustomer == null) {
            return null;
        }

        existingCustomer.setName(newCustomer.getName());
        existingCustomer.setAddress(newCustomer.getAddress());
        existingCustomer.setPhone(newCustomer.getPhone());
        existingCustomer.setEmail(newCustomer.getEmail());
        existingCustomer.setPassword(newCustomer.getPassword());
        existingCustomer.setBirthDay(newCustomer.getBirthDay());
        existingCustomer.setGender(newCustomer.getGender());
        existingCustomer.setGrade(newCustomer.getGrade());

        if (newCustomer.getPersonalHealths() != null) {
            existingCustomer.getPersonalHealths().forEach(health -> health.setCustomer(null));
            existingCustomer.getPersonalHealths().clear();

            newCustomer.getPersonalHealths().forEach(health -> {
                PersonalHealth newHealth = new PersonalHealth();
                newHealth.setHeight(health.getHeight());
                newHealth.setWeight(health.getWeight());
                newHealth.setBmi(health.getBmi());
                newHealth.setBloodType(health.getBloodType());
                newHealth.setAlcoholConsumption(health.getAlcoholConsumption());
                newHealth.setSmokeConsumption(health.getSmokeConsumption());
                newHealth.setIsPregnant(health.getIsPregnant());
                newHealth.setHasDisability(health.getHasDisability());
                newHealth.setHasPreviousSurgeries(health.getHasPreviousSurgeries());

                newHealth.setCustomer(existingCustomer);
                existingCustomer.getPersonalHealths().add(newHealth);
            });
        }

        return customerRepository.save(existingCustomer);
    }

    @Override
    @CacheEvict(value = "customerCache", key = "#customer.tckn")
    public Customer delete(Customer customer) {
        Customer existingCustomer = get(customer);
        if (existingCustomer == null) {
            return null;
        }
        customerRepository.delete(existingCustomer);
        updateTotalCount();
        return existingCustomer;
    }

    @Override
   // @Cacheable(value = "customerCache", key = "#page + '-' + #size")
    public List<Customer> getList(Specification<Customer> specification, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findAll(specification, pageable);
        return customerPage.getContent();
    }

    @Cacheable("totalCustomer")
    public int getTotal() {
        return (int) customerRepository.count();
    }

    @CacheEvict(value = "totalCustomer", allEntries = true)
    public void updateTotalCount() {}

}