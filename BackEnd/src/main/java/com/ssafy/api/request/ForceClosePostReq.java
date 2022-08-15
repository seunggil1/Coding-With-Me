package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@ApiModel("ForceClosePostReq")
public class ForceClosePostReq {

    @ApiModelProperty(name = "반 식별자", example = "1")
    Long classId;

    @ApiModelProperty(name = "사용자 아이디", example = "ssafy")
    String userId;

    @ApiModelProperty(name = "개인 식별 토큰", example = "WebSocket Url")
    String token;
}
