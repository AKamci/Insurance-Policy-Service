package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer Customer) {
        return customerRepository.save(Customer);
    }

    @Override
    public Customer get(Customer Customer) {
        return customerRepository.findById(Customer.getId()).orElse(null);
    }

    @Override
    public Customer update(Customer Customer) {
        var customer = customerRepository.findById(Customer.getId()).orElse(null);
        if (customer == null) {
            return null;
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer delete(Customer Customer) {
        var customer = customerRepository.findById(Customer.getId()).orElse(null);
        if (customer == null) {
            return null;
        }
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    public Iterable<Customer> getList() {
        return customerRepository.findAll();
    }


}
