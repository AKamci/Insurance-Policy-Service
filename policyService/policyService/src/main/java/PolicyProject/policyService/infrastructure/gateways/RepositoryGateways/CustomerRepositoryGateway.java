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

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    public CompletableFuture<Customer> create(Customer customer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Customer entity = customerRepository.save(customer);
                updateTotalCount();
                cacheCustomer(entity);
                return entity;
            } catch (DataIntegrityViolationException e) {
                throw new DuplicateTcknException("TCKN is Duplicate", customer.getTckn());
            }
        });
    }


    @Cacheable(value = "customerCache", key = "#customer.tckn")
    public Customer findByTckn(String tckn) {
        return customerRepository.findByTckn(tckn)
                .orElseThrow(() -> new DuplicateTcknException("TCKN not found", tckn));
    }

    @Override
    public CompletableFuture<Customer> get(Customer customer) {
        Customer foundCustomer = findByTckn(customer.getTckn());
        return CompletableFuture.completedFuture(foundCustomer);
    }

    @Override
    public CompletableFuture<Customer> update(Customer newCustomer) {
        return get(newCustomer).thenCompose(existingCustomer -> {
            if (existingCustomer != null) {
                newCustomer.setId(existingCustomer.getId());
                newCustomer.setCarPolicies(existingCustomer.getCarPolicies());
                Customer updatedCustomer = customerRepository.save(newCustomer);
                cacheCustomer(updatedCustomer);
                return CompletableFuture.completedFuture(updatedCustomer);
            } else {
                return CompletableFuture.completedFuture(null);
            }
        }).exceptionally(e -> {
            throw new DuplicateTcknException("TCKN is Duplicate", newCustomer.getTckn());
        });
    }

    @Override
    public CompletableFuture<Customer> delete(Customer customer) {
        return get(customer).thenApply(existingCustomer -> {
            if (existingCustomer != null) {
                customerRepository.delete(existingCustomer);
                updateTotalCount();
                evictCustomerCache(existingCustomer.getTckn());
                return existingCustomer;
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<Customer>> getList(Specification<Customer> specification, int page, int size) {
        return CompletableFuture.supplyAsync(() -> {
            Pageable pageable = PageRequest.of(page, size);
            Page<Customer> customerPage = customerRepository.findAll(specification, pageable);
            return customerPage.getContent();
        });
    }

    @Cacheable("totalCustomer")
    public int getTotal() {
        return (int) customerRepository.count();
    }

    @CacheEvict(value = "totalCustomer", allEntries = true)
    public void updateTotalCount() {}


    @CachePut(value = "customerCache", key = "#customer.tckn")
    public Customer cacheCustomer(Customer customer) {
        return customer;
    }


    @CacheEvict(value = "customerCache", key = "#tckn")
    public void evictCustomerCache(String tckn) {}
}
