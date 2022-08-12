import { defineStore } from 'pinia';
import { ref } from 'vue';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { compileLang } from 'src/components/lectures/WebEditorAsset';
export const studentExamData = defineStore('studentExamData', () => {
    const language = ref('java');
	const code = ref([
        'import java.util.*;\nimport java.io.*;\n'
    ]);

    const initCode = () => {
        const piniaCommonExamData = commonExamData();
        code.value = Array.apply(null, Array(piniaCommonExamData.testQuCnt)).map(() => compileLang[0].code);
        console.log(code.value);
    }
    
    return {
        language,
        code,
        initCode
    }
});