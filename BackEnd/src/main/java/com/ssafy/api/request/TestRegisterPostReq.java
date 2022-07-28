package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel("TestPostReq")
public class TestRegisterPostReq {
    @ApiModelProperty(name = "반 식별자", example = "1")
    Long classId;
    @ApiModelProperty(name = "제목", example = "다익스트라 응용문제")
    String testName;

    @ApiModelProperty(name = "문항 수", example = "3")
    int testQno;

    @ApiModelProperty(name="경로", example= "/path/..")
    String testPath;

    @ApiModelProperty(name="테스트케이스",example= "[\n" +
            "        {\n" +
            "            \"qno\": 1,\n" +
            "            \"inputOutputList\": [\n" +
            "                {\n" +
            "                    \"input\": \"string\",\n" +
            "                    \"output\": \"string\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"input\": \"string\",\n" +
            "                    \"output\": \"string\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"qno\": 2,\n" +
            "            \"inputOutputList\": [\n" +
            "                {\n" +
            "                    \"input\": \"string\",\n" +
            "                    \"output\": \"string\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"input\": \"string\",\n" +
            "                    \"output\": \"string\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]")
    List<testcase> testcaseList;

    @Data
    public static class testcase{
        int qno;
        List<InputOutput> inputOutputList;
    }

    @Data
    public static class InputOutput {
        String input;
        String output;
    }
}