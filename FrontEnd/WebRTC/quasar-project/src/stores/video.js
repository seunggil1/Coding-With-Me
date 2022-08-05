import { OpenVidu } from "openvidu-browser";
import { defineStore } from "pinia";
import axios from "axios";
import { ref } from "vue";

axios.defaults.headers.post['Content-Type'] = 'application/json';

export const useVideoStore = defineStore('video', () => {

  const OPENVIDU_SERVER_URL = "https://" + "localhost:4443"; // 서버 주소
  const OPENVIDU_SERVER_SECRET = "MY_SECRET"; // 비번?

  const state = ref({
    OV: undefined,
    session: undefined,
    mainStreamManager: undefined,
    publisher: undefined,
    subscribers: [],
    mySessionId: 'SessionA', // 나중에 처리
    myUserName: '', // 나중에 처리
    screenOV: undefined,
    screenSession: undefined,
    screenShareName: '',  // 나중에 처리
    chatting : [],
    
    // true면 내가 선생.
    isTeacher : false,
    // 웹 IDE 데이터도 여기서 처리함.
    teacherCode : '',
    myCode : ''
  })

  const isChat = ref(false); // 채팅창 보여줄까 말까
  const isParti = ref(false); // 참가자 보여줄까 말까

  function joinSession() {
    state.value.OV = new OpenVidu();
    state.value.session = state.value.OV.initSession();

    state.value.session.on('streamCreated', ({ stream }) => {
      const subscriber = state.value.session.subscribe(stream);
      state.value.subscribers.push(subscriber);
    });

    state.value.session.on('streamDestroyed', ({ stream }) => {
      const index = state.value.subscribers.indexOf(stream.streamManager, 0);
      if (index >= 0) {
        state.value.subscribers.splice(index, 1);
      }
    });

    state.value.session.on('exception', ({ exception }) => {
      console.error(exception);
    });

    state.value.session.on('signal:chat', (event) => {
      console.log('received chat : ', event.data);
      state.value.chatting.push({
        sender : event.data.sender,
        message : event.data.message
      });
      console.log(event.data); // Message
      console.log(event.from); // Connection object of the sender
      console.log(event.type); // The type of message ("my-chat")
    });

    state.value.session.on('signal:code', (event) => {
      console.log('received code : ', event.data);
      state.value.teacherCode = event.data.code;
    });

    getToken(state.value.mySessionId).then(token => {
      state.value.session.connect(token, { clientData: state.value.myUserName })
        .then(() => {
          
          state.value.OV.getDevices().then(() =>{
              // let videoDevices = devices.filter(device => device.kind === 'videoinput');
              let publisher = state.value.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                resolution: '640x480',  // The resolution of your video
                frameRate: 30,			// The frame rate of your video
                insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                mirror: true       	// Whether to mirror your local video or not
              });

            state.value.mainStreamManager = publisher;
            state.value.publisher = publisher;

            // --- Publish your stream ---
            state.value.session.publish(state.value.publisher);
          })
        })
        .catch(error => {
          console.log('There was an error connecting to the session:', error.code, error.message);
        });
    });
    window.addEventListener('beforeunload', leaveSession);
  }

  function leaveSession() {
    // --- Leave the session by calling 'disconnect' method over the Session object ---
    if (state.value.session) {
      state.value.session.disconnect();
    }
    if (state.value.screenSession) {
      state.value.screenSession.disconnect();
    } 
    state.value.session = undefined;
    state.value.screenSession = undefined;
    state.value.mainStreamManager = undefined;
    state.value.publisher = undefined;
    state.value.subscribers = [];
    state.value.OV = undefined;
    state.value.screenOV = undefined;

    window.removeEventListener('beforeunload', leaveSession);
  }

  async function getToken(mySessionId) {
    const id = await createSession(mySessionId);
    return await createToken(id);
  }

  function createToken(sessionId) {
    return new Promise((resolve, reject) => {
      axios
        .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
          auth: {
            username: 'OPENVIDUAPP',
            password: `${OPENVIDU_SERVER_SECRET}`,
          },
        })
        .then(response => response.data)
        .then(data => resolve(data.token))
        .catch(error => reject(error.response));
    });
  }

  function createSession(sessionId) {
    return new Promise((resolve, reject) => {
      axios
        .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
          customSessionId: sessionId,
        }), {
          auth: {
            username: 'OPENVIDUAPP',
            password: 'MY_SECRET',
          },
        })
        .then(response => response.data)
        .then(data => resolve(data.id))
        // 접속 못하면 에러 나는 부분
        .catch(error => {
          if (error.response.status === 409) {
            resolve(sessionId);
          } else {
            console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
            if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
              location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
            }
            reject(error.response);
          }
        });
    });
  }

  function startScreenShare() {
    state.value.screenOV = new OpenVidu();
    state.value.screenSession = state.value.screenOV.initSession();

    getToken(state.value.mySessionId).then(token =>{
      state.value.screenSession.connect(token, { clientData: state.value.screenShareName }).then(()=>{
          let publisher = state.value.screenOV.initPublisher("html-element-id", { videoSource: "screen", publishAudio: false });

          try {
            publisher.once('accessAllowed', () => {
            publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
              console.log('User pressed the "Stop sharing" button');
              stopScreenShare();
            });
              state.value.screenSession.publish(publisher);
            });

            publisher.once('accessDenied', (event) => {
              console.error(event, 'ScreenShare: Access Denied');
              stopScreenShare();
            });
          } catch (error) {
            console.log(error);
          }
          
      })
    }).catch(error => {
      console.error(error);
      state.value.screenOV = undefined;
      state.value.screenSession = undefined;
    })
  }

  function stopScreenShare() {
    state.value.screenSession.disconnect();
    state.value.screenOV = undefined;
    state.value.screenSession = undefined;
  }

  function updateMainVideoStreamManager(stream) {
    if (state.value.mainStreamManager === stream) {
      return;
    }
    state.value.mainStreamManager = stream;
  }

  function setMyUserName() {
    state.value.myUserName = 'Participant' + Math.floor(Math.random() * 10) + 1;
  }

  function setScreenShareName() {
    state.value.screenShareName = state.value.myUserName + "'S screen";
  }

  function sendMessage(message){
    if(state.value.session == undefined){
      console.log('session is not connected. sendMessage is canceled.');
      return;
    }
    state.value.session.singal({
      data: {
        sender : state.value.myUserName,
        message : message
      },  // Any string (optional)
      to: [],         
      type: 'chat'             // The type of message (optional)
    })
    .then(() => {
        console.log('Message successfully sent');
    })
    .catch(error => {
        console.error(error);
    });
  }

  function sendCode(code){
    if(state.value.session == undefined){
      console.log('session is not connected. sendCode is canceled.');
      return;
    }
    state.value.session.singal({
      data: {
        sender : state.value.myUserName,
        message : code
      },  // Any string (optional)
      to: [],         
      type: 'code'             // The type of message (optional)
    })
    .then(() => {
        console.log('Code successfully sent');
    })
    .catch(error => {
        console.error(error);
    });
  }

  return {
    state,
    joinSession,
    leaveSession,
    getToken,
    createToken,
    createSession,
    startScreenShare,
    stopScreenShare,
    updateMainVideoStreamManager,
    setMyUserName,
    setScreenShareName,
  }
})