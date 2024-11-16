package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.application.gateways.PersonalHealthGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutePersonalHealth {
    private final PersonalHealthGateway personalHealthGateway;
    private final ExecuteHealthPolicyWeight executeHealthPolicyWeight;

}
