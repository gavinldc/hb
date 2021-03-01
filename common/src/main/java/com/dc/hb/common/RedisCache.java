package com.dc.hb.common;

import java.util.Random;

/*
 *  =======================================
 *  项目：hb
 *  模块：common
 *  文件：RedisCache.java
 *  描述：redis接口的模拟类
 *  创建：gavin
 *  更新：2021/3/1 上午11:23
 *  =======================================
 */
public class RedisCache {
	
	
	/**
	 * @param key
	 * @param obj
	 */
	public static void lpush(String key,Object obj) {
		
	}
	
	
	public static long rpop(String key) {
		return new Random().nextInt(1000)+1;
	}
	
	
	
	public static Long llen(String key) {
		return System.currentTimeMillis()%9;
	}
	
	/**
	 * 设置key-v，并设置过期时间
	 * @param key
	 * @param obj
	 * @param time
	 */
	public static void setAndExpire(String key,Object obj,Long time) {
		
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		return false;
	}
	
	public static void del(String key) {
		
	}
}
