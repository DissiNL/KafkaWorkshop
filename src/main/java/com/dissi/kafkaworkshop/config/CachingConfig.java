package com.dissi.kafkaworkshop.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

  /**
   * Save everything in a map to cache. No renewal needed and not multi-tenant on renewals.
   *
   * @return The cache that is used by the application.
   */
  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager("kafka-users");
  }
}
