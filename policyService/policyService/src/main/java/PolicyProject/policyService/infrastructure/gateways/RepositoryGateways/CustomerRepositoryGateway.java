package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.infrastructure.exception.DuplicateTcknException;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@RequiredArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateTcknException("TCKN is Duplicate", customer.getTckn());
        }
    }

    @Override
    public Customer get(Customer Customer) {
        var customer = customerRepository.findByTckn(Customer.getTckn());
        return customer;
    }

    @Override
    public Customer update(Customer newCustomer) {
        var customer = get(newCustomer);
        if (customer == null) {
            return null;
        }
        newCustomer.setId(customer.getId());
        newCustomer.setCarPolicies(customer.getCarPolicies());
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer delete(Customer Customer) {
        var customer = get(Customer);
        if (customer == null) {
            return null;
        }
        customerRepository.delete(customer);

        return customer;
    }

    @Override
    public List<Customer> getList(Specification<Customer> specification, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findAll(specification, pageable);
        return customerPage.getContent();
    }

    public int getTotal() {
        return (int) customerRepository.count();
    }


}
