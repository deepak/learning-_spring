package org.deepak.springdemo.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Triangle triangle(@Qualifier("origin") Point pointA,
                             Point pointB,
                             Point pointC) {
        return new Triangle(pointA, pointB, pointC);
    }
}
