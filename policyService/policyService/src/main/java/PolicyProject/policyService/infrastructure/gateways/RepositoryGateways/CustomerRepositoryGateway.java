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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    @CachePut(value = "customerCache", key = "#customer.tckn")
    @Async
    public CompletableFuture<Customer> create(Customer customer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Customer entity = customerRepository.save(customer);
                updateTotalCount();
                return entity;
            } catch (DataIntegrityViolationException e) {
                throw new DuplicateTcknException("TCKN is Duplicate", customer.getTckn());
            }
        });
    }

    @Override
    @Cacheable(value = "customerCache", key = "#customer.tckn")
    public CompletableFuture<Customer> get(Customer customer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return customerRepository.findByTckn(customer.getTckn());
            } catch (DataIntegrityViolationException e) {
                throw new DuplicateTcknException("TCKN is Duplicate", customer.getTckn());
            }
        });
    }

    @Override
    @CachePut(value = "customerCache", key = "#customer.tckn")
    public CompletableFuture<Customer> update(Customer newCustomer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                 CompletableFuture<Customer> customerCompletableFuture = get(newCustomer);
                 customerCompletableFuture.thenApply(entity -> Optional.ofNullable(entity).isPresent()
                         ? entity : customerRepository.save(newCustomer));





            } catch (DataIntegrityViolationException e) {
                throw new DuplicateTcknException("TCKN is Duplicate", customer.getTckn());
            }
        });


//        var customer = get(newCustomer);
//        if (customer == null) {
//            return null;
//        }
//        newCustomer.setId(customer.getId());
//        newCustomer.setCarPolicies(customer.getCarPolicies());
//        return customerRepository.save(newCustomer);
    }

    @Override
    @CacheEvict(value = "customerCache", key = "#customer.tckn")
    public CompletableFuture<Customer> delete(Customer Customer) {
        var customer = get(Customer);
        if (customer == null) {
            return null;
        }
        customerRepository.delete(customer);
        updateTotalCount();
        return customer;
    }

    @Override
    @Cacheable(value = "customerCache", key = "#page + '-' + #size")
    public CompletableFuture<List<Customer>> getList(Specification<Customer> specification, int page, int size) {

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
