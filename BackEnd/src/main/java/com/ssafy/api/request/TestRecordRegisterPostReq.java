package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("TestRecordRegisterPostReq")
public class TestRecordRegisterPostReq {
    @ApiModelProperty(name = "사용자 식별자", example = "1")
    Long userId;

    @ApiModelProperty(name = "반 식별자", example = "1")
    Long classId;

    @ApiModelProperty(name = "시험 이름", example = "1차시험")
    String testName;

    @ApiModelProperty(name = "소스코드", example = "...")
    String sourceCode;



}
