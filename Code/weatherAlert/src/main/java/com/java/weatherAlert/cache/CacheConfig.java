package com.java.weatherAlert.cache;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	
	 @Bean
	  public CacheManager cacheManager() {
	    ConcurrentMapCacheManager mgr = new ConcurrentMapCacheManager();
	    mgr.setCacheNames(aslist("employees"));
	    return mgr;
	  }

	private Collection<String> aslist(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
