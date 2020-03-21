package com.desafioJava.parlamentares.domain.infrastructure.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

@Service
public class CacheManager {

    public final Cache<Long,Long> cache;

    public CacheManager() {
        cache = Caffeine.newBuilder().build();
    }

    public void populateCache(Long key, Long value) {
        cache.put(key,value);
    }
}
