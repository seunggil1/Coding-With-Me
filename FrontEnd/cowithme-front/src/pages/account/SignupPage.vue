<template>
	<div class="signup">
		<div class="signup-box row justify-center">
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
.signup {
	height: 100vh;
	width: 100vw;
	background-color: #eeeeee;
}

/* .signup-box {
	margin: auto;
} */
</style>
