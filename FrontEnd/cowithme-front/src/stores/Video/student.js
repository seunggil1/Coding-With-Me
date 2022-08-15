import { defineStore } from 'pinia';
import axios from 'axios';
import { ref, reactive } from 'vue';
import { api } from 'src/boot/axios.js';
import { commonVideoData } from 'src/stores/Video/common.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const studentVideoData = defineStore('studentVideoData', () => {
	const teacherCode = ref('');
    const enableTest = ref(false);

    const piniaCommonVideoData = commonVideoData();

    const addEventListener = async () => {
        piniaCommonVideoData.openvidu.session.on('signal:code', event => {
			event = JSON.parse(event.data);
			teacherCode.value = event.message;
		});

		piniaCommonVideoData.openvidu.session.on('signal:testInfo', event => {
			event = JSON.parse(event.data);
			let testData = commonExamData();
			testData.testID = event.testID;
			testData.testName = event.testName;
			testData.timeLimit = event.time;

			enableTest.value = true;
		});
    };

	const getStudentList = async () => {
		let result = await api.get(`/student/${piniaCommonVideoData.userInfo.userKey}/classes`);

		piniaCommonVideoData.displayInfo.studentList.splice(0, piniaCommonVideoData.displayInfo.studentList.length);

		for(let item of result.data.students){
			piniaCommonVideoData.displayInfo.studentList.push(item.name);
		}
		piniaCommonVideoData.displayInfo.studentListIsActive = Array.apply(null, Array(piniaCommonVideoData.displayInfo.studentList.length)).map(() => false);

		let myIdx = piniaCommonVideoData.displayInfo.studentList.indexOf(piniaCommonVideoData.userInfo.userName);
		if(myIdx !== -1){
			piniaCommonVideoData.displayInfo.studentListIsActive[myIdx] = true;
		}
	}

	return {
		teacherCode,
		enableTest,

		addEventListener,
		getStudentList
	};
});
