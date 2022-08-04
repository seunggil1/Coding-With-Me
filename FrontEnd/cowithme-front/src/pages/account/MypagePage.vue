<template>
	<div>
		<q-btn @click="goDelete">회원탈퇴</q-btn>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';
import { useUsersStore } from 'src/stores';
// import { onBeforeMount } from 'vue';

export default {
	name: 'MypagePage',
	setup() {
		const router = useRouter();
		const info = JSON.parse(localStorage.getItem('info'));
		async function goDelete() {
			let user = {
				name: info.name,
				email: info.email,
				password: info.password,
				role: info.role,
				id: info.id,
				birthDt: info.birthDt,
				phone: info.phone,
				nickname: info.nickname,
			};
			const usersStore = useUsersStore();
			await usersStore.delete(user);
			await router.push({ path: '/login' });
		}
		return { goDelete };
	},
};
</script>

<style lang="scss" scoped></style>
