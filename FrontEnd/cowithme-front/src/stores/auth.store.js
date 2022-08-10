import { defineStore } from 'pinia';
import jwt_decode from 'jwt-decode';
import { fetchWrapper } from 'src/helpers';
import { useAlertStore } from 'src/stores';
import router from 'src/router';
// import { useRouter } from 'vue-router';

const HOST = 'https://i7a304.p.ssafy.io/api/v1';

const baseUrl = `${HOST}`;

export const useAuthStore = defineStore({
	id: 'auth',
	state: () => ({
		// initialize state from local storage to enable user to stay logged in
		token: localStorage.getItem('token'),
		user: localStorage.getItem('user'),
		info: localStorage.getItem('info'),
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
				console.log(token_message);
				var token = token_message.accessToken;

				var decoded = jwt_decode(token);

				// store user details and jwt in local storage to keep user logged in between page refreshes
				localStorage.setItem('token', token);
				localStorage.setItem('user', JSON.stringify(decoded));
			} catch (error) {
				// const router = useRouter();
				router.push({ path: '/login' });
			}
			// 로그인과 동시에 유저 정보 받아오기
			try {
				const info = await fetchWrapper.get(`${baseUrl}/users/id/${id}`);
				localStorage.setItem('info', JSON.stringify(info.user));
			} catch (error) {
				const alertStore = useAlertStore();
				alertStore.error(error);
			}
		},
		// 로그아웃 함수
		async logout() {
			await localStorage.clear();
		},
	},
});
