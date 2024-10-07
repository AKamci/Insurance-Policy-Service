package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.request.carPolicyRequest.ICarPolicyRequest;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.ICarPolicyResponse;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;

public interface ICarPolicyService extends IBaseService<ICarPolicyResponse,carPolicyModel>{

}
