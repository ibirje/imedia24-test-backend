package com.imedia.config.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	String cachenames[] = { "currencyrates", "categorytree" };


	public CacheConfig() {
	}

	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public CacheManager cacheManager() {

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<ConcurrentMapCache> caches = new ArrayList<ConcurrentMapCache>();

		for (String s : cachenames)
			caches.add(new ConcurrentMapCache(s));

		cacheManager.setCaches(caches);

		return cacheManager;
	}



	@Bean
	@Override
	public CacheResolver cacheResolver() {
		return new CustomCacheResolver(cacheManager());
	}

}
