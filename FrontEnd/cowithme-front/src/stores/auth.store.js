import { defineStore } from 'pinia';

import { fetchWrapper } from 'src/helpers';
import { useRouter } from 'vue-router';
import { useAlertStore } from 'src/stores';
const router = useRouter();

const HOST = 'http://localhost:8080/api/v1';

const baseUrl = `${HOST}`;

export const useAuthStore = defineStore({
	id: 'auth',
	state: () => ({
		// initialize state from local storage to enable user to stay logged in
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

				// update pinia state
				this.user = user;

				// store user details and jwt in local storage to keep user logged in between page refreshes
				localStorage.setItem('user', JSON.stringify(user));

				// redirect to previous url or default to home page
				router.push('/join');
			} catch (error) {
				const alertStore = useAlertStore();
				alertStore.error(error);
			}
		},
		logout() {
			this.user = null;
			localStorage.removeItem('user');
			router.push('/login');
		},
	},
});
