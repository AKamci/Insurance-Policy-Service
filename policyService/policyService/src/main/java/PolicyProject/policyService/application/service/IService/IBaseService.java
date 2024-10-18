package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;

import java.util.List;

public interface IBaseService<
        CreateResponse,
        UpdateResponse,
        DeleteResponse,
        GetResponse,
        ListResponse,
        T
        > {
    List<ListResponse> getList(T entity);
    GetResponse get(T entity);
    CreateResponse create(T entity);
    UpdateResponse update(T entity);
    DeleteResponse delete(T entity);
}
