package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy;

import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.PersonalHealthMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ExecutePersonalHealth {
    private final PersonalHealthGateway personalHealthGateway;
    private final PolicyProject.policyService.application.usecases.ExecuteHealthPolicyWeight executeHealthPolicyWeight;
    private final ExecuteCustomer executeCustomer;

    public PersonalHealthModel ExecuteGetWithCustomer(PersonalHealthModel personalHealthModel) {
        PersonalHealth entity = PersonalHealthMapper.INSTANCE.getPersonalHealthModelToPersonalHealthEntity(personalHealthModel);
        PersonalHealth personalHealthEntity = Optional.ofNullable(personalHealthGateway.getWCustomer(entity))
                .orElseThrow(() -> new EntityNotFoundException(personalHealthModel.id(), "Entity not found"));

        var newModel = PersonalHealthMapper.INSTANCE.getPersonalHealthEntityToPersonalHealthModel(personalHealthEntity);

        return executeHealthPolicyWeight.Get_APersonalHealthModel(newModel,personalHealthModel.coverageCode());
    }

    public PersonalHealthModel ExecuteCreate(PersonalHealthModel personalHealthModel) {
        CustomerModel customerModel = new CustomerModel(null,
                null,personalHealthModel.tckn(),null,null,null, null,0,0, 0, 0, null );
        Customer customer = CustomerMapper.INSTANCE.customerModelToCustomerEntity
                (executeCustomer.executeGet(customerModel));
        PersonalHealth entity = PersonalHealthMapper.INSTANCE.getPersonalHealthModelToPersonalHealthEntity(personalHealthModel);
        var newModel = PersonalHealthMapper.INSTANCE.getPersonalHealthEntityToPersonalHealthModel( personalHealthGateway.create(entity, customer));
        return newModel;
    }

    public PersonalHealthModel ExecuteGetPersonalHealth(PersonalHealthModel personalHealthModel) {
        PersonalHealth entity = PersonalHealthMapper.INSTANCE.getPersonalHealthModelToPersonalHealthEntity(personalHealthModel);
        PersonalHealth personalHealthEntity = Optional.ofNullable(personalHealthGateway.get(entity))
                .orElseThrow(() -> new EntityNotFoundException(personalHealthModel.id(), "Entity not found"));

        var newHouseModel = PersonalHealthMapper.INSTANCE.getPersonalHealthEntityToPersonalHealthModel(personalHealthEntity);

        return newHouseModel;
    }







}
