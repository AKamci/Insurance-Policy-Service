package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.CustomerModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerModelFactory {
    public static CustomerModel createCustomerModelWithTckn(String tckn) {
        return new CustomerModel(null, null, tckn, null, null, null, null, 0, 0, 0, 0, null);
    }

}
