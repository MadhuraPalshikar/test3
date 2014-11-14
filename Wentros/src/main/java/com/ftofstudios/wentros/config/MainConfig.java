package com.ftofstudios.wentros.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ftofstudios.wentros.utils.PropertyUtil;






/**
 * Main configuration class for the application.
 * Turns on @Component scanning, loads externalized application.properties, and sets up the database.
 * @author nachiket
 *
 */

@Configuration
@ComponentScan(basePackages = "com.ftofstudios.wentros", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:wentros.properties")
@EnableTransactionManagement
@EnableScheduling
//@EnableMongoRepositories(basePackages="com.cbproductions.censeo.repository")
public class MainConfig {
	
//	private Logger logger = Logger.getLogger(MainConfig.class);
	@Inject
	private Environment environment;
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		if(environment == null)  {
			System.out.println("Null");
			System.out.println("Mod");
		}
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(environment.getProperty("DB_URL"));
		dataSource.setDriverClassName(environment.getProperty("DB_DRIVER"));
		dataSource.setUsername(environment.getProperty("DB_USERNAME"));
		dataSource.setPassword(environment.getProperty("DB_PASSWORD"));
		dataSource.setMaxActive(200);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		dataSource.setMaxWait(10000);
		dataSource.setInitialSize(10);
		dataSource.setTimeBetweenEvictionRunsMillis(5000);
		dataSource.setMinEvictableIdleTimeMillis(30000);		
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new ClassPathResource[] { 
				new ClassPathResource("wentros.properties") };
		propertySources.setLocations(resources);
		propertySources.setIgnoreUnresolvablePlaceholders(true);
		//propertySources.
		return propertySources;
		//return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public static PropertyUtil properties() {
		PropertyUtil propertySources = new PropertyUtil();
		//propertySources.set
		propertySources.setSystemPropertiesMode(PropertyUtil.SYSTEM_PROPERTIES_MODE_NEVER);
		Resource[] resources = new ClassPathResource[] { 
				new ClassPathResource("wentros.properties")};
		propertySources.setLocations(resources);
		propertySources.setIgnoreUnresolvablePlaceholders(true);
		//propertySources.
		return propertySources;
		//return new PropertySourcesPlaceholderConfigurer();
	}
	
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setMaxUploadSize(1048576000);
//		
//		return commonsMultipartResolver;
//	}
	
	/*
	* Use the standard Mongo driver API to create a com.mongodb.Mongo instance.
	*/
	/*@Bean
	public  Mongo mongo() throws UnknownHostException {
		return new 
	}*/
	
	

	
}
