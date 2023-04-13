package ru.gb.springboot.patterns.behavioral;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomSpringEvent extends ApplicationEvent {

    private String msg;

    public CustomSpringEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
