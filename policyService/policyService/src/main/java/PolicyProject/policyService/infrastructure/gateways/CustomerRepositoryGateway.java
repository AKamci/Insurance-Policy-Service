package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer Customer) {
        return customerRepository.save(Customer);
    }

    @Override
    public Customer get(Customer Customer) {
        return customerRepository.findByTckn(Customer.getTckn());
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
    public List<Customer> getList() {
        Iterable<Customer> iterable = customerRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }


}
