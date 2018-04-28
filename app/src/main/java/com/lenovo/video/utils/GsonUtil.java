package com.lenovo.video.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by wanggl8 on 2018/1/3.
 */

public class GsonUtil
{
	private static Gson gson = new Gson();

	public synchronized static Gson getInstance()
	{
		return gson;
	}

	public static String beanToJson(Object o)
	{
		if (o != null)
		{
			return gson.toJson(o);
		}
		return "";
	}

	public static String mapToJsonString(Map<String, String>  map){
		if (gson ==null){
			gson = new Gson();
		}
		return gson.toJson(map);
	}
}
