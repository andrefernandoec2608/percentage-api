package com.andre.percentage_api.service;

import com.andre.percentage_api.model.CalculationRequest;
import com.andre.percentage_api.model.CalculationResponse;

public interface CalculationService {
    CalculationResponse calculate(CalculationRequest request);
}
