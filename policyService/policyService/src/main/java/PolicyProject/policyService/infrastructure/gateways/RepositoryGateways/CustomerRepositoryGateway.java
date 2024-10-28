package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.infrastructure.exception.DuplicateTcknException;
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
            var entity = customerRepository.save(customer);
            updateTotalCount();
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
        newCustomer.setId(existingCustomer.getId());
        newCustomer.setCarPolicies(existingCustomer.getCarPolicies());
        return customerRepository.save(newCustomer);
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