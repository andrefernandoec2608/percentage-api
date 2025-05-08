package com.andre.percentage_api.service.impl;

import com.andre.percentage_api.entity.CallLog;
import com.andre.percentage_api.repository.CallLogRepository;
import com.andre.percentage_api.service.CallLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CallLogServiceImpl implements CallLogService {

    private final CallLogRepository repository;

    @Override
    @Async
    public void saveCall(String endpoint, String params, String response, boolean success) {
        CallLog log = CallLog.builder()
                .timestamp(LocalDateTime.now())
                .endpoint(endpoint)
                .parameters(params)
                .response(response)
                .success(success)
                .build();
        repository.save(log);
    }
}
