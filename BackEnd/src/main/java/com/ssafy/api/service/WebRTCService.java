package com.ssafy.api.service;

import com.ssafy.api.request.WebTokenPostReq;

public interface WebRTCService {
    String joinUser(WebTokenPostReq webTokenPostReq);
}
