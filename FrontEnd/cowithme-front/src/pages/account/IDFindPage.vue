<template>
	<div class="login flex">
		<div class="login-box row justify-center">
			<LeftLogo></LeftLogo>
			<RightIDForm></RightIDForm>
		</div>
	</div>
</template>

<script>
import LeftLogo from 'src/components/organisms/find/LeftLogo.vue';
import RightIDForm from 'src/components/organisms/find/RightIDForm.vue';
export default {
	name: 'IDFindPage',
	setup() {
		return {};
	},
	components: { LeftLogo, RightIDForm },
};
</script>

<style>
.login {
	height: 100vh !important;
	width: 100vw !important;
	background-color: #eeeeee !important;
}

.login-box {
	margin: auto !important;
}
</style>
