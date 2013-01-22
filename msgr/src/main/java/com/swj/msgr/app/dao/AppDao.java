package com.swj.msgr.app.dao;

import java.util.List;

import com.swj.msgr.app.model.App;

public interface AppDao {
	
	List<App> getApps();
	
	int insertApp(App app);
	
}
