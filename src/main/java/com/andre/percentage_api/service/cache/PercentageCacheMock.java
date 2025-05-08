package com.andre.percentage_api.service.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@Service
public class PercentageCacheMock {

    @Cacheable("percentage")
    public double generateAndCachePercentage() {
        double percentage = 10.0;
        log.info("Generando y cacheando porcentaje: {}", percentage);
        return percentage;
    }
}
