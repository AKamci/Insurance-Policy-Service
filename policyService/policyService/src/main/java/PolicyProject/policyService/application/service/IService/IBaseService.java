package PolicyProject.policyService.application.service.IService;

import java.util.List;

public interface IBaseService<T,Response> {

    List<Response> getList();
    Response get(T T);
    Response create(T T);
    Response update(T T);
    Response delete(T T);

}
