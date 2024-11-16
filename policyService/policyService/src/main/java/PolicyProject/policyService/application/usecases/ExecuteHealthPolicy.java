package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.EarthQuakeGateway;
import PolicyProject.policyService.application.gateways.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.model.*;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.EarthQuakeSpecificationBuild;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.HealthPolicySpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import PolicyProject.policyService.interfaces.mappers.EarthQuakeMapper;
import PolicyProject.policyService.interfaces.mappers.HealthPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.HouseMapper;
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
//        CustomerModel customerModel = new CustomerModel(null,
//                null,healthPolicyModel.tckn(),null,null,null, null,0,0, 0, 0, null );
//
//        Customer customer = CustomerMapper.INSTANCE.customerModelToCustomerEntity
//                (executeCustomer.executeGet(customerModel));
//
//        PersonalHealthModel personalHealthModel = new PersonalHealthModel();
//
//       // House house = HouseMapper.INSTANCE.HouseModelToHouseEntity(executePersonalHealth.ExecuteGetHouse(houseModel));
//
//
//        HealthPolicy EnityObject = healthPolicyGateway.create
//                (HealthPolicyMapper.INSTANCE.healthPolicyModelToHealthPolicyEntity
//                        (healthPolicyModel),customer, house);
//
//        return HealthPolicyMapper.INSTANCE.healthPolicyEntityToHealthPolicyModel(EnityObject);
        return null;
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
//        Specification<HealthPolicy> specification = healthPolicySpecificationBuild.EarthQuakeBuild(HealthPolicyMapper
//                .INSTANCE.earthQuakeModelToEarthQuakePolicyEntity
//                        (earthQuakeModel),earthQuakeModel.tckn());
//
//        int page = earthQuakeModel.page();
//        int size = earthQuakeModel.size();
//        Optional<List<EarthquakePolicy>> EntityList = Optional.ofNullable
//                (healthPolicyGateway.getList(specification, page, size));
//
//        List<EarthquakePolicy> earthquakePolicies = EntityList.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.policyId(),"Entity not found"));
//        return HealthPolicyMapper.INSTANCE.earthQuakePolicyEntityListToEarthQuakeModelList(earthquakePolicies);
        return null;
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
