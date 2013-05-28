package com.swj.msgr.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


public class MsgrWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		// TODO Auto-generated method stub

		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext
				.setConfigLocation("classpath:/spring/applicationContext-*.xml");

		container.addListener(new ContextLoaderListener(appContext));

		//FilterRegistration fr = container.addFilter("site-mesh", MsgrSiteMeshFilter.class);
		//fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");
		

		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(WebMvcConfig.class);

		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"dispatcher", new DispatcherServlet(mvcContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setMultipartConfig(new MultipartConfigElement(null, 1024 * 1024, -1L, 1024*1024));


		
		FilterRegistration charEncodingfilterReg = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
		charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
		charEncodingfilterReg.setInitParameter("forceEncoding", "true");
		charEncodingfilterReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/**");
		

	}

}
