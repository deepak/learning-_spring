package org.deepak.springdemo;

import org.deepak.springdemo.db.Offer;
import org.deepak.springdemo.db.OfferDAO;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");

        try {
            Offer newOffer1 = new Offer(3, "thanksgiving", "user@example.com", "black friday deal");
            Offer newOffer2 = new Offer(2, "new year", "user@example.com", "for a happy new year");
            List<Offer> offers = new ArrayList<Offer>(Arrays.asList(newOffer1, newOffer2));

            int[] createOffers = offerDAO.createOffer(offers);
            System.out.println("bulk create: " + Arrays.toString(createOffers));
        }
        catch (DataAccessException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
            e.printStackTrace();
        }
    }
}
