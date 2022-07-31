<template>
	<div class="q-py-xs q-gutter-sm">
		<q-btn
			size="24px"
			class="btn-size"
			align="left"
			flat
			label="로그아웃"
			icon="logout"
			style="font-family: 'Elice Digital Baeum'"
			@click="onLogout"
		>
		</q-btn>
	</div>
</template>

<script>
import { useAuthStore } from 'src/stores';
import { useRouter } from 'vue-router';

export default {
	name: 'AtomButton',
	setup() {
		const router = useRouter();
		async function onLogout() {
			const authStore = useAuthStore();
			await authStore.logout();
			await router.push({ path: '/login' });
		}
		return { onLogout };
	},
};
</script>

<style>
.btn-size {
	width: 299px;
}
</style>
