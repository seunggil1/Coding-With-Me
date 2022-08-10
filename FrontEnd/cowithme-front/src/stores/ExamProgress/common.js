import { defineStore } from 'pinia';
import { ref, computed, reactive } from 'vue';
export const commonExamData = defineStore('commonExamData', () => {
	const testID = ref(1);
    const testName = ref('');
    const testCase = reactive([]);
    const testQuCnt = computed(()=>{
        return testCase.length;
    })

    // chatting은 studentVideo, teacherVideo 쓴다. 대신 들어가기전에 chatting 초기화 하자.

    // 시험 시간 관련 데이터
    const timeLimit = ref(3600 * 0 + 60 * 7 + 1 * 50);
    const setTimeLimit = (hour, min, sec) => {
        timeLimit.value = 3600 * hour + 60 * min + 1 * sec;
    };
    const formattedTime = computed(() =>{
        let time = timeLimit.value;

        const hour = parseInt(time / 3600);
        time %= 3600;
        
        const minute = parseInt(time / 60);
        const second = time % 60;
    
        return `${hour < 10 ? '0' + hour : hour}:${ minute < 10 ? '0' + minute : minute}:${second}`;
    });

    return {
        testID,
        testName,
        testCase,
        testQuCnt,

        timeLimit,
        setTimeLimit,
        formattedTime
    }
});