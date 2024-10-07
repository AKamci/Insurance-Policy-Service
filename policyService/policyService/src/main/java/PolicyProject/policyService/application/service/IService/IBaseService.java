package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;

import java.util.List;

public interface IBaseService<Response,T> {

    //Iterable<Response> getList();
    Response get(T T);
    Response create(T T);
    Response update(T T);
    Response delete(T T);

}
