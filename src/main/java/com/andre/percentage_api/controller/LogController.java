package com.andre.percentage_api.controller;

import com.andre.percentage_api.entity.CallLog;
import com.andre.percentage_api.repository.CallLogRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final CallLogRepository callLogRepository;

    @GetMapping
    @Operation(summary = "Devuelve los logs de las llamadas a las apis")
    public Page<CallLog> getLogs(Pageable pageable) {
        return callLogRepository.findAll(pageable);
    }
}
