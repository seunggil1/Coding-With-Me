<template>
	<div class="login flex row">
		<div class="col-6">
			<LeftLogo></LeftLogo>
		</div>
		<div class="col">
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
	background-color: #eeeeee !important;
}
</style>
