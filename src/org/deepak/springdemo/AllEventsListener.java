package org.deepak.springdemo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AllEventsListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof BlacklistEvent) {
            System.out.println("===> Blacklist is handled in its listener");
        } else {
            System.out.println("===> Event: " + applicationEvent);
        }
    }

}
