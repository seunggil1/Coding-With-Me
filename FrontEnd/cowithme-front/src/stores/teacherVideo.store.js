import { OpenVidu } from 'openvidu-browser';
import { defineStore } from 'pinia';
import axios from 'axios';
import { ref, reactive } from 'vue';
import { api } from 'src/boot/axios.js';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const teacherVideoStore = defineStore('teacherVideo', () => {
	const state = reactive({
		OV: undefined,
		session: undefined,
		mainStreamManager: undefined,
		publisher: undefined,
		subscribers: [],
		mySessionId: '알고리즘 10주차 수업', // 나중에 처리
		myUserName: '김동욱', // 나중에 처리
		classId: 5,
		userId: 26,
		id: 'ksgg1', // 로그인 할 때
		screenOV: undefined,
		screenSession: undefined,
		screenShareName: '', // 나중에 처리
		chatting: [],
		
		testList : [],
		testNameList : [],
		// 웹 IDE 데이터도 여기서 처리함.
		teacherCode:
			'import java.util.*;\nimport java.io.*;\n\npublic class Main{\n    public static void main(String[] args) throws IOException {\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\n       \n        int a = Integer.parseInt(re.readLine());\n        int b = Integer.parseInt(re.readLine());\n\n        System.out.println(a+b);\n        re.close();\n    }\n}',
		
		
		token : ''
	});

	const getTestList = () => {
		api.get('/tests/' + state.classId)
			.then((res)=>{
				for(let iter of res.data.testList){
					state.testList.push({
						"testId": iter.testId,
						"testName": iter.testName,
						"testQno": iter.testQno,
					})
					state.testNameList.push(iter.testName);
				}
			})
	};

	const rightDrawerOpen = ref(true); // 참여자 + 채팅창. ture면 on
	function setRightDrawer() {
		rightDrawerOpen.value = !rightDrawerOpen.value;
	}

	const subCamsOpen = ref(true);
	function setSubCams() {
		subCamsOpen.value = !subCamsOpen.value;
	}

	const isAudio = ref(true); // 자기 비디오 켜져있는지 여부. true면 on
	function muteAudio() {
		if (state.publisher == undefined) {
			console.log('session is not connected. muteAudio is canceled.');
			return;
		}
		state.publisher.publishAudio(false);
		isAudio.value = !isAudio.value;
	}

	function unmuteAudio() {
		if (state.publisher == undefined) {
			console.log('session is not connected. umMuteAudio is canceled.');
			return;
		}
		state.publisher.publishAudio(true);
		isAudio.value = !isAudio.value;
	}
	const isVideo = ref(true); // 자기 비디오 꺼져있는지 여부. true면 on
	function muteVideo() {
		if (state.publisher == undefined) {
			console.log('session is not connected. muteVideo is canceled.');
			return;
		}
		state.publisher.publishVideo(false);
		isVideo.value = !isVideo.value;
	}

	function unmuteVideo() {
		if (state.publisher == undefined) {
			console.log('session is not connected. unmutedVideo is canceled.');
			return;
		}
		state.publisher.publishVideo(true);
		isVideo.value = !isVideo.value;
	}

	// 화면 공유 처리
	const isScreen = ref(false);
	function startScreenShare() {
		state.screenOV = new OpenVidu();
		state.screenSession = state.screenOV.initSession();

		getToken(state.mySessionId)
			.then(token => {
				state.screenSession
					.connect(token, { clientData: state.screenShareName })
					.then(() => {
						let publisher = state.screenOV.initPublisher('html-element-id', {
							videoSource: 'screen',
							publishAudio: false,
						});
						isScreen.value = !isScreen.value;
						try {
							publisher.once('accessAllowed', () => {
								publisher.stream
									.getMediaStream()
									.getVideoTracks()[0]
									.addEventListener('ended', () => {
										console.log('User pressed the "Stop sharing" button');
										stopScreenShare();
									});
								state.screenSession.publish(publisher);
							});

							publisher.once('accessDenied', event => {
								console.error(event, 'ScreenShare: Access Denied');
								stopScreenShare();
							});
						} catch (error) {
							console.log(error);
						}
					});
			})
			.catch(error => {
				console.error(error);
				state.screenOV = undefined;
				state.screenSession = undefined;
			});
		// let last = state.value.subscribers[state.value.subscribers.length - 1];
	}

	function stopScreenShare() {
		state.screenSession.disconnect();
		state.screenOV = undefined;
		state.screenSession = undefined;
		isScreen.value = !isScreen.value;
	}

	function joinSession() {
		state.OV = new OpenVidu();
		state.session = state.OV.initSession();

		state.session.on('streamCreated', ({ stream }) => {
			const subscriber = state.session.subscribe(stream);
			state.subscribers.push(subscriber);
		});

		state.session.on('streamDestroyed', ({ stream }) => {
			const index = state.subscribers.indexOf(stream.streamManager, 0);
			if (index >= 0) {
				state.subscribers.splice(index, 1);
			}
		});

		state.session.on('exception', exception => {
			console.error(exception);
		});

		state.session.on('signal:chat', event => {
			event = JSON.parse(event.data);
			console.log('received chat : ', event);
			state.chatting.push({
				sender: event.sender,
				message: event.message,
			});
		});

		getToken().then(token => {
			state.token = token;
			state.session
				.connect(token, { clientData: state.myUserName })
				.then(() => {
					state.OV.getDevices().then(() => {
						// let videoDevices = devices.filter(device => device.kind === 'videoinput');
						let publisher = state.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
							publishVideo: true, // Whether you want to start publishing with your video enabled or not
							resolution: '640x480', // The resolution of your video
							frameRate: 30, // The frame rate of your video
							insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
							mirror: true, // Whether to mirror your local video or not
						});

						state.mainStreamManager = publisher;
						state.publisher = publisher;

						// --- Publish your stream ---
						state.session.publish(state.publisher);
					});
				})
				.catch(error => {
					console.log(
						'There was an error connecting to the session:',
						error.code,
						error.message,
					);
				});
		});

		api.get('/conferences/'+ state.classId +'/active').then((res)=>{
			api.post('/records/attendances',{
				"conferenceId": res.data.conference.conferenceId,
				"userId": state.userId
			});
		})

		window.addEventListener('beforeunload', leaveSession);
	}

	async function leaveSession() {
		// --- Leave the session by calling 'disconnect' method over the Session object ---
		if (state.session) {
			state.session.disconnect();
		}
		if (state.screenSession) {
			state.screenSession.disconnect();
		}
		state.session = undefined;
		state.screenSession = undefined;
		state.mainStreamManager = undefined;
		state.publisher = undefined;
		state.subscribers = [];
		state.OV = undefined;
		state.screenOV = undefined;
		
		rightDrawerOpen.value = true;
		subCamsOpen.value = true;
		isAudio.value = true;
		isVideo.value = true;
		isScreen.value = false;
		
		let conferenceID = (await api.get('/conferences/'+ state.classId +'/active')).data.conference.conferenceId;
		await api.put('/records/attendances',{
			"conferenceId": conferenceID,
			"userId": state.userId
		});
		await api.post('/conference/leaveSession',{
			conferenceId : conferenceID,
			token : state.token
		});
		state.token = '';
		window.removeEventListener('beforeunload', leaveSession);
	}

	// serverSide start. Backend가 완성되면 변경 필요.
	async function getToken() {
		// await createSession();
		return await createToken();
	}

	async function createToken() {
		try {
			const response = await api.post(`/conference/getToken`, {
				classId: state.classId,
				displayName: state.myUserName,
				id: state.userId,
			});
			console.log('토큰발급', response);
			return response.data.token;
		} catch (error) {
			console.log('토큰발급 err', error);
		}
	}

	async function createSession() {
		try {
			const response = await api.post(`/conferences`, {
				classId: state.classId,
				conferenceName: state.mySessionId,
				ownerId: state.id,
			});
			console.log('강의방 생성', response);
		} catch (error) {
			if (error.response.status === 409) {
				console.log('이미 존재하는 방');
				return;
			}
			console.log('강의방 생성 err', error);
		}
	}

	// serverSide end.

	function updateMainVideoStreamManager(stream) {
		if (state.mainStreamManager === stream) {
			return;
		}
		state.mainStreamManager = stream;
	}

	function setScreenShareName() {
		state.screenShareName = state.myUserName + "'S screen";
	}

	function sendMessage(message) {
		if (state.session == undefined) {
			console.log('session is not connected. sendMessage is canceled.');
			return;
		}
		state.session
			.signal({
				data: JSON.stringify({
					sender: state.myUserName,
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
	}

	function sendCode() {
		if (state.session == undefined) {
			console.log('session is not connected. sendCode is canceled.');
			return;
		}
		state.session
			.signal({
				data: JSON.stringify({
					sender: state.myUserName,
					message: state.teacherCode,
				}), // Any string (optional)
				to: [],
				type: 'code', // The type of message (optional)
			})
			.then(() => {
				console.log('Code successfully sent');
			})
			.catch(error => {
				console.error(error);
			});
	}

	 async function sendTestInfo(testId, time){
		if (state.session == undefined) {
			console.log('session is not connected. sendCode is canceled.');
			return;
		}
		try{
			await state.session.signal({
					data: JSON.stringify({
						testID: testId,
						time: time,
					}), // Any string (optional)
					to: [],
					type: 'testInfo', // The type of message (optional)
			});
			console.log('Code successfully sent');

		}catch(e){
			console.error(e);
		}
	}

	return {
		getTestList,
		state,
		rightDrawerOpen,
		setRightDrawer,
		subCamsOpen,
		setSubCams,
		isAudio,
		muteAudio,
		unmuteAudio,
		isVideo,
		muteVideo,
		unmuteVideo,
		isScreen,
		startScreenShare,
		stopScreenShare,

		joinSession,
		leaveSession,
		getToken,
		createToken,
		createSession,

		updateMainVideoStreamManager,
		setScreenShareName,
		sendMessage,
		sendCode,
		sendTestInfo
	};
});
