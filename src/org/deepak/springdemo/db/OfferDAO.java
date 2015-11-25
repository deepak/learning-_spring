package org.deepak.springdemo.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OfferDAO {
    private JdbcTemplate jdbc;

    public List<Offer> getOffers() {
        return jdbc.query("select id, name, email, text from offers", new RowMapper<Offer>() {
            @Override
            public Offer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String text = resultSet.getString("text");
                Offer offer = new Offer(id, name, email, text);
                return offer;
            }
        });
    }

    @Resource
    public void setDataSource(@Value("dataSource") DataSource jdbc) {
        this.jdbc = new JdbcTemplate(jdbc);
    }
}
