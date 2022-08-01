import { defineStore } from 'pinia';

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
		token: JSON.parse(localStorage.getItem('token')),
		user: JSON.parse(localStorage.getItem('user')),
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
				console.log(token_message);
				var token = token_message.accessToken;
				console.log(token);

				// update pinia state
				this.token = token;

				var payload = Buffer.from(token, 'base64');
				var result = JSON.parse(payload.toString());

				// store user details and jwt in local storage to keep user logged in between page refreshes
				localStorage.setItem('token', token);
				localStorage.setItem('user', result);
				console.log(localStorage);

				// redirect to previous url or default to home page
				// router.push(this.returnUrl || '/');

				// redirect to previous url or default to home page
				// await router.push({ path: '/' });
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
