<script setup>
// 삭제용
import { useAuthStore } from 'src/stores';
import { router } from 'src/router';

// redirect home if already logged in
const authStore = useAuthStore();
if (authStore.user) {
	router.push('/');
}
</script>

<template>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 offset-sm-2 mt-5">
				<router-view />
			</div>
		</div>
	</div>
</template>
