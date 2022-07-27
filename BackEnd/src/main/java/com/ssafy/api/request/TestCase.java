package com.ssafy.api.request;

import com.ssafy.api.service.TestCaseListJsonConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.List;

@Getter
@Setter
public class TestCase {

    @Convert(converter = TestCaseListJsonConverter.class)
    List<TestRegisterPostReq.testcase> testcaseList;
}
