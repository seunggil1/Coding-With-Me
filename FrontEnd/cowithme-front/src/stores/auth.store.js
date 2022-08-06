import { defineStore } from 'pinia';
import jwt_decode from 'jwt-decode';

import { fetchWrapper } from 'src/helpers';
// import { useRouter } from 'vue-router';
import { useAlertStore } from 'src/stores';
// const router = useRouter();

const HOST = 'http://i7a304.p.ssafy.io:8080/api/v1';

const baseUrl = `${HOST}`;

export const useAuthStore = defineStore({
	id: 'auth',
	state: () => ({
		// initialize state from local storage to enable user to stay logged in
		token: localStorage.getItem('token'),
		user: localStorage.getItem('user'),
		info: localStorage.getItem('info'),
		// classes: {
		// 	userClassInfo: null,
		// },
		// returnUrl: null,
	}),
	actions: {
		// 로그인 함수
		async login(id, password) {
			// 로그인 및 토큰 저장 후 홈 화면으로 이동
			try {
				const user = await fetchWrapper.post(`${baseUrl}/auth/login`, {
					id,
					password,
				});
				var token_message = user;
				var token = token_message.accessToken;

				var decoded = jwt_decode(token);

				// store user details and jwt in local storage to keep user logged in between page refreshes
				localStorage.setItem('token', token);
				localStorage.setItem('user', JSON.stringify(decoded));
			} catch (error) {
				const alertStore = useAlertStore();
				alertStore.error(error);
			}
			// 로그인과 동시에 유저 정보 받아오기
			try {
				const info = await fetchWrapper.get(`${baseUrl}/users/id/${id}`);
				localStorage.setItem('info', JSON.stringify(info.user));
				// console.log(this.info);
				// console.log(info);
			} catch (error) {
				const alertStore = useAlertStore();
				alertStore.error(error);
			}
			// if (this.info.includes('강사')) {
			// 	// 강사일 경우 반 정보를 불러옴
			// 	try {
			// 		const userId = JSON.parse(this.info).userId;
			// 		// console.log(userId);
			// 		this.classes = await fetchWrapper.get(
			// 			`${baseUrl}/tutor/${userId}/classes`,
			// 		);
			// 		console.log(this.classes);
			// 	} catch (error) {
			// 		const alertStore = useAlertStore();
			// 		alertStore.error(error);
			// 	}
			// }
		},
		// 로그아웃 함수
		logout() {
			this.user = null;
			this.token = null;
			this.info = null;
			localStorage.removeItem('user');
			localStorage.removeItem('token');
			localStorage.removeItem('info');
		},
	},
});
