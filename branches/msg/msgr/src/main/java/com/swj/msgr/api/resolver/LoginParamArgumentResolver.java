package com.swj.msgr.api.resolver;

import java.util.Enumeration;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.swj.msgr.api.model.Login;
import com.swj.msgr.commons.exception.LoginRequiredException;
import com.swj.msgr.member.service.LoginService;



/**
 * @author josw
 *
 * rtoken , uuid 를 resolve 하여 Login 정보를 리턴한다. 
 *
 */
public class LoginParamArgumentResolver implements HandlerMethodArgumentResolver {
	
	private final static Logger logger = Logger.getLogger(LoginParamArgumentResolver.class);
	
	@Resource
	LoginService loginService;
	
	@Value("#{configProperties['env']}")
	String env;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		logger.debug("supports: " +  parameter.getParameterAnnotation(LoginParam.class) + ":" + parameter.getParameterType());
		
		if (parameter.getParameterAnnotation(LoginParam.class) != null
				&& Login.class == parameter.getParameterType()) return true;
		else return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		LoginParam loginParam = parameter.getParameterAnnotation(LoginParam.class);
		
		
		logger.debug (loginParam);

		String rtoken = webRequest.getHeader("rtoken");
		/*
		Iterator<String> eNames = webRequest.getHeaderNames();
		
		while (eNames.hasNext()) {
			logger.debug (eNames.next());
		}*/
		
		String uuid = webRequest.getHeader("uuid");
		if (StringUtils.isBlank(rtoken))
			rtoken = webRequest.getParameter("rtoken");
		
		if (StringUtils.isBlank(rtoken)) {
			if (loginParam.required())
				throw new LoginRequiredException();
			return new Login();
		}

		logger.debug (rtoken);
		
		Login login = loginService.getLogin(rtoken);
		
		//DEV 환경이 아니고 UUID 가 없으면 Exception 
		if (StringUtils.isNotBlank(env) && !env.equals("DEV") && (StringUtils.isBlank(uuid) || !uuid.trim().equals(login.getMember().getUuid()))) 
				throw new LoginRequiredException();
		
		
		if (login == null && loginParam.required())
			throw new LoginRequiredException();
		
		return login;
	}

}
