package org.deepak.springdemo.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class OfferDAO {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Offer> rowMapper;
    private final String insertSql = "INSERT INTO offers (name, email, text) VALUES (:name, :email, :text)";


    public OfferDAO() {
        this.rowMapper = new RowMapper<Offer>() {
            @Override
            public Offer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String text = resultSet.getString("text");
                Offer offer = new Offer(id, name, email, text);
                return offer;
            }
        };
    }

    public List<Offer> getOffers() {
        return jdbc.query("select id, name, email, text from offers",
                rowMapper);
    }

    public List<Offer> getHtmlOffers() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "html");
        params.addValue("email", "user1@example.com");

        // how will sql injection work here ?
        // query: "select id, name, email, text from offers where id='1'; delete from offers where id='2'"
        // returns an error
        // class org.springframework.dao.DataIntegrityViolationException StatementCallback; Multiple ResultSets were returned by the query.;
        // nested exception is org.postgresql.util.PSQLException: Multiple ResultSets were returned by the query.
        return jdbc.query("select id, name, email, text from offers where name = :name and email = :email",
                params, rowMapper);
    }

    public Offer getOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select id, name, email, text from offers where id = :id",
                params, rowMapper);
    }

    public boolean deleteOffer(int id) {
        // how to do "delete IN" ?
        // delete from offers where id IN (2, 3)
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        int deletedCount = jdbc.update("delete from offers where id = :id", params);
        return deletedCount == 1;
    }


    public Optional<Offer> createOffer(Offer offer) {
        return updateWithSql(offer, insertSql);
    }

    @Transactional
    public int[] createOffer(List<Offer> offers) {
        SqlParameterSource[] params =
                SqlParameterSourceUtils.createBatch(offers.toArray());
        return jdbc.batchUpdate("INSERT INTO offers (id, name, email, text) VALUES (:id, :name, :email, :text)", params);
//        for (Offer o: offers) {
//            createOffer(o);
//        }
    }

    // without the @Transactional annotation, the first batchUpdate (in createOffer) will succeed
    // and the second with fail, but db will have 2 records. with this annotation however
    // db inserts will be transactional and no records will be inserted
    // this annotation is sufficient, do not need it on createOffer as well
    @Transactional
    public void createOffersInBatches() {
        Offer newOffer1 = new Offer(4, "thanksgiving", "user@example.com", "black friday deal");
        Offer newOffer2 = new Offer(5, "new year", "user@example.com", "for a happy new year");
        List<Offer> offers1 = new ArrayList<Offer>(Arrays.asList(newOffer1, newOffer2));

        Offer newOffer3 = new Offer(4, "thanksgiving", "user@example.com", "black friday deal");
        Offer newOffer4 = new Offer(6, "new year", "user@example.com", "for a happy new year");
        List<Offer> offers2 = new ArrayList<Offer>(Arrays.asList(newOffer3, newOffer4));

        int[] createOffers1 = createOffer(offers1);
        System.out.println("bulk create: " + Arrays.toString(createOffers1));

        int[] createOffers2 = createOffer(offers2);
        System.out.println("bulk create: " + Arrays.toString(createOffers2));
    }

    // TODO: bad api. offer.id should not be nil. how to enforce this ?
    public Optional<Offer> updateOffer(Offer offer) {
        String sql = "UPDATE offers SET name=:name, email=:email, text=:text WHERE id=:id";
        return updateWithSql(offer, sql);
    }

    private Optional<Offer> updateWithSql(Offer offer, String sql) {
        KeyHolder holder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
        int updateCount = jdbc.update(sql, params, holder);
        boolean updateStatus = updateCount == 1;
        return offerFromKeyHolder(holder, updateStatus);
    }

    private Optional<Offer> offerFromKeyHolder(KeyHolder holder, boolean status) {
        Offer offer = null;

        if (status) {
            Map<String, Object> keys = holder.getKeys();
            Integer id = (Integer) keys.get("id");
            String name = (String) keys.get("name");
            String email = (String) keys.get("email");
            String text = (String) keys.get("text");
            offer = new Offer(id, name, email, text);
        }

        Optional<Offer> offerMaybe = Optional.ofNullable(offer);
        return offerMaybe;
    }

    @Resource
    public void setDataSource(@Value("dataSource") DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }
}
