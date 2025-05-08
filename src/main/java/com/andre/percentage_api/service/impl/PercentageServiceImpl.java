package com.andre.percentage_api.service.impl;

import com.andre.percentage_api.exceptionHandler.NoPercentageAvailableException;
import com.andre.percentage_api.service.PercentageService;
import com.andre.percentage_api.service.cache.PercentageCacheMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PercentageServiceImpl implements PercentageService {

    private final PercentageCacheMock percentageCacheMock;
    private final CacheManager cacheManager;

    @Value("${simulate.failure:false}")
    private boolean simulateFailure;

    public PercentageServiceImpl(PercentageCacheMock percentageCacheMock, CacheManager cacheManager) {
        this.percentageCacheMock = percentageCacheMock;
        this.cacheManager = cacheManager;
    }

    @Override
    public double getCurrentPercentage() {
        if (simulateFailure) {
            log.warn("Servicio externo simulado ha fallado. Intentando usar caché...");
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                Double cached = cache.get("generateAndCachePercentage", Double.class);
                if (cached != null) {
                    log.info("Valor recuperado de caché: {}", cached);
                    return cached;
                }
            }
            throw new NoPercentageAvailableException();
        }

        return percentageCacheMock.generateAndCachePercentage();
    }
}
