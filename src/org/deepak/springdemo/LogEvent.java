package org.deepak.springdemo;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {
    public LogEvent(Object source) {
        super(source);
    }
}
