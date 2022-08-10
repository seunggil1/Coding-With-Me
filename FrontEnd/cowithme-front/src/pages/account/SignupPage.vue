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
@import url('src/css/app.css');

.signup {
	height: 100vh !important;
	width: 100vw !important;
	background-color: #eeeeee !important;
}

/* .signup-box {
	margin: auto;
} */
</style>
