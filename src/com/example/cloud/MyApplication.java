package com.example.cloud;

import java.util.HashMap;

import android.app.Application;

public class MyApplication extends Application {
	private HashMap<String, Object> map = new HashMap<String, Object>();
	private static MyApplication instance;

	public static MyApplication getInstance() {
		return instance;
	}

	public HashMap<String, Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.instance = this;
	}
}
