package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LeaveSessionPostReq")
public class LeaveSessionPostReq {
    @ApiModelProperty(name = "강의 식별자", example = "1")
    Long conferenceId;
    @ApiModelProperty(name = "개인 식별 토큰", example = "WebSocket Url")
    String token;
}
