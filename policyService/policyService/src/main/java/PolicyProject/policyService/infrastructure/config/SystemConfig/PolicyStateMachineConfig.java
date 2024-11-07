package PolicyProject.policyService.infrastructure.config.SystemConfig;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEvent;
import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
public class PolicyStateMachineConfig extends EnumStateMachineConfigurerAdapter<CarPolicyState, CarPolicyEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<CarPolicyState, CarPolicyEvent> states) throws Exception {
        states
                .withStates()
                .initial(CarPolicyState.CREATED)
                .state(CarPolicyState.ACTIVE)
                .state(CarPolicyState.EXPIRED)
                .state(CarPolicyState.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<CarPolicyState, CarPolicyEvent> transitions) throws Exception {
        transitions
                .withExternal().source(CarPolicyState.CREATED).target(CarPolicyState.ACTIVE).event(CarPolicyEvent.ACTIVATE)
                .and()
                .withExternal().source(CarPolicyState.ACTIVE).target(CarPolicyState.EXPIRED).event(CarPolicyEvent.EXPIRE)
                .and()
                .withExternal().source(CarPolicyState.ACTIVE).target(CarPolicyState.CANCELLED).event(CarPolicyEvent.CANCEL);
    }
}