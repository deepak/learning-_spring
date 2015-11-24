package org.deepak.springdemo;

import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        Shape shape = (Shape) context.getBean("circle");
        MessageSource messageSource = (MessageSource) context.getBean("messageSource");

        shape.draw();

        String welcome = messageSource.getMessage("welcome", null, "default welcome", null);
        System.out.println(welcome);

        LogEvent logEvent = new LogEvent(shape);
        context.publishEvent(logEvent);
    }
}
