import { defineStore } from 'pinia';
import { ref } from 'vue';
export const studentExamData = defineStore('studentExamData', () => {
    const language = ref('java');
	const code = ref([
        'import java.util.*;\nimport java.io.*;\n'
    ]);
    
    return {
        language,
        code
    }
});