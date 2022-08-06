package com.ssafy.api.service;

import com.ssafy.api.request.ForceClosePostReq;
import com.ssafy.api.request.LeaveSessionPostReq;
import com.ssafy.api.request.WebTokenPostReq;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;

public interface WebRTCService {
    String joinUser(WebTokenPostReq webTokenPostReq) throws Exception;

    void removeUserSession(LeaveSessionPostReq leaveSessionPostReq) throws Exception;

    void forceCloseSession(ForceClosePostReq forceClosePostReq) throws Exception;
}
