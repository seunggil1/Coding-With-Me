<template>
	<div>
		<div>{{ myInfo[0].phone }}</div>
		<q-btn @click="goDelete">회원탈퇴</q-btn>
		<p>이제 되나</p>
		<p>여기만 안되나</p>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';
import { useUsersStore } from 'src/stores';
import { onBeforeMount } from 'vue';
import { ref } from 'vue';

export default {
	name: 'MypagePage',
	setup() {
		const myInfo = ref([]);
		const router = useRouter();
		const info = JSON.parse(localStorage.getItem('info'));

		onBeforeMount(async () => {
			myInfo.value.push(info);
			console.log(myInfo);
		});
		console.log(myInfo.value);
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
			await router.push({ path: '/join' });
		}
		return { goDelete, info, myInfo };
	},
};
</script>

<style lang="scss" scoped></style>
