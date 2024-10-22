package PolicyProject.policyService.application.service.IService;

import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IBaseService<
        CreateResponse,
        UpdateResponse,
        DeleteResponse,
        GetResponse,
        ListResponse,
        T
        > {
    CompletableFuture<List<ListResponse>> getList(T entity);
    CompletableFuture<GetResponse> get(T entity);
    CompletableFuture<CreateResponse> create(T entity);
    CompletableFuture<UpdateResponse> update(T entity);
    CompletableFuture<DeleteResponse> delete(T entity);
    CompletableFuture<Integer> getTotalRecord();
}
