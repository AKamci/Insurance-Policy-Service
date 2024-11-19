package PolicyProject.policyService.application.usecases.ExecutePolicy;

import PolicyProject.policyService.application.gateways.PolicyGateway.EarthQuakeGateway;
import PolicyProject.policyService.application.service.ModelFactory.CustomerModelFactory;
import PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.EarthquakePolicy.ExecuteHouse;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.EarthQuakeSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.*;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.interfaces.mappers.*;

import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.EarthQuakeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteEarthQuakePolicy {

    private final EarthQuakeGateway earthQuakeGateway;
    private final ExecuteCustomer executeCustomer;
    private final EarthQuakeSpecificationBuild earthQuakeSpecificationBuild;
    private final ExecuteHouse executeHouse;


    public EarthQuakeModel executeUpdate(EarthQuakeModel earthQuakeModel)
    {
        Optional<EarthquakePolicy> optionalEntity = Optional.ofNullable
                (earthQuakeGateway.update(EarthQuakeMapper.INSTANCE.earthQuakeModelToEarthQuakePolicyEntity(earthQuakeModel)));
        EarthquakePolicy earthquakePolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.customerId(),"Entity not found"));
        return EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityToEarthQuakeModel(earthquakePolicy);
    }

    public EarthQuakeModel executeCreate(EarthQuakeModel earthQuakeModel)
    {
        Customer customer = CustomerMapper.INSTANCE.customerModelToCustomerEntity
                (executeCustomer.executeGet(CustomerModelFactory.createCustomerModelWithTckn(earthQuakeModel.tckn())));
        House house = HouseMapper.INSTANCE.HouseModelToHouseEntity(executeHouse.ExecuteGetHouse(HouseModelFactory.createHouseModelWithHouseId(earthQuakeModel.houseId())));
        EarthquakePolicy EnityObject = earthQuakeGateway.create
                (EarthQuakeMapper.INSTANCE.earthQuakeModelToEarthQuakePolicyEntity
                        (earthQuakeModel),customer, house);
        return EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityToEarthQuakeModel(EnityObject);
    }

    public EarthQuakeModel executeGet(EarthQuakeModel earthQuakeModel)
    {
        Optional<EarthquakePolicy> optionalEntity = Optional.ofNullable
                (earthQuakeGateway.get(EarthQuakeMapper.INSTANCE.earthQuakeModelToEarthQuakePolicyEntity(earthQuakeModel)));
        EarthquakePolicy earthquakePolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.customerId(),"Entity not found"));
        return (EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityToEarthQuakeModel(earthquakePolicy));
    }


    public EarthQuakeModel executeDelete(EarthQuakeModel earthQuakeModel)
    {
        Optional<EarthquakePolicy> optionalEntity = Optional.ofNullable
                (earthQuakeGateway.delete(EarthQuakeMapper.INSTANCE.earthQuakeModelToEarthQuakePolicyEntity(earthQuakeModel)));
        EarthquakePolicy earthquakePolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.customerId(),"Entity not found"));
        return EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityToEarthQuakeModel(earthquakePolicy);
    }

    public List<EarthQuakeModel> executeGet_wPolicy(EarthQuakeModel earthQuakeModel)
    {
        String tckn = earthQuakeModel.tckn();

        Optional<List<EarthquakePolicy>> EntityList = Optional.ofNullable
                (earthQuakeGateway.getCarPoliciesByCustomer(tckn));

        List<EarthquakePolicy> earthquakePolicies = EntityList.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.policyId(),"Entity not found"));
        return EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityListToEarthQuakeModelList(earthquakePolicies);
    }

    public List<EarthQuakeModel> executeGetList(EarthQuakeModel earthQuakeModel)
    {
        Specification<EarthquakePolicy> specification = earthQuakeSpecificationBuild.EarthQuakeBuild(EarthQuakeMapper
                .INSTANCE.earthQuakeModelToEarthQuakePolicyEntity
                        (earthQuakeModel),earthQuakeModel.tckn());

        int page = earthQuakeModel.page();
        int size = earthQuakeModel.size();
        Optional<List<EarthquakePolicy>> EntityList = Optional.ofNullable
                (earthQuakeGateway.getList(specification, page, size));

        List<EarthquakePolicy> earthquakePolicies = EntityList.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.policyId(),"Entity not found"));
        return EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityListToEarthQuakeModelList(earthquakePolicies);

    }


    public int executeGetTotalRecord()
    {
        return earthQuakeGateway.getTotal();
    }


    @Transactional
    public EarthQuakeModel changeCarPolicyState(EarthQuakeModel earthQuakeModel, PolicyEvent event) {

        Optional<EarthquakePolicy> optionalEntity = Optional.empty();
        if (event == PolicyEvent.CANCEL)
        {
            optionalEntity = Optional.ofNullable
                    (earthQuakeGateway.SetStateCarPolicy(EarthQuakeMapper.INSTANCE.earthQuakeModelToEarthQuakePolicyEntity(earthQuakeModel), PolicyState.CANCELLED));
        }
        else if (event == PolicyEvent.ACTIVATE) {
            optionalEntity = Optional.ofNullable
                    (earthQuakeGateway.SetStateCarPolicy(EarthQuakeMapper.INSTANCE.earthQuakeModelToEarthQuakePolicyEntity(earthQuakeModel), PolicyState.ACTIVE));
        }
        else
        { new IllegalStateException();}
        EarthquakePolicy earthquakePolicy = optionalEntity.orElseThrow(() -> new EntityNotFoundException(earthQuakeModel.customerId(),"Entity not found"));
        return EarthQuakeMapper.INSTANCE.earthQuakePolicyEntityToEarthQuakeModel(earthquakePolicy);

    }
}
