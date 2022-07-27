<template>
	<div class="login flex">
		<div class="login-box row justify-center">
			<LeftLogo></LeftLogo>
			<RightForm></RightForm>
		</div>
	</div>
</template>

<script>
import LeftLogo from 'src/components/organisms/login/LeftLogo.vue';
import RightForm from 'src/components/organisms/login/RightForm.vue';
export default {
	name: 'LoginPage',
	setup() {
		return {};
	},
	components: { LeftLogo, RightForm },
};
</script>

<style>
.login {
	height: 100vh;
	width: 100vw;
	background-color: #808080;
}

.login-box {
	margin: auto;
}
</style>
