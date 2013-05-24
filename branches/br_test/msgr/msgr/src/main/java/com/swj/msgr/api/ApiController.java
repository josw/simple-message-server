package com.swj.msgr.api;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {
	
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(ApiController.class);
	

	@RequestMapping(value={"", "/"})
	public ModelAndView hello(ModelAndView mav) {
		
		
		mav.setViewName("hello");
		
		
		return mav;
		
	}
}
