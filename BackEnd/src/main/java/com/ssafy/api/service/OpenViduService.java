package com.ssafy.api.service;

import io.openvidu.java.client.*;
public interface OpenViduService {

    /**
     * @param sessionId 세션 아이디(방 고유 식별자. 이게 같으면 같은 방에 들어간다)
     * @param userId 사용자 아이디(같은 세션에 userID는 1개만 존재한다.)
     * @param displayName 웹에서 표시될 이름. 이 이름은 동일해도 된다.
     * @return
     * @throws OpenViduException
     */
    public String getToken(Long sessionId, Long userId, String displayName) throws OpenViduException;

    /**
     *
     * @param sessionId 세션 아이디(방 고유 식별자.)
     * @param token 접속을 종료한 사용자의 토큰
     * @return 세션에 인원이 0일 경우 true, 아닐 경우 false.
     * <br>
     * <ul>
     *  <li>화상회의에서 사용자가 방 밖으로 나갈 경우 호출 하는 함수.</li>
     *  <li>사용자의 토큰을 삭제하고, 세션이 비었을 경우 해당 세션도 삭제처리합니다.</li>
     * </ul>
     */
    public boolean removeUser(Long sessionId, String token);

    /**
     * 해당 세션을 강제로 닫습니다.
     * @param sessionId
     * @return 성공시 true, 실패시 false
     */
    public boolean closeSession(Long sessionId);
    /**
     * openVidu 서버와 통신을 통해 session이 존재하는지 확인한다.
     * @param sessionId
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean sessionExist(String sessionId);

    /**
     * 특정 session에서 토큰에 해당하는 userID 반환
     * @param sessionId
     * @param token
     * @return userID, 없으면 null 반환
     */
    public Long findUser(Long sessionId, String token);
}
