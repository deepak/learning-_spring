package org.deepak.springdemo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

public class Circle implements Shape {

    private Point center;

    @Resource(name = "pointC")
    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public void draw() {
        System.out.println(this);
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("init circle");
    }

    @PreDestroy
    public void beforeDestroy() {
        System.out.println("destroying circle");
    }

    @Override
    public String toString() {
        return "org.deepak.springdemo.Circle{" +
                "center=" + center +
                '}';
    }
}
