<template>
	<div class="login flex">
		<div class="login-box row justify-center">
			<LeftLogo></LeftLogo>
			<RightPWForm></RightPWForm>
		</div>
	</div>
</template>

<script>
import LeftLogo from 'src/components/organisms/find/LeftLogo.vue';
import RightPWForm from 'src/components/organisms/find/RightPWForm.vue';
export default {
	name: 'PWFindPage',
	setup() {
		return {};
	},
	components: { LeftLogo, RightPWForm },
};
</script>

<style>
.login {
	height: 100vh;
	width: 100vw;
	background-color: grey;
}

.login-box {
	margin: auto;
}
</style>
