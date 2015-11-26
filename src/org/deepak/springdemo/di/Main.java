package org.deepak.springdemo.di;

import org.deepak.springdemo.db.OfferDAO;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        try {
            OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");
            System.out.println("offers: " + offerDAO.getOffers());
        }
        catch (CannotGetJdbcConnectionException ce) {
            System.out.println(ce.getMessage());
        }
        catch (DataAccessException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
            // e.printStackTrace();
        }
    }
}
