package org.deepak.springdemo;

import org.springframework.context.ApplicationEvent;

public class BlacklistEvent extends ApplicationEvent {
    private final String address;

    public BlacklistEvent(Object source, String address) {
        super(source);
        this.address = address;
    }

    @Override
    public String toString() {
        return "BlacklistEvent{" +
                "address='" + address + '\'' +
                '}';
    }
}
