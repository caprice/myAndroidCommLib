package com.gm.driverGuard.helper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheHelper {
	@SuppressWarnings("rawtypes")
	private static LoadingCache cache;
	private static final String cacheSpec="maximumSize=2000,expireAfterWrite=10m";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static synchronized LoadingCache getCache(CacheLoader loader){
		if(cache!=null) return cache;
		
		cache=CacheBuilder.from(cacheSpec).build(loader);
		return cache;
	}
}
