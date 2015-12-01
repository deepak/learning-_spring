package org.deepak.springdemo.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DbAppConfig {

    @Bean
    public BasicDataSource dataSource(@Value("${jdbc.driver}")   String driverName,
                                      @Value("${jdbc.url}")      String url,
                                      @Value("${jdbc.user:deepak}")     String userName,
                                      @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    // TODO: @Value("dataSource") does not work here
    // even though it works in OfferDAO
    // @Autowired does work though
    @Bean
    @Autowired
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        System.out.println("datasource: " + dataSource);
        return new DataSourceTransactionManager(dataSource);
    }

    @EventListener
    public void onApplicationRefresh(ContextClosedEvent contextClosedEvent) throws SQLException {
        System.out.println("close datasource");
        // same as
        // this.dataSource(<arguments>) as it has singleton scope
        // but did not want to duplicate arguments DI
        BasicDataSource dataSource = (BasicDataSource) contextClosedEvent
                .getApplicationContext()
                .getBean("dataSource");
        dataSource.close();
    }
}
