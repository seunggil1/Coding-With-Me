<template>
	<!-- <LoginPage></LoginPage> -->
	<div>
		<SelectRoleButton></SelectRoleButton>
		<IDIput></IDIput>
		<PWInput></PWInput>
		<LoginButton></LoginButton>
		<AtomLogo></AtomLogo>
	</div>
</template>

<script>
import { defineComponent } from 'vue';
// import LoginPage from './LoginPage.vue';
import SelectRoleButton from 'src/components/molecules/login/SelectRoleButton.vue';
import IDIput from 'src/components/molecules/login/IDIput.vue';
import PWInput from 'src/components/molecules/login/PWInput.vue';
import LoginButton from 'src/components/molecules/login/LoginButton.vue';
import AtomLogo from 'src/components/atoms/AtomLogo.vue';

export default defineComponent({
	name: 'IndexPage',
	components: { SelectRoleButton, IDIput, PWInput, LoginButton, AtomLogo },
});
</script>

<style>
/* LoginPage {
	height: 100vh;
	width: 100vw;
	background-color: red;
	background-size: cover;
	background-position: center;
	margin: 0;
} */
</style>
