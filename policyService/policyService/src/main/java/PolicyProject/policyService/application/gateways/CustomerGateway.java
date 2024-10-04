package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;

public interface CustomerGateway {

    Customer create(Customer Customer);
    Customer get(Customer Customer);
    Customer update(Customer Customer);
    Customer delete(Customer Customer);
    Customer getList(Customer Customer);



}
