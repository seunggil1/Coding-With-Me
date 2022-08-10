import { defineStore } from 'pinia';
import { fetchWrapper } from 'src/helpers';
import { useAuthStore } from 'src/stores';

const HOST = 'https://i7a304.p.ssafy.io:8080/api/v1';

const baseUrl = `${HOST}`;

export const useUsersStore = defineStore({
	id: 'users',
	state: () => ({
		users: {},
		user: {},
	}),
	actions: {
		async register(user) {
			await fetchWrapper.post(`${baseUrl}/users`, user);
		},
		async getAll() {
			this.users = { loading: true };
			try {
				this.users = await fetchWrapper.get(baseUrl);
			} catch (error) {
				this.users = { error };
			}
		},
		async getById(id) {
			this.user = { loading: true };
			try {
				this.user = await fetchWrapper.get(`${baseUrl}/${id}`);
			} catch (error) {
				this.user = { error };
			}
		},
		// 회원정보 수정
		async update(params) {
			await fetchWrapper.put(`${baseUrl}/users`, params);

			// update stored user if the logged in user updated their own record
			const authStore = useAuthStore();
			// update local storage
			const user = { ...params };
			localStorage.setItem('user', JSON.stringify(user));

			// update auth user in pinia state
			authStore.user = user;
		},
	},
	async delete(userInput) {
		// add isDeleting prop to user being deleted
		// this.users.find(x => x.id === id).isDeleting = true;
		await fetchWrapper.delete(`${baseUrl}/users`, userInput);

		// remove user from list after deleted
		// this.users = this.users.filter(x => x.id !== id);

		// auto logout if the logged in user deleted their own record
		const authStore = useAuthStore();
		if (userInput === authStore.user) {
			authStore.logout();
		}
	},
});
