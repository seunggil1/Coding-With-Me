import { defineStore } from 'pinia';
import { fetchWrapper } from 'src/helpers';

const baseUrl = 'https://i7a304.p.ssafy.io:8080/api/v1';

export const useClassStore = defineStore({
	id: 'class',
	state: () => ({}),
	getters: {},
	actions: {
		async makeClass(classInformation) {
			await fetchWrapper.post(`${baseUrl}/tutor/classes`, classInformation);
		},
		async getClass(userId) {
			try {
				console.log(userId);
				const userClassInfo = await fetchWrapper.get(
					`${baseUrl}/users/${userId}/class`,
				);
				localStorage.setItem('userClass', JSON.stringify(userClassInfo.result));
			} catch (error) {
				console.log(error);
			}
		},
		async setClassInfo(classInfo, classId, className, userId) {
			await localStorage.setItem('classInfo', JSON.stringify(classInfo));
			await localStorage.setItem('classId', classId);
			await localStorage.setItem('className', className);
			await localStorage.setItem('userId', userId);
		},
	},
});
