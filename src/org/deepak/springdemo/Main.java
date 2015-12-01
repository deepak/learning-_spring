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
            Offer newOffer1 = new Offer(4, "thanksgiving", "user@example.com", "black friday deal");
            Offer newOffer2 = new Offer(5, "new year", "user@example.com", "for a happy new year");
            List<Offer> offers1 = new ArrayList<Offer>(Arrays.asList(newOffer1, newOffer2));

            Offer newOffer3 = new Offer(4, "thanksgiving", "user@example.com", "black friday deal");
            Offer newOffer4 = new Offer(6, "new year", "user@example.com", "for a happy new year");
            List<Offer> offers2 = new ArrayList<Offer>(Arrays.asList(newOffer3, newOffer4));

            int[] createOffers1 = offerDAO.createOffer(offers1);
            System.out.println("bulk create: " + Arrays.toString(createOffers1));

            int[] createOffers2 = offerDAO.createOffer(offers2);
            System.out.println("bulk create: " + Arrays.toString(createOffers2));
        }
        catch (DataAccessException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
            e.printStackTrace();
        }
    }
}
