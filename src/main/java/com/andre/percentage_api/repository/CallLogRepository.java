package com.andre.percentage_api.repository;

import com.andre.percentage_api.entity.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {
}
