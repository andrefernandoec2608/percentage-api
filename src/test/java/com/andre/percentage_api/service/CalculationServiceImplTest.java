package com.andre.percentage_api.service;

import com.andre.percentage_api.model.CalculationRequest;
import com.andre.percentage_api.model.CalculationResponse;
import com.andre.percentage_api.service.impl.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CalculationServiceImplTest {

    @Mock
    private PercentageService percentageService;

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @Test
    void shouldCalculateCorrectlyWithPositiveNumbers() {
        CalculationRequest request = new CalculationRequest();
        request.setNum1(100.00);
        request.setNum2(50.00);
        when(percentageService.getCurrentPercentage()).thenReturn(10.0); // 10%

        CalculationResponse response = calculationService.calculate(request);

        double expectedResult = (100.0 + 50.0) * 1.10;
        assertEquals(expectedResult, response.getResult(), 0.001);
        assertEquals(10.0, response.getAppliedPercentage());
    }

    @Test
    void shouldCalculateCorrectlyWithZeros() {
        CalculationRequest request = new CalculationRequest();
        request.setNum1(0.00);
        request.setNum2(0.00);
        when(percentageService.getCurrentPercentage()).thenReturn(5.0);

        CalculationResponse response = calculationService.calculate(request);

        assertEquals(0.0, response.getResult(), 0.001);
        assertEquals(5.0, response.getAppliedPercentage());
    }

    @Test
    void shouldCalculateCorrectlyWithNegativeNumbers() {
        CalculationRequest request = new CalculationRequest();
        request.setNum1(-10.00);
        request.setNum2(-5.00);
        when(percentageService.getCurrentPercentage()).thenReturn(20.0);

        CalculationResponse response = calculationService.calculate(request);

        double expectedResult = (-10.0 + -5.0) * 1.20;
        assertEquals(expectedResult, response.getResult(), 0.001);
        assertEquals(20.0, response.getAppliedPercentage());
    }
}