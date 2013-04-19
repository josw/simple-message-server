package com.swj.msgr.app.service;

import java.util.List;

import com.swj.msgr.app.model.App;

public interface AppService {
	List<App> getApps();
	int register(App app);
}
