package com.andre.percentage_api.service;

import com.andre.percentage_api.service.cache.PercentageCacheMock;
import com.andre.percentage_api.service.impl.PercentageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PercentageServiceImplTest {

    private PercentageCacheMock percentageCacheMock;
    private CacheManager cacheManager;
    private PercentageServiceImpl service;

    @BeforeEach
    void setup() {
        percentageCacheMock = mock(PercentageCacheMock.class);
        cacheManager = mock(CacheManager.class);
        service = new PercentageServiceImpl(percentageCacheMock, cacheManager);
    }

    @Test
    void shouldReturnValueFromMockWhenNoFailure() {
        ReflectionTestUtils.setField(service, "simulateFailure", false);

        when(percentageCacheMock.generateAndCachePercentage()).thenReturn(12.5);

        double result = service.getCurrentPercentage();

        assertEquals(12.5, result);
        verify(percentageCacheMock).generateAndCachePercentage();
    }

    @Test
    void shouldReturnValueFromCacheOnFailure() {
        ReflectionTestUtils.setField(service, "simulateFailure", true);

        Cache cache = mock(Cache.class);
        when(cacheManager.getCache("percentage")).thenReturn(cache);
        when(cache.get("generateAndCachePercentage", Double.class)).thenReturn(7.5);

        double result = service.getCurrentPercentage();

        assertEquals(7.5, result);
        verify(cacheManager).getCache("percentage");
    }

    @Test
    void shouldThrowWhenFailureAndNoCacheValue() {
        ReflectionTestUtils.setField(service, "simulateFailure", true);

        Cache cache = mock(Cache.class);
        when(cacheManager.getCache("percentage")).thenReturn(cache);
        when(cache.get("generateAndCachePercentage", Double.class)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getCurrentPercentage());

        assertEquals("Fallo simulado y caché vacía o expirada.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenFailureAndCacheIsNull() {
        ReflectionTestUtils.setField(service, "simulateFailure", true);

        when(cacheManager.getCache("percentage")).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getCurrentPercentage());

        assertEquals("Fallo simulado y caché vacía o expirada.", ex.getMessage());
    }
}
