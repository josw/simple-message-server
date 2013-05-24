package com.swj.msgr.config;

import org.apache.log4j.Logger;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MsgrSiteMeshFilter extends ConfigurableSiteMeshFilter {
	
	private final static Logger logger = Logger.getLogger(MsgrSiteMeshFilter.class);
	
	
	
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		logger.debug ("apply config");
		
//		builder.setMimeTypes("text/html", "text/html;charset=UTF-8");
		builder.addDecoratorPath("/*", "/decorators/default-layout.ftl");
		
		super.applyCustomConfiguration(builder);
	}

}
