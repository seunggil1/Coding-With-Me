import { defineStore } from 'pinia';
import { ref, reactive } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
export const teacherExamData = defineStore('teacherExamData', () => {
    const piniaCommonVideoData = commonVideoData();
    /**
     * 시험에서 제출 여부를 표시하는 boolean 배열
     */
    const submitStudentList = ref([]);

    /**
     * addEventListener를 호출했는지 여부
     */
    const initEventLister = ref(false);
    
    /**
     * 학생 데이터에 맞춰서 제출 여부를 표시하는 배열 생성.
     * commonVideoData에서 학생 데이터를 먼저 받아와야함.
     */
     const initSubmitStudentList = () =>{
        submitStudentList.value = Array.apply(
            null,
            Array(piniaCommonVideoData.displayInfo.studentList.length)
        ).map(() => false);
    };

    const addEventListener = () => {
        if(initEventLister.value)
            return;
        piniaCommonVideoData.openvidu.session.on('signal:submit', event => {
            const index = piniaCommonVideoData.displayInfo.studentList.indexOf(event.data);
            
            if(index !== -1){
                submitStudentList.value[index] = true;
            }
		});

        initEventLister.value = true;
    }

    return {
        submitStudentList,
        initSubmitStudentList,
        addEventListener,
    }
});