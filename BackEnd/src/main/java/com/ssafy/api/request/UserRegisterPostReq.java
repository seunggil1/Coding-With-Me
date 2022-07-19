package com.ssafy.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
    @ApiModelProperty(name = "유저 ID", example = "ssafy")
    String id;
    @ApiModelProperty(name = "유저 Password", example = "password")
    String password;
    @ApiModelProperty(name = "유저 Name", example = "김싸피")
    String name;
    @ApiModelProperty(name = "유저 Email", example = "kim@ssafy.com")
    String email;
    @ApiModelProperty(name = "유저 Phone", example = "010-1111-2222")
    String phone;
    @ApiModelProperty(name = "유저 NickName", example = "포도맛캔디")
    String nickname;
    @ApiModelProperty(name = "유저 birthDt", example = "2000-12-12")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date birthDt;
    @ApiModelProperty(name = "유저 profilePath", example = "your_profile")
    String profilePath;
    @ApiModelProperty(name = "유저 role", example = "학생")
    String role;
}
