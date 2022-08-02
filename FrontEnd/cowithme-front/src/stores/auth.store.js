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
		returnUrl: null,
	}),
	actions: {
		async login(id, password) {
			try {
				const user = await fetchWrapper.post(`${baseUrl}/auth/login`, {
					id,
					password,
				});
				var token_message = user;
				var token = token_message.accessToken;

				// update pinia state
				var decoded = jwt_decode(token);

				// store user details and jwt in local storage to keep user logged in between page refreshes
				localStorage.setItem('token', token);
				localStorage.setItem('user', JSON.stringify(decoded));

				// redirect to previous url or default to home page
				// router.push(this.returnUrl || '/');

				// redirect to previous url or default to home page
				// await router.push({ path: '/' });
			} catch (error) {
				const alertStore = useAlertStore();
				alertStore.error(error);
			}
			try {
				const info = await fetchWrapper.get(`${baseUrl}/users/id/${id}`);
				localStorage.setItem('info', JSON.stringify(info.user));
				// console.log(info.user);
			} catch (error) {
				const alertStore = useAlertStore();
				alertStore.error(error);
			}
		},
		logout() {
			this.user = null;
			localStorage.removeItem('user');
			// router.push({ path: '/login' });
		},
	},
});
