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
			testData.timeLimit = event.time;

			enableTest.value = true;
		});
    };
	return {
		teacherCode,
		enableTest,

		addEventListener
	};
});
