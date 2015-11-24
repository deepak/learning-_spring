package org.deepak.springdemo.di;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {
    public LogEvent(Object source) {
        super(source);
    }
}
