package com.feiyin.statemachine;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author 非音
 * @date 2018/10/29 - 下午2:40
 */
@WithStateMachine
public class MyBean {

    @OnTransition(target = "STATE1")
    void toState1() {
    }

    @OnTransition(target = "STATE2")
    void toState2() {
    }
}
