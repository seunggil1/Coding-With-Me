import { defineStore } from 'pinia';
import { fetchWrapper } from 'src/helpers';

const baseUrl = 'http://i7a304.p.ssafy.io:8080/api/v1';

export const useClassStore = defineStore({
	id: 'class',
	state: () => ({
		userClass: localStorage.getItem('userClass')
			? JSON.parse(localStorage.getItem('userClass'))
			: [],
	}),
	getters: {
		getUserClassInfo(state) {
			return state.userClassInfo;
		},
	},
	actions: {
		async makeClass(classInformation) {
			await fetchWrapper.post(`${baseUrl}/tutor/classes`, classInformation);
		},
		async getClass(userId) {
			try {
				console.log(userId);
				const userClassInfo = await fetchWrapper.get(
					`${baseUrl}/users/2/class`,
				);
				localStorage.setItem('userClass', JSON.stringify(userClassInfo.result));
			} catch (error) {
				console.log(error);
			}
		},
	},
});
