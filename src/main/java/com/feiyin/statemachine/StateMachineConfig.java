package com.feiyin.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author 非音
 * @date 2018/10/29 - 下午3:13
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig
    extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
        throws Exception {
        config
            .withConfiguration()
            .autoStartup(true)
            .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
        throws Exception {
        states
            .withStates()
            .initial(States.start)
            .states(EnumSet.allOf(States.class));
    }

    /**
     * Configuring Transitions
     * @author 非音
     * @date 2018/10/29 - 下午5:05
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
        throws Exception {

        transitions
            .withExternal()
            .source(States.start).target(States.userNameChecked).event(Events.checkUserName)
            .and()
            .withExternal()
            .source(States.userNameChecked).target(States.smsSended).event(Events.sendSms).action(action())
            .and()
            .withExternal()
            .source(States.smsSended).target(States.smsChecked).event(Events.checkSms)
            .and()
            .withExternal()
            .source(States.smsChecked).target(States.end).event(Events.register);
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }

            @Override
            public void stateContext(StateContext<States, Events> stateContext) {
                super.stateContext(stateContext);
            }
        };
    }

    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                // check userName

                //throw new NullPointerException();


            }
        };
    }

}
