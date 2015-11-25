package org.deepak.springdemo.di;

import org.deepak.springdemo.db.OfferDAO;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");
        System.out.println("offers: " + offerDAO.getOffers());
    }
}
