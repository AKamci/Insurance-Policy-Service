package PolicyProject.policyService.interfaces.mappers;


import PolicyProject.policyService.domain.dto.request.carPolicyRequest.createCarPolicyRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.deleteCarPolicyRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.getCarPolicyRequest;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.updateCarPolicyRequest;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.createCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.deleteCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.getCarPolicyResponse;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.updateCarPolicyResponse;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.carPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

}
