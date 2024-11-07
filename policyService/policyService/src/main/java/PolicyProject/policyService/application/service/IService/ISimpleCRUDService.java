package PolicyProject.policyService.application.service.IService;

import java.util.List;

public interface ISimpleCRUDService<
        CreateResponse,
        UpdateResponse,
        DeleteResponse,
        GetResponse,
        ListResponse,
        T
        > {
    List<ListResponse> getList();
    GetResponse get(T entity);
    CreateResponse create(T entity);
    UpdateResponse update(T entity);
    DeleteResponse delete(T entity);
}