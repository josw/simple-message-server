package com.swj.msgr.api;

import javax.annotation.Resource;

import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.swj.msgr.app.service.AppService;
import com.swj.msgr.maze.Cell;
import com.swj.msgr.maze.Maze;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger("FlatFileAppender");
	
	@Resource
	AppService appService;
	
	@RequestMapping({"", "/"})
	public ModelAndView index(ModelAndView mav) {
		
//		Appender appender = new DailyRollingFileAppender();
//		
//		appender.setName("FlatFileAppender");
//		
//		logger.addAppender(appender);
		
		logger.error ("ROLLLLLLLL>>>>");
		
		Cell[][] cells = new Maze(10, 10).getCells();
		
//		for (int i=0; i< cells.length; i++) {
//			Cell[] temp = cells[i];
//			for (int j=0; j< temp.length; j++) {
//				logger.debug (temp[j]);
//			}
//		}
		
		
		mav.setViewName("/test/index");
		mav.addObject("maze", cells);
		return mav;
	}
	
    private void draw(Cell[][] cells) {
    	
    	
		for (int i=0; i< cells.length; i++) {
			for (int j=0; j<cells[i].length; j++) {
				int value = cells[i][j].getData();
				
				if (value%2 != 0) {
					System.out.print("a");
				}
				
				if ((value>>>2)%2 != 0) {
					System.out.print("b");
					
				}
				
				if (value>>>3 != 0) {
					System.out.print("c");
					
				}
				
				if ((value>>>1)%2 != 0) {
					System.out.print("d");
					
				}
				System.out.print(",");
			}
			System.out.println();
		}
    	
    	
    	
    	
    }
	
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/register");
		return mav;
	}
	
	@RequestMapping("/waitroom")
	public ModelAndView wait(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/waitroom");
		return mav;
	}
	
	@RequestMapping("/dash")
	public ModelAndView dash(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/dash");
		return mav;
	}
	
	@RequestMapping("/write")
	public ModelAndView write(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/write");
		return mav;
	}
	
	
	@RequestMapping("/read")
	public ModelAndView listMessage(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/read");
		return mav;
	}
	
	@RequestMapping("/myroom")
	public ModelAndView getMyroom(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/myroom");
		return mav;
	}
	
	@RequestMapping("/prove")
	public ModelAndView proveRtoken(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/prove-rtoken");
		return mav;
	}
	
	@RequestMapping("/image")
	public ModelAndView updateImage(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/update-image");
		return mav;
	}
	
	@RequestMapping("/friends")
	public ModelAndView getFriends(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/friends");
		return mav;
	}

	@RequestMapping("/friend_add")
	public ModelAndView getFriendAdd(ModelAndView mav) {
		
		mav.addObject("apps", appService.getApps());
		mav.setViewName("/test/friend_add");
		return mav;
	}

}
