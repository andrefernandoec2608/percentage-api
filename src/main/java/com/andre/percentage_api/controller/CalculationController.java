package com.andre.percentage_api.controller;

import com.andre.percentage_api.model.CalculationRequest;
import com.andre.percentage_api.model.CalculationResponse;
import com.andre.percentage_api.service.CalculationService;
import com.andre.percentage_api.service.CallLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;
    private final CallLogService callLogService;
    private final boolean SUCCESSFUL_CALL = true, FAILED_CALL = false;

    @PostMapping
    @Operation(summary = "Realiza la suma de num1 y num2 aplicando un porcentaje dinámico")
    public ResponseEntity<CalculationResponse> calculate(@Parameter(description = "Valores numéricos a calcular")
                                                         @RequestBody
                                                         @Valid CalculationRequest request) {
        try {
            CalculationResponse response = calculationService.calculate(request);
            callLogService.saveCall("/calculate", request.toString(), response.toString(), SUCCESSFUL_CALL);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            callLogService.saveCall("/calculate", request.toString(), ex.getMessage(), FAILED_CALL);
            throw ex;
        }
    }
}
