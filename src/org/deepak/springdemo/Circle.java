package org.deepak.springdemo;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class Circle implements Shape {

    private Point center;
    private MessageSource messageSource;

    @Resource
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Resource(name = "pointC")
    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public void draw() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String message = messageSource.getMessage("circle.draw",
                new Object[] { center.getX(), center.getX() },
                Locale.FRENCH);
        return message;
    }
}
