package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CompileRes")
public class CompileRes {
    String status;
    List<Comp> result;

    @Getter
    @Setter
    public static class Comp{
        int time;
        String output;
        boolean success;
    }
}
