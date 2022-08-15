import { defineStore } from 'pinia';
import axios from 'axios';
import { ref, reactive, computed } from 'vue';
import { api } from 'src/boot/axios.js';
import { commonVideoData } from 'src/stores/Video/common.js';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const teacherVideoData = defineStore('teacherVideoData', () => {
    const testList = ref([]);
    const testNameList = computed(() => testList.value.map((it) => it.testName));

	const teacherCode = ref('');
    const enableTest = ref(false);

    const piniaCommonVideoData = commonVideoData();
	
    const getTestList = async () => {
		let res = await api.get(`/tests/${piniaCommonVideoData.userInfo.classKey}`);
        
        testList.value = [];
        for (let iter of res.data.testList) {
            testList.value.push({
                testId: iter.testId,
                testName: iter.testName,
                testQno: iter.testQno,
            });
        }
	};

	const getStudentList = async () => {
		let result = api.get(`/tutor/${piniaCommonVideoData.userInfo.userKey}/classes/${piniaCommonVideoData.userInfo.className}`);

		piniaCommonVideoData.displayInfo.studentList.splice(0, piniaCommonVideoData.displayInfo.studentList.length);

		for(let item of (await result).data.students){
			piniaCommonVideoData.displayInfo.studentList.push(item.name);
		}
		piniaCommonVideoData.displayInfo.studentListIsActive = Array.apply(null, Array(piniaCommonVideoData.displayInfo.studentList.length)).map(() => false);
	}

    const addEventListener = async () => {
       
    };

    const createSession = async () => {
		try {
			const response = await api.post(`/conferences`, {
				classId: piniaCommonVideoData.userInfo.classKey,
				conferenceName: piniaCommonVideoData.userInfo.conferenceName,
				ownerId: piniaCommonVideoData.userInfo.userId,
			});
			console.log('강의방 생성', response);
		} catch (error) {
			if (error.response.status === 409) {
				console.log('이미 존재하는 방');
				return;
			}
			console.log('강의방 생성 err', error);
		}
	};

    /**
     * 강사의 코드를 학생에게 전달한다.
     * 
     * 전송전 webEdtior에서 코드를 받아서 teacherVideoData.displayInfo.code에 해당 코드를 넣어줘야 한다.
     * 
     * @argu terminate = true면 빈 값 전송
     * @returns Promise<void>
     */
    const sendCode = async (terminate = false) => {
		if (piniaCommonVideoData.openvidu.session == undefined) {
			console.log('session is not connected. sendCode is canceled.');
			return;
		}
        await piniaCommonVideoData.openvidu.session
			.signal({
				data: JSON.stringify({
					sender: piniaCommonVideoData.userInfo.userName,
					message: terminate ? '' : piniaCommonVideoData.displayInfo.code,
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
	};

	const sendTestInfo = async (testID, testName, time) => {
		if (piniaCommonVideoData.openvidu.session == undefined) {
			console.log('session is not connected. sendCode is canceled.');
			return;
		}
		try {
			await piniaCommonVideoData.openvidu.session.signal({
				data: JSON.stringify({
					testID : testID,
					testName: testName,
					time: time,
				}), // Any string (optional)
				to: [],
				type: 'testInfo', // The type of message (optional)
			});
			console.log('Code successfully sent');
		} catch (e) {
			console.error(e);
		}
	};

	/**
	 * 학생들에게 페이지 이동을 지시하는 문.
	 * 
	 * 
	 * @param { String } msg lecture, exam, leave
	 * @returns 
	 */
	const sendRedirectInfo = async (msg) => {
		if (piniaCommonVideoData.openvidu.session == undefined) {
			console.log('session is not connected. sendCode is canceled.');
			return;
		}
		try {
			await piniaCommonVideoData.openvidu.session.signal({
				data: msg, // Any string (optional)
				to: [],
				type: 'move', // The type of message (optional)
			});
			console.log('Code successfully sent');
		} catch (e) {
			console.error(e);
		}
	}

	return {
        testList,
        testNameList,
        teacherCode,
        enableTest,

        getTestList,
		getStudentList,
        addEventListener,
        createSession,
        sendCode,
        sendTestInfo,
		sendRedirectInfo,
	};
});
