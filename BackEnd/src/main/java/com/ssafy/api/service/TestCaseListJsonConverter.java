package com.ssafy.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.request.TestCase;
import com.ssafy.api.request.TestRegisterPostReq;

import javax.persistence.AttributeConverter;
import java.util.List;

public class TestCaseListJsonConverter implements AttributeConverter<List<TestRegisterPostReq.testcase>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<TestRegisterPostReq.testcase> attribute) {
        //Information 객체 -> Json 문자열로 변환
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TestRegisterPostReq.testcase> convertToEntityAttribute(String dbData) {
        //Json 문자열 Information 객체로 변환
        try {
            return objectMapper.readValue(dbData, List.class);
        } catch (Exception e) {
            return null;
        }
    }
}
