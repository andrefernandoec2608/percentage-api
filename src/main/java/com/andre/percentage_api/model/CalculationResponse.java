package com.andre.percentage_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationResponse {
    private double result;
    private double appliedPercentage;
}