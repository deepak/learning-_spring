package org.deepak.springdemo.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OfferDAO {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Offer> rowMapper;

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

        // how will sql injection work here
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

    public boolean createOffer(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
        int insertCount = jdbc.update("insert into offers (name, email, text) values (:name, :email, :text)",
                params);
        return insertCount == 1;
    }

    @Resource
    public void setDataSource(@Value("dataSource") DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }
}
