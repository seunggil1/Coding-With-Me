import { defineStore } from 'pinia';
import { reactive, computed } from 'vue';
import { api } from 'src/boot/axios.js';
import { OpenVidu } from 'openvidu-browser';
api.defaults.headers.post['Content-Type'] = 'application/json';

export const commonVideoData = defineStore('commonVideoData', () => {
	const userInfo = reactive({
		conferenceKey : 0,
		conferenceName : '',
		
		classKey : 0,
		userKey : 0,

		userId : 'aaa', // 로그인할 때 사용하는 id
		userName : '내 이름'
	});
    const openvidu = reactive({
		OV : undefined,
		session : undefined,
		mainStreamManager: undefined,
		publisher : undefined,
		subscribers : [],

		screenOV: undefined,
		screenSession: undefined,
		screenPublisher : undefined,

		token : undefined,
		screenToken : undefined,

	});

	const screenName = computed(() => `${userInfo.userName}'s Screen`);
	const screenId = computed(() => userInfo.userKey + 2100000);

	const displayInfo = reactive({
		videoEnable : true,
		audioEnable : true,
		screenShareEnable : false,

		chatting : [],
		code : 'testCode',

		studentList : [],
	})

	const muteAudio = () => {
		if (openvidu.publisher == undefined) return;
		openvidu.publisher.publishAudio(false);
		displayInfo.audioEnable = false;
	};
	const unmuteAudio = () => {
		if (openvidu.publisher == undefined) return;
		openvidu.publisher.publishAudio(false);
		displayInfo.audioEnable = true;
	};
	const muteVideo = () => {
		if (openvidu.publisher == undefined) return;
		openvidu.publisher.publishVideo(false);
		displayInfo.videoEnable = false;
	}
	const unmuteVideo = () => {
		if (openvidu.publisher == undefined) return;
		openvidu.publisher.publishVideo(true);
		displayInfo.videoEnable = true;
	};

	const getConferenceKey = async () => { 
		let res = await api.get(`/conferences/${userInfo.classKey}/active`);
		userInfo.conferenceKey = res.data.conference.conferenceId;
	}
	const joinSession = async () =>{
		if(openvidu.session)
			return;
		openvidu.OV ??= new OpenVidu();
		openvidu.session ??= openvidu.OV.initSession();
		await addCommonEventListener();
		openvidu.token ??= await getToken();

		try{
			await openvidu.session.connect(openvidu.token, { clientData: userInfo.userName });
		}catch(e){
			// 연결에러, 토큰이 더 이상 유효하지 않음.
			openvidu.token = await getToken();
			await openvidu.session.connect(openvidu.token, { clientData: userInfo.userName });
		}

		await openvidu.OV.getDevices();
		openvidu.publisher = openvidu.OV.initPublisher(undefined, {
			audioSource: undefined, // The source of audio. If undefined default microphone
			videoSource: undefined, // The source of video. If undefined default webcam
			publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
			publishVideo: true, // Whether you want to start publishing with your video enabled or not
			resolution: '640x480', // The resolution of your video
			frameRate: 30, // The frame rate of your video
			insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
			mirror: true, // Whether to mirror your local video or not
		});

		openvidu.mainStreamManager = openvidu.publisher;
		openvidu.session.publish(openvidu.publisher);

		console.log(openvidu.session);
	};

	const leaveSession = async () => {
		if (openvidu.session) {
			openvidu.session.disconnect();
		}
		if (openvidu.screenSession) {
			await stopScreenShare();
		}

		openvidu.session = undefined;
		openvidu.screenSession = undefined;
		openvidu.mainStreamManager = undefined;
		openvidu.publisher = undefined;
		openvidu.subscribers = [];
		openvidu.OV = undefined;
		openvidu.screenOV = undefined;

		displayInfo.audioEnable = true;
		displayInfo.videoEnable = true;
		displayInfo.screenShareEnable = false;

		await api.post('/conference/leaveSession', {
			conferenceId: userInfo.conferenceKey,
			token: openvidu.token,
		});
	}

	const startScreenShare = async () => {
		if(openvidu.screenSession)
			return;
		openvidu.screenOV ??= new OpenVidu();
		openvidu.screenSession ??= openvidu.screenOV.initSession();
		openvidu.screenToken ??= await getScreenToken();

		try {
			await openvidu.screenSession.connect(openvidu.screenToken, { clientData : screenName.value });
		} catch (error) {
			openvidu.screenToken = await getScreenToken();
			await openvidu.screenSession.connect(openvidu.screenToken, { clientData : screenName.value });
		}

		displayInfo.screenShareEnable = true;

		try {
			let publisher = openvidu.screenOV.initPublisher('html-element-id', {
				videoSource: 'screen',
				publishAudio: false,
			});
	
			publisher.once('accessAllowed', () => {
				publisher.stream
					.getMediaStream()
					.getVideoTracks()[0]
					.addEventListener('ended', () => {
						console.log('User pressed the "Stop sharing" button');
						stopScreenShare();
					});
				openvidu.screenSession.publish(publisher);
			});
			publisher.once('accessDenied', event => {
				console.error(event, 'ScreenShare: Access Denied');
				stopScreenShare();
			});
			openvidu.screenPublisher = publisher;
		} catch (error) {
			console.log(error);
		}
	};

	const stopScreenShare = async () => {
		openvidu.screenSession.disconnect();
		openvidu.screenSession = undefined;
		openvidu.screenOV = undefined;
		openvidu.screenPublisher = undefined;

		displayInfo.screenShareEnable = false;

		await api.post('/conference/leaveSession', {
			conferenceId: userInfo.conferenceKey,
			token: openvidu.screenToken,
		});
	}


	const getToken = async () => {
		try {
			const response = await api.post(`/conference/getToken`, {
				classId: userInfo.classKey,
				displayName: userInfo.userName,
				id: userInfo.userKey,
			});
			console.log('토큰발급', response);
			return response.data.token;
		} catch (error) {
			console.log('토큰발급 err', error);
		}
	}
	const getScreenToken = async () => {
		try {
			const response = await api.post(`/conference/getToken`, {
				classId: userInfo.classKey,
				displayName: userInfo.userName,
				id: screenId.value,
			});
			console.log('screen 토큰발급', response);
			return response.data.token;
		} catch (error) {
			console.log('screen 토큰발급 error', error);
		}
	};
	

	const addCommonEventListener = async () =>{
		openvidu.session.on('streamCreated', ({ stream }) => {
			const subscriber = openvidu.session.subscribe(stream);
			openvidu.subscribers.push(subscriber);
		});

		openvidu.session.on('streamDestroyed', ({ stream }) => {
			const index = openvidu.subscribers.indexOf(stream.streamManager, 0);
			if (index >= 0) openvidu.subscribers.splice(index, 1);
		});

		openvidu.session.on('exception', ({ exception }) => console.error(exception));

		openvidu.session.on('signal:chat', event => {
			event = JSON.parse(event.data);
			console.log('received chat : ', event);
			displayInfo.chatting.push({
				sender: event.sender,
				message: event.message,
			});
		});
	};

	const updateMainVideoStreamManager = (stream) => {
		if (openvidu.mainStreamManager === stream) return;
		openvidu.mainStreamManager = stream;
	}
	const sendMessage = async (message) => {
		if(openvidu.session == undefined) return;
		await openvidu.session
			.signal({
				data: JSON.stringify({
					sender: userInfo.userName,
					message: message,
				}), // Any string (optional)
				to: [],
				type: 'chat', // The type of message (optional)
			})
			.then(() => {
				console.log('Message successfully sent');
			})
			.catch(error => {
				console.error(error);
			});
	};
	return {
		userInfo,
		openvidu,
		screenName,
		screenId,
		displayInfo,

		// function
		muteAudio,
		unmuteAudio,
		muteVideo,
		unmuteVideo,

		getConferenceKey,
		joinSession,
		leaveSession,
		startScreenShare,
		stopScreenShare,
		getToken,
		getScreenToken,
		addCommonEventListener,
		updateMainVideoStreamManager,
		sendMessage
	};
});
