<template>
	<div class="q-py-xs q-gutter-sm">
		<q-btn
			size="23px"
			class="btn-size"
			align="left"
			flat
			label="로그아웃"
			icon="logout"
			style="font-family: 'OTWelcomeBA'"
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
@font-face {
	font-family: 'OTWelcomeBA';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2');
	font-weight: normal;
	font-style: normal;
}
</style>
