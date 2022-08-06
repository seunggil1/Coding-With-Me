package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("WebTokenPostReq")
public class WebTokenPostReq {
    @ApiModelProperty(name = "사용자 식별자", example = "ssafy")
    String id;
    @ApiModelProperty(name = "반 이름", example = "서울 1반")
    String className;
    @ApiModelProperty(name = "방 닉네임", example = "짱구")
    String displayName;
}
