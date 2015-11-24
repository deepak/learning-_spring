package org.deepak.springdemo.mailer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BlacklistNotifier {

    @EventListener
    public void processBlacklistEvent(BlacklistEvent blacklistEvent) {
        System.out.println("---> blocked by blacklist: " + blacklistEvent);
    }

    @EventListener
    public void processMailEvent(MailEvent mailEvent) {
        System.out.println("---> email sent: " + mailEvent);
    }

    @EventListener
    public void processEvent(ApplicationEvent event) {
        System.out.println("---> event: " + event);
    }
}
