package com.ssafy.db.repository;

import com.ssafy.db.entity.TestRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRecordRepository extends JpaRepository <TestRecord,Long> {


}
