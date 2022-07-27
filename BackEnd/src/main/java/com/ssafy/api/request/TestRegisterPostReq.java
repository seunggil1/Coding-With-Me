package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
            "            \"IOList\": [\n" +
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
            "            \"IOList\": [\n" +
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

    @Getter
    @Setter

    public static class testcase{
        int qno;
        List<IO> IOList;
    }
    @Getter
    @Setter
    public static class IO{
        String input;
        String output;
    }


}