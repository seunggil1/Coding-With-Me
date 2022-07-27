package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GsonTestRegister")
public class GsonTestRegister {
    @ApiModelProperty(name = "json 문자열", example = "\"{\"classId\":1,\"testName\":\"다익스트라응용문제\",\"testPath\":\"/path/..\",\"testQno\":3,\"testcaseList\":[{\"qno\":1,\"IOList\":[{\"input\":\"string\",\"output\":\"string\"},{\"input\":\"string\",\"output\":\"string\"}]},{\"qno\":2,\"IOList\":[{\"input\":\"string\",\"output\":\"string\"},{\"input\":\"string\",\"output\":\"string\"}]}]}\"")
    String json;
}
