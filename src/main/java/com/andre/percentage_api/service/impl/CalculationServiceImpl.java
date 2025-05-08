package com.andre.percentage_api.service.impl;

import com.andre.percentage_api.model.CalculationRequest;
import com.andre.percentage_api.model.CalculationResponse;
import com.andre.percentage_api.service.CalculationService;
import com.andre.percentage_api.service.PercentageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final PercentageService percentageService;

    @Override
    public CalculationResponse calculate(CalculationRequest request) {
        double sum = request.getNum1() + request.getNum2();
        double percentage = percentageService.getCurrentPercentage();
        double result = sum * (1 + percentage / 100.0);

        return new CalculationResponse(result, percentage);
    }
}
