package com.ssafy.api.service;

import com.ssafy.api.request.ConferenceModifyPostReq;
import com.ssafy.api.request.ConferenceRegisterPostReq;
import com.ssafy.db.entity.Conference;

public interface ConferenceService {
    Conference createConference(ConferenceRegisterPostReq conferenceRegisterPostReq);

    Conference modifyConference(ConferenceModifyPostReq conferenceModifyPostReq);

    Conference finishConference(ConferenceModifyPostReq conferenceModifyPostReq);
}
