package com.example.home.assignment.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;

/**
 * Configuration class for caching in the application.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private static final long MAX_CACHE_SIZE = 500;
    private static final long CACHED_ITEM_TTL_IN_MINUTES = 5;

    /**
     * Configures the cache manager for caching currency rates information.
     *
     * @return The cache manager bean.
     */
    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        var caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setCacheNames(Collections.singletonList("currency-rates"));
        return caffeineCacheManager;
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(MAX_CACHE_SIZE)
                .expireAfterAccess(Duration.ofMinutes(CACHED_ITEM_TTL_IN_MINUTES))
                .expireAfterWrite(Duration.ofMinutes(CACHED_ITEM_TTL_IN_MINUTES));
    }

}
