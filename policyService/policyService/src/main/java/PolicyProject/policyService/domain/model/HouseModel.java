package PolicyProject.policyService.domain.model;

import PolicyProject.policyService.infrastructure.persistence.entity.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import jakarta.persistence.*;

public record HouseModel(

        Long id,
        Integer number,
        Integer squareMeters,
        Customer customer,
        Building building,
        String tckn,
        Long Amount
) {


}
