package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long testRecordId;
    Long userId;
    Long testId;
    int correctCount;
    String sourceCode;
}
