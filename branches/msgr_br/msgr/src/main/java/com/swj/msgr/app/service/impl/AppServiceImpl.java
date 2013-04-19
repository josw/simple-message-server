package com.swj.msgr.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swj.msgr.app.dao.AppDao;
import com.swj.msgr.app.model.App;
import com.swj.msgr.app.service.AppService;

@Service
public class AppServiceImpl implements AppService {
	
	@Resource
	AppDao appDao;

	@Override
	public List<App> getApps() {
		return appDao.getApps();
	}

	@Override
	public int register(App app) {
		return appDao.insertApp(app);
	}

}
