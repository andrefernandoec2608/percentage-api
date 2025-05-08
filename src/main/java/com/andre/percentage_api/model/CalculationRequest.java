package com.andre.percentage_api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalculationRequest {
    @NotNull
    private Double num1;

    @NotNull
    private Double num2;

}
