package com.ssafy.db.repository;

import com.ssafy.db.entity.TestRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRecordRepository extends JpaRepository <TestRecord,Long> {
}
