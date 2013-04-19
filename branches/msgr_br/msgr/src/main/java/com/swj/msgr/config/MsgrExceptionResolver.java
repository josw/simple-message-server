package com.swj.msgr.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.swj.msgr.commons.exception.CommonException;
import com.swj.msgr.commons.model.ErrorCode;
import com.swj.msgr.commons.model.ResultJsonContainer;

public class MsgrExceptionResolver extends AbstractHandlerExceptionResolver {
	private static final Logger logger = Logger.getLogger(MsgrExceptionResolver.class);

	private HttpMessageConverter<Object> messageConverter = new MappingJacksonHttpMessageConverter();

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (logger.isDebugEnabled()) {
			logger.debug(request.getServletPath());
			logger.debug(request.getContextPath());
			logger.debug(request.getPathInfo());
			logger.debug(request.getRequestURI());
		}
		
		ResultJsonContainer result = null;

		if (ex instanceof CommonException)
			result = new ResultJsonContainer((CommonException) ex);
		else if (ex instanceof ServletRequestBindingException)
			result = new ResultJsonContainer(ErrorCode.ERROR_REQUEST_BINDING);
		else
			result = new ResultJsonContainer(ErrorCode.ERROR);

		ModelAndView mav = null;
		try {
			
			response.setCharacterEncoding("UTF-8");
			HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
			messageConverter.write(result, MediaType.APPLICATION_JSON, outputMessage);

		} catch (Exception e) {
			logger.error("error in making error json", e);
		}
		return mav;
	}
	
	
}