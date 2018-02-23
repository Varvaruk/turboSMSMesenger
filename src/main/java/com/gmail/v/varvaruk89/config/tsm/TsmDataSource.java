package com.gmail.v.varvaruk89.config.tsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableJpaRepositories(basePackages = {"com.gmail.v.varvaruk89.repo.tsm"},
        entityManagerFactoryRef = "db2EntityManager",
        transactionManagerRef = "db2TransactionManager")
public class TsmDataSource {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db2Datasource());
        em.setPackagesToScan(
                new String[]{"com.gmail.v.varvaruk89.entities.tsm"});
        em.setPersistenceUnitName("db2EntityManager");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect",env.getProperty("org.hibernate.dialect.MySQL5Dialect"));
        properties.put("hibernate.show-sql",  env.getProperty("jdbc.show-sql"));
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("db2.hibernate.hbm2ddl.auto"));
       // properties.put("hibernate.id.new_generator_mappings","false");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource db2Datasource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("jdbc.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));


        return dataSource;
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		db2EntityManager().getObject());
        return transactionManager;
    }
}