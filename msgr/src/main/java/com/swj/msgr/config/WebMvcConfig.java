package com.swj.msgr.config;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.swj.msgr.api.resolver.LoginParamArgumentResolver;

@Configuration
@ComponentScan(basePackages = {"com.swj.msgr"})
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}
	 
	
	
	@Override
	protected void configureHandlerExceptionResolvers(
			List<HandlerExceptionResolver> exceptionResolvers) {

		exceptionResolvers.add(new MsgrExceptionResolver());
		
		super.configureHandlerExceptionResolvers(exceptionResolvers);
	}
	
	@Override
	protected void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		
		converters.add(new MsgrJsonMesageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new ByteArrayHttpMessageConverter());
		//converters.add(new SourceHttpMessageConverter());
		converters.add(new XmlAwareFormHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		
		super.configureMessageConverters(converters);
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		super.addResourceHandlers(registry);
	}

	@Override
	public HandlerMapping resourceHandlerMapping() {
		AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping) super.resourceHandlerMapping();
		handlerMapping.setOrder(-1);
        return handlerMapping;
	}
	

	
	@Override
	protected void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {

		argumentResolvers.add(LoginParamArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}
	
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		 return new StandardServletMultipartResolver();
	}

	
	
	@Bean(autowire=Autowire.BY_TYPE)
	public LoginParamArgumentResolver LoginParamArgumentResolver() {
		return new LoginParamArgumentResolver();
	}

}
