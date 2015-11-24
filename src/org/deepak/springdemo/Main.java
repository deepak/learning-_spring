package org.deepak.springdemo;

import org.deepak.springdemo.mailer.EmailService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        Shape shape = (Shape) context.getBean("circle");
        LogEvent logEvent = new LogEvent(shape);
        context.publishEvent(logEvent);

        EmailService emailService = (EmailService) context.getBean("emailService");
        emailService.sendEmail("spammer1@example.com", "goodbye spam");
        emailService.sendEmail("user@example.com", "hello");
    }
}
