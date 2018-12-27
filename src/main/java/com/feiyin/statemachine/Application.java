package com.feiyin.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

/**
 * @author 非音
 * @date 2018/10/29 - 下午3:10
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    StateMachine<States, Events> stateMachine;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.checkUserName);
        stateMachine.sendEvent(Events.sendSms);
        stateMachine.sendEvent(Events.checkSms);
        stateMachine.sendEvent(Events.register);
    }

}
