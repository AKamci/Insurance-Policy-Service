package PolicyProject.policyService.application.usecases.ExecutePolicy;

import PolicyProject.policyService.application.gateways.HealthPolicyGateway;
import PolicyProject.policyService.application.service.ModelFactory.CustomerModelFactory;
import PolicyProject.policyService.application.service.ModelFactory.PersonalHealthModelFactory;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.HealthPolicy.ExecutePersonalHealth;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.model.*;
import PolicyProject.policyService.domain.model.HealthPolicyModel;
import PolicyProject.policyService.domain.model.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.HealthPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.interfaces.mappers.*;
import PolicyProject.policyService.interfaces.mappers.HealthPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.PersonalHealthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class ExecuteHealthPolicy {

    private final HealthPolicyGateway healthPolicyGateway;
    private final ExecuteCustomer executeCustomer;
    private final HealthPolicySpecificationBuild healthPolicySpecificationBuild;
    private final ExecutePersonalHealth executePersonalHealth;


    public HealthPolicyModel executeUpdate(HealthPolicyModel healthPolicyModel)
    {
        Optional<HealthPolicy> optionalEntity = Optional.ofNullable
                (healthPolicyGateway.update(HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)));
        HealthPolicy healthPolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(healthPolicyModel.customerId(),"Entity not found"));
        return HealthPolicyMapper.INSTANCE.healthPolicyEntityToHealthPolicyModel(healthPolicy);
    }

    public HealthPolicyModel executeCreate(HealthPolicyModel healthPolicyModel)
    {
        Customer customer = CustomerMapper.INSTANCE.customerModelToCustomerEntity
                (executeCustomer.executeGet(CustomerModelFactory.createCustomerModelWithTckn(healthPolicyModel.tckn())));
        PersonalHealth personalHealth = PersonalHealthMapper.INSTANCE.getPersonalHealthModelToPersonalHealthEntity
                (executePersonalHealth.ExecuteGetPersonalHealth
                        (PersonalHealthModelFactory.createPersonalHealthModelWithHealthIdAndTckn
                                (healthPolicyModel.personalHealthId(), healthPolicyModel.tckn())));

        HealthPolicy EnityObject = healthPolicyGateway.create
                (HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity
                        (healthPolicyModel),customer, personalHealth);

        return HealthPolicyMapper.INSTANCE.healthPolicyEntityToHealthPolicyModel(EnityObject);
    }

    public HealthPolicyModel executeGet(HealthPolicyModel healthPolicyModel)
    {
        Optional<HealthPolicy> optionalEntity = Optional.ofNullable
                (healthPolicyGateway.get(HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)));
        HealthPolicy healthPolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(healthPolicyModel.customerId(),"Entity not found"));
        return HealthPolicyMapper.INSTANCE.healthPolicyEntityToHealthPolicyModel(healthPolicy);
    }


    public HealthPolicyModel executeDelete(HealthPolicyModel healthPolicyModel)
    {
        Optional<HealthPolicy> optionalEntity = Optional.ofNullable
                (healthPolicyGateway.delete(HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity(healthPolicyModel)));
        HealthPolicy healthPolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(healthPolicyModel.customerId(),"Entity not found"));
        return HealthPolicyMapper.INSTANCE.healthPolicyEntityToHealthPolicyModel(healthPolicy);
    }

    public List<HealthPolicyModel> executeGet_wPolicy(HealthPolicyModel healthPolicyModel)
    {
        String tckn = healthPolicyModel.tckn();

        Optional<List<HealthPolicy>> EntityList = Optional.ofNullable
                (healthPolicyGateway.getCarPoliciesByCustomer(tckn));

        List<HealthPolicy> earthquakePolicies = EntityList.orElseThrow(() -> new EntityNotFoundException(healthPolicyModel.policyId(),"Entity not found"));
        return HealthPolicyMapper.INSTANCE.healthPolicyEntityListToHealthPolicyModelList(earthquakePolicies);
    }

    public List<HealthPolicyModel> executeGetList(HealthPolicyModel healthPolicyModel)
    {
        Specification<HealthPolicy> specification = healthPolicySpecificationBuild.HealthPolicyBuild(HealthPolicyMapper
                .INSTANCE.healthPolicyModelToHealthPolicyEntity
                        (healthPolicyModel),healthPolicyModel.tckn());

        int page = healthPolicyModel.page();
        int size = healthPolicyModel.size();
        Optional<List<HealthPolicy>> EntityList = Optional.ofNullable
                (healthPolicyGateway.getList(specification, page, size));

        List<HealthPolicy> healthPolicyList = EntityList.orElseThrow(() -> new EntityNotFoundException(healthPolicyModel.policyId(),"Entity not found"));
        return HealthPolicyMapper.INSTANCE.healthPolicyEntityListToHealthPolicyModelList(healthPolicyList);
    }


    public int executeGetTotalRecord()
    {
        return healthPolicyGateway.getTotal();
    }


    @Transactional
    public HealthPolicyModel changeCarPolicyState(HealthPolicyModel healthPolicyModel, PolicyEvent event) {

        Optional<HealthPolicy> optionalEntity = Optional.empty();
        if (event == PolicyEvent.CANCEL)
        {
            optionalEntity = Optional.ofNullable
                    (healthPolicyGateway.SetStateCarPolicy(HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity(healthPolicyModel), PolicyState.CANCELLED));
        }
        else if (event == PolicyEvent.ACTIVATE) {
            optionalEntity = Optional.ofNullable
                    (healthPolicyGateway.SetStateCarPolicy(HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity(healthPolicyModel), PolicyState.ACTIVE));
        }
        else
        { new IllegalStateException();}
        HealthPolicy healthPolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(healthPolicyModel.customerId(),"Entity not found"));
        return HealthPolicyMapper.INSTANCE.healthPolicyEntityToHealthPolicyModel(healthPolicy);

    }
}
