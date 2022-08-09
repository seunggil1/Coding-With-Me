import { defineStore } from 'pinia';
import { fetchWrapper } from 'src/helpers';

const baseUrl = 'https://i7a304.p.ssafy.io/api/v1';

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
