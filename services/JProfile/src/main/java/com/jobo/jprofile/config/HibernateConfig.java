package com.jobo.jprofile.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.jobo.jprofile.config" })
@PropertySource(value = { "classpath:services.properties" })
public class HibernateConfig {


    @Autowired
    private Environment m_environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.jobo.jprofile.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource l_dataSource = new DriverManagerDataSource();
        l_dataSource.setDriverClassName(m_environment.getRequiredProperty("jdbc.driverClassName"));
        l_dataSource.setUrl(m_environment.getRequiredProperty("jdbc.url"));
        l_dataSource.setUsername(m_environment.getRequiredProperty("jdbc.username"));
        l_dataSource.setPassword(m_environment.getRequiredProperty("jdbc.password"));
        return l_dataSource;
    }
    
    private Properties hibernateProperties() {
        Properties l_properties = new Properties();
        l_properties.put("hibernate.dialect", m_environment.getRequiredProperty("hibernate.dialect"));
        l_properties.put("hibernate.show_sql", m_environment.getRequiredProperty("hibernate.show_sql"));
        l_properties.put("hibernate.format_sql", m_environment.getRequiredProperty("hibernate.format_sql"));
        return l_properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager l_txManager = new HibernateTransactionManager();
       l_txManager.setSessionFactory(s);
       return l_txManager;
    }
}
