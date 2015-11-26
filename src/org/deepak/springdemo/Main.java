package org.deepak.springdemo;

import org.deepak.springdemo.db.OfferDAO;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");

        try {
            System.out.println("all offers: " + offerDAO.getOffers());
            System.out.println("html offers: " + offerDAO.getHtmlOffers());
            System.out.println("offer: " + offerDAO.getOffer(1));
        }
        catch (DataAccessException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
            // e.printStackTrace();
        }
    }
}
