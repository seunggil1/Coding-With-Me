import { defineStore } from 'pinia';
// import { api } from 'src/boot/axios.js';
import { fetchWrapper } from 'src/helpers';
// import { useAuthStore } from 'src/stores';

const baseUrl = 'http://i7a304.p.ssafy.io:8080/api/v1';

export const useClassStore = defineStore({
	id: 'class',
	state: () => ({
		userClass: null,
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
				this.userClass = userClassInfo.result;
				console.log(this.userClass);
			} catch (error) {
				console.log(error);
			}
		},
	},
});
