package com.ssafy.api.service;

import io.openvidu.java.client.OpenViduRole;
import io.openvidu.java.client.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.openvidu.java.client.*;

class SessionInfo{
    Session session;
    // UserID, Connection
    Map<Long, Connection> connectionIDMap = new ConcurrentHashMap<>();
    // Token, userId
    Map<String,Long> tokenIDMap = new ConcurrentHashMap<>();

    SessionInfo(Session session){
        this.session = session;
    }
}
@Service("openViduService")
public class OpenViduServiceImpl implements OpenViduService {

    // Session ID
    private Map<Long, SessionInfo> SessionInfoMap = new ConcurrentHashMap<>();
    // Map<SessionID, Map<토큰, 유저 ID>

    private OpenVidu openVidu;

    OpenViduServiceImpl(@Value("${openvidu.url}") String OPENVIDU_URL, @Value("${openvidu.secret}") String SECRET){
        openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
        SessionInfoMap = new ConcurrentHashMap<>();
    }


    @Override
    public String getToken(Long sessionId, Long userId, String displayName) throws OpenViduException {
        String serverData = "{\"serverData\": \"" + displayName + "\"}";

        // Role은 3가지
        // OpenViduRole.MODERATOR : PUBLISHER 권한 + 다른 사람 쫓아낼 수 있음
        // OpenViduRole.PUBLISHER : SUBSCRIBER 권한 + 자기 영상 송출 가능
        // OpenViduRole.SUBSCRIBER : 다른 사람 영상을 볼 수 있음.
        System.out.println("=========================test1 ");
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER).data(serverData).build();

        System.out.println("=========================test2 ");
        // 해당 방이 존재하면 정보 갱신부터.
        if (SessionInfoMap.get(sessionId) != null){
            try {
                // 접속하려는 방
                SessionInfo targetSessionInfo = SessionInfoMap.get(sessionId);
                targetSessionInfo.session.fetch();

                // 방은 존재하는데 아무도 없는 경우
//                if(targetSessionInfo.session.getActiveConnections().size() == 0){
//                    targetSessionInfo.session.close();
//                    SessionInfoMap.remove(sessionId);
//                }
            } catch(OpenViduHttpException e){
                // 미디어 서버 오류 or 해당 방이 존재하지 않음.
                SessionInfoMap.remove(sessionId);
            }
            catch (Exception e) {
                System.out.println("OpenViduService Error : " + e.getMessage());
                SessionInfoMap.remove(sessionId);
            }
        }

        // 정보 업데이트 완료.

        // 접속하려는 방이 있으면 받아오고, 없으면 새로 생성한다.
        SessionInfo targetSessionInfo;
        if (SessionInfoMap.containsKey(sessionId)){
            targetSessionInfo = SessionInfoMap.get(sessionId);
        }else{
            targetSessionInfo = new SessionInfo(makeSession(sessionId));
        }

        Connection connection = targetSessionInfo.session.createConnection(connectionProperties);

        // 동일한 id로 접속 정보가 있으면 해제한다.
        if(targetSessionInfo.connectionIDMap.containsKey(userId)){
            Connection oldConnection = targetSessionInfo.connectionIDMap.get(userId);
            targetSessionInfo.tokenIDMap.remove(oldConnection.getToken());
            targetSessionInfo.session.forceDisconnect(oldConnection);
            targetSessionInfo.connectionIDMap.remove(userId);
        }

        // 접속 정보 추가
        targetSessionInfo.connectionIDMap.put(userId, connection);
        targetSessionInfo.tokenIDMap.put(connection.getToken(), userId);

        return connection.getToken();
    }

    @Override
    public boolean removeUser(Long sessionId, String token) {
        SessionInfo targetSessionInfo = SessionInfoMap.get(sessionId);

        // 이미 방이 존재하지 않음.
        if(targetSessionInfo == null){
            return true;
        }

        // If the session exists ("TUTORIAL" in this case)
        Long userID = targetSessionInfo.tokenIDMap.get(token);
        if(userID != null){
            Connection connection = targetSessionInfo.connectionIDMap.get(userID);
            if(connection != null){
                try{
                    targetSessionInfo.session.forceDisconnect(connection);
                }catch (OpenViduException e){
                    System.out.println("OpenViduService forceDisconnect Error : " + e.getMessage());
                }
            }
            targetSessionInfo.connectionIDMap.remove(userID);
            targetSessionInfo.tokenIDMap.remove(token);
        }

        if(targetSessionInfo.tokenIDMap.size() == 0){
            try{
                targetSessionInfo.session.close();
            }catch (OpenViduException e){
                System.out.println("OpenViduService close Error : " + e.getMessage());
            }
            SessionInfoMap.remove(sessionId);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean closeSession(Long sessionId) {
        SessionInfo targetSession = SessionInfoMap.get(sessionId);

        if(targetSession == null){
            return true;
        }
        try {
            targetSession.session.close();
            SessionInfoMap.remove(sessionId);
        }catch (OpenViduException e){
            System.out.println("OpenViduService close Error : " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean sessionExist(Long sessionId) {
        SessionInfo targetSessionInfo = SessionInfoMap.get(sessionId);

        if(targetSessionInfo == null){
            return false;
        }

        try {
            targetSessionInfo.session.fetch();
            if(targetSessionInfo.session.getActiveConnections().size() == 0){
                targetSessionInfo.session.close();
                SessionInfoMap.remove(sessionId);
                return false;
            }
        } catch (OpenViduException e) {
            System.out.println("OpenViduService sessionExist Error : " + e.getMessage());
            SessionInfoMap.remove(sessionId);
            return false;
        }

        return true;
    }

    @Override
    public Session makeSession(Long sessionId) throws OpenViduException{
        try{
            // 화면 녹화 설정
//            RecordingProperties recordingProperties = new RecordingProperties.Builder()
//                    .outputMode(Recording.OutputMode.COMPOSED)
//                    .recordingLayout(RecordingLayout.BEST_FIT)
//                    .resolution("640x480")
//                    .frameRate(24)
//                    .build();

            SessionProperties sessionProperties = new SessionProperties.Builder()
                    .recordingMode(RecordingMode.ALWAYS) // RecordingMode.ALWAYS for automatic recording
//                    .defaultRecordingProperties(recordingProperties)
                    .build();

            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession(sessionProperties);
            SessionInfoMap.put(sessionId, new SessionInfo(session));
            return session;
        }catch(OpenViduException e){
            throw e;
        }
    }

    @Override
    public Long findUser(Long sessionId, String token){
        SessionInfo targetSessionInfo = SessionInfoMap.get(sessionId);

        if(targetSessionInfo == null){
            return null;
        }

        if(targetSessionInfo.tokenIDMap.containsKey(token))
            return targetSessionInfo.tokenIDMap.get(token);
        else
            return null;
    }
}
