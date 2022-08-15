import { defineStore } from 'pinia';
import { ref } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { compileLang } from 'src/components/lectures/WebEditorAsset';
export const studentExamData = defineStore('studentExamData', () => {
    const piniaCommonVideoData = commonVideoData();

    const language = ref('java');
	const code = ref([
        'import java.util.*;\nimport java.io.*;\n'
    ]);

    const initCode = () => {
        const piniaCommonExamData = commonExamData();
        code.value = Array.apply(null, Array(piniaCommonExamData.testQuCnt)).map(() => compileLang[0].code);
        console.log(code.value);
    }

    const submit = async () => {
		if (piniaCommonVideoData.openvidu.session == undefined) {
			console.log('session is not connected. sendCode is canceled.');
			return;
		}
        await piniaCommonVideoData.openvidu.session
			.signal({
				data: piniaCommonVideoData.userInfo.userName,
				to: [],
				type: 'submit', // The type of message (optional)
			})
			.then(() => {
				console.log('Code successfully sent');
			})
			.catch(error => {
				console.error(error);
			});
	};
    
    return {
        language,
        code,
        initCode,
        submit
    }
});