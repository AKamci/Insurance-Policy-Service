package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.interfaces.mappers.HouseMapper;
import PolicyProject.policyService.interfaces.mappers.PersonalHealthMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ExecutePersonalHealth {
    private final PersonalHealthGateway personalHealthGateway;
    private final ExecuteHealthPolicyWeight executeHealthPolicyWeight;

    public PersonalHealthModel ExecuteGetWithCustomer(PersonalHealthModel personalHealthModel) {
        PersonalHealth entity = PersonalHealthMapper.INSTANCE.getPersonalHealthModelToPersonalHealthEntity(personalHealthModel);
        PersonalHealth personalHealthEntity = Optional.ofNullable(personalHealthGateway.getWCustomer(entity))
                .orElseThrow(() -> new EntityNotFoundException(personalHealthModel.id(), "Entity not found"));

        var newModel = PersonalHealthMapper.INSTANCE.getPersonalHealthEntityToPersonalHealthModel(personalHealthEntity);

        return executeHealthPolicyWeight.Get_APersonalHealthModel(newModel);
    }

    public PersonalHealthModel ExecuteGetPersonalHealth(PersonalHealthModel personalHealthModel) {
        PersonalHealth entity = PersonalHealthMapper.INSTANCE.getPersonalHealthModelToPersonalHealthEntity(personalHealthModel);
        PersonalHealth personalHealthEntity = Optional.ofNullable(personalHealthGateway.get(personalHealthModel.tckn()))
                .orElseThrow(() -> new EntityNotFoundException(personalHealthModel.id(), "Entity not found"));

        var newHouseModel = PersonalHealthMapper.INSTANCE.getPersonalHealthEntityToPersonalHealthModel(personalHealthEntity);

        return newHouseModel;
    }







}
