package com.andre.percentage_api.service;

public interface CallLogService {
    void saveCall(String endpoint, String params, String response, boolean success);
}