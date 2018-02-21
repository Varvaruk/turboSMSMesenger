package com.gmail.v.varvaruk89.config.sms;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = "com.gmail.v.varvaruk89.repo.sms",
        entityManagerFactoryRef = "smsEntityManagerFactory",
        transactionManagerRef = "smsTransactionManager")
@PropertySource("classpath:smsdb.properties")
public class SmsDatabaseConfig {
   // @Value("${sms.jdbc.driver}")
    //private String jdbcDriver;
    @Value("${sms.jdbc.url}")
    private String jdbcURL;
    @Value("${sms.jdbc.username}")
    private String jdbcUsername;
    @Value("${sms.jdbc.password}")
    private String jdbcPassword;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String sqlDialect;

    @Bean
    public LocalContainerEntityManagerFactoryBean smsEntityManagerFactory(
            @Qualifier("smsDataSource") DataSource dataSource,
            @Qualifier("smsJpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan("com.gmail.v.varvaruk89.entities.sms");
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager smsTransactionManager(
            @Qualifier("smsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter smsJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        return adapter;
    }

    @Bean
    public DataSource smsDataSource() {

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(jdbcURL);
        mysqlDS.setUser(jdbcUsername);
        mysqlDS.setPassword(jdbcPassword);
        //  return DbConfig.createDataSource(jdbcDriver, jdbcURL, jdbcUsername, jdbcPassword);

        return mysqlDS;
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }
}