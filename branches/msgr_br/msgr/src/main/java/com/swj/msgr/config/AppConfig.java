package com.swj.msgr.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.utility.HtmlEscape;
import freemarker.template.utility.XmlEscape;

//@Configuration
//@ComponentScan(basePackages = {"com.swj.msgr"})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@PropertySource(value="classpath:config.properties")
public class AppConfig {
	
	@Autowired
	Environment env;

	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		basicDataSource.setUrl(env.getProperty("jdbc.url"));
		basicDataSource.setUsername(env.getProperty("jdbc.username"));
		basicDataSource.setPassword(env.getProperty("jdbc.password"));
		basicDataSource.setMaxActive(20);
		basicDataSource.setMaxIdle(5);
		basicDataSource.setValidationQuery("select 2");
		basicDataSource.setTestOnBorrow(true);
		basicDataSource.setTestWhileIdle(true);
		basicDataSource.setTimeBetweenEvictionRunsMillis(10000);
		basicDataSource.setMinEvictableIdleTimeMillis(60000);
		
		return basicDataSource;
	}
		
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(new Resource[] {new ClassPathResource("mybatis/mapper/*.xml")});
		
		return sqlSessionFactoryBean.getObject();
	}
	
//	static @Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setBasePackage("com.swj.msgr.member.dao;com.swj.msgr.app.dao;com.swj.msgr.message.dao");
//		return mapperScannerConfigurer;
//	}
	
	
	
	@Bean
	public freemarker.template.Configuration freeMarkerConfigurationFactoryBean() {
		
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
		
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/WEB-INF/freemarker");
		Properties settings = new Properties();
		settings.setProperty("output_encoding", "utf-8");
		settings.setProperty("number_format", "#");
		settings.setProperty("whitespace_stripping", "true");

		freeMarkerConfigurationFactoryBean.setFreemarkerSettings(settings);
		
		freeMarkerConfigurationFactoryBean.setDefaultEncoding("UTF-8");
		

		Map<String, Object> urls = new HashMap<String, Object>();
		urls.put("base", env.getProperty("url.base"));
		urls.put("js", env.getProperty("url.js"));
		urls.put("css", env.getProperty("url.css"));
		urls.put("img", env.getProperty("url.img"));
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("xml_escape", XmlEscape.class);
		variables.put("html_escape", HtmlEscape.class);
		variables.put("url", urls);
		
		freeMarkerConfigurationFactoryBean.setFreemarkerVariables(variables);
		
		return freeMarkerConfigurationFactoryBean.getObject();
	}

	
	@Bean(name="viewResolver")
	public ViewResolver freeMarkerViewResolver() {
		
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
		freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
		freeMarkerViewResolver.setCache(false);
		freeMarkerViewResolver.setExposeSpringMacroHelpers(true);
		freeMarkerViewResolver.setRequestContextAttribute("rc");
		freeMarkerViewResolver.setPrefix("/");
		freeMarkerViewResolver.setSuffix(".ftl");
		
		return freeMarkerViewResolver;
	}
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		
		
		
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
		
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/WEB-INF/freemarker");
		Properties settings = new Properties();
		settings.setProperty("output_encoding", "utf-8");
		settings.setProperty("number_format", "#");
		settings.setProperty("whitespace_stripping", "true");

		freeMarkerConfigurationFactoryBean.setFreemarkerSettings(settings);
		
		freeMarkerConfigurationFactoryBean.setDefaultEncoding("UTF-8");
		

		Map<String, Object> urls = new HashMap<String, Object>();
		urls.put("base", env.getProperty("url.base"));
		urls.put("js", env.getProperty("url.js"));
		urls.put("css", env.getProperty("url.css"));
		urls.put("img", env.getProperty("url.img"));
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("xml_escape", XmlEscape.class);
		variables.put("html_escape", HtmlEscape.class);
		variables.put("url", urls);
		
		freeMarkerConfigurationFactoryBean.setFreemarkerVariables(variables);
		
		
		
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setConfiguration(freeMarkerConfigurationFactoryBean.getObject());
		
		return freeMarkerConfigurer;
	}
	
	
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.swj.msgr.member.dao;com.swj.msgr.app.dao;com.swj.msgr.message.dao");
		
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		
		mapperScannerConfigurer.afterPropertiesSet();
		
		return mapperScannerConfigurer;
	}
	

}
