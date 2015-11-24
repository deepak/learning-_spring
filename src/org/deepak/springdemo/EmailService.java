package org.deepak.springdemo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EmailService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    private List<String> blacklists;

    @Resource(name = "spammers")
    public void setBlacklists(List<String> blacklists) {
        this.blacklists = blacklists;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendEmail(String address, String text) {
        if (blacklists.contains(address)) {
            BlacklistEvent blacklistEvent = new BlacklistEvent(this, address);
            publisher.publishEvent(blacklistEvent);
        } else {
            System.out.println("emailing " + text);
        }
    }
}
