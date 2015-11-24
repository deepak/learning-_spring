package org.deepak.springdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BlacklistNotifier implements ApplicationListener<BlacklistEvent> {

    @Override
    public void onApplicationEvent(BlacklistEvent blacklistEvent) {
        System.out.println("---> blocked by blacklist: " + blacklistEvent);
    }
}
