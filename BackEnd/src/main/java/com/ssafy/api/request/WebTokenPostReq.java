package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("WebTokenPostReq")
public class WebTokenPostReq {
    @ApiModelProperty(name = "사용자 식별자", example = "1")
    Long id;
    @ApiModelProperty(name = "반 식별자", example = "1")
    Long classId;
    @ApiModelProperty(name = "방 닉네임", example = "짱구")
    String displayName;
}
