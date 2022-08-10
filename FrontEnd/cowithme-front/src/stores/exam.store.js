import { defineStore } from 'pinia';
import { fetchWrapper } from 'src/helpers';

const baseUrl = 'https://150.230.248.208:8080/api/v1';

export const useExamStore = defineStore({
	id: 'exam',
	state: () => ({}),
	actions: {
		async makeExam(examInfo) {
			await fetchWrapper.post(`${baseUrl}/tests`, examInfo);
		},
		async uploadExam(testId, files) {
			await fetchWrapper.post(`${baseUrl}/tests/upload/${testId}`, files);
		},
	},
});
