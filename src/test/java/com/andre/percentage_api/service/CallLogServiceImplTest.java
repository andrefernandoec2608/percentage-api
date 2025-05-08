package com.andre.percentage_api.service;

import com.andre.percentage_api.entity.CallLog;
import com.andre.percentage_api.repository.CallLogRepository;
import com.andre.percentage_api.service.impl.CallLogServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CallLogServiceImplTest {

    @Mock
    private CallLogRepository repository;

    @InjectMocks
    private CallLogServiceImpl service;

    @Test
    void shouldSaveCallLogCorrectly() {
        String endpoint = "/api/test";
        String params = "{\"key\":\"value\"}";
        String response = "{\"result\":42}";
        boolean success = true;

        service.saveCall(endpoint, params, response, success);

        ArgumentCaptor<CallLog> logCaptor = ArgumentCaptor.forClass(CallLog.class);
        verify(repository).save(logCaptor.capture());

        CallLog savedLog = logCaptor.getValue();

        assertEquals(endpoint, savedLog.getEndpoint());
        assertEquals(params, savedLog.getParameters());
        assertEquals(response, savedLog.getResponse());
        assertEquals(success, savedLog.isSuccess());
        assertNotNull(savedLog.getTimestamp());
        assertTrue(savedLog.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
