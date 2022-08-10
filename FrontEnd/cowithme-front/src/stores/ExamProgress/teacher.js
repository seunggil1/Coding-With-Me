import { defineStore } from 'pinia';
import { ref, reactive } from 'vue';
export const teacherExamData = defineStore('teacherExamData', () => {
    const studentCnt = ref(0);
    const studentList = reactive([]);
    const activeStudentList = reactive(new Set());
    const passiveStudentList = reactive(new Set());

    const submitStudentList = reactive(new Set());
    const notSubmitStudentList = reactive(new Set());
    
    return {
        studentCnt,
        studentList,
        activeStudentList,
        passiveStudentList,
        submitStudentList,
        notSubmitStudentList
    }
});