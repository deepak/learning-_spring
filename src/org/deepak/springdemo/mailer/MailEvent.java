package org.deepak.springdemo.mailer;

import org.springframework.context.ApplicationEvent;

public class MailEvent extends ApplicationEvent {
    public String address, text;

    public MailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    @Override
    public String toString() {
        return "MailEvent{" +
                "address='" + address + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
