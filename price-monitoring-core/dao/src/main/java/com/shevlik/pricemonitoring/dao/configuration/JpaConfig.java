package com.shevlik.pricemonitoring.dao.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("classpath:application.properties")
public class JpaConfig {
	
	@Value("${database.driverClassName}")
	private String driverClassName;
	@Value("${database.url}")
	private String databaseUrl;
	@Value("${database.username}")
	private String username;
	@Value("${database.password}")
	private String password;
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.enable_lazy_load_no_trans}")
	private String hibernateEnableLazyLoadNoTrans;
	@Value("${hibernate.use_jdbc_metadata_defaults}")
	private String hibernateUseJdbcMetadataDefaults;
	@Value("${hibernate.format_sql}")
	private String hibernateFormatSql;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(databaseUrl, username, password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource());
		entityManager.setPackagesToScan("com.shevlik.pricemonitoring.model","com.shevlik.pricemonitoring.security.model");
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setJpaProperties(getJpaProperties());
		return entityManager;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	private Properties getJpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		properties.setProperty("hibernate.show_sql", hibernateShowSql);
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.enable_lazy_load_no_trans", hibernateEnableLazyLoadNoTrans);
		properties.setProperty("hibernate.use_jdbc_metadata_defaults", hibernateUseJdbcMetadataDefaults);
		properties.setProperty("hibernate.format_sql", hibernateFormatSql);
		return properties;
	}	

}