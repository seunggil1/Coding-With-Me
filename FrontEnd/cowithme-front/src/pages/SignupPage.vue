<template>
	<div class="login flex">
		<div class="login-box row justify-center">
			<BaseSquare></BaseSquare>
		</div>
	</div>
</template>

<script>
import BaseSquare from 'src/components/organisms/signup/BaseSquare.vue';
export default {
	name: 'SignupPage',
	setup() {
		return {};
	},
	components: { BaseSquare },
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
