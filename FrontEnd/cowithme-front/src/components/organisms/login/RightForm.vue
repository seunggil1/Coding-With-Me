<script setup>
import { ref } from 'vue';
import { useAuthStore } from 'src/stores';

// import { Form, Field } from 'vee-validate';
// import * as Yup from 'yup';

// const schema = Yup.object().shape({
// 	id: Yup.string().required('id is required'),
// 	password: Yup.string().required('Password is required'),
// });
const id = ref('');
const password = ref('');

async function onSubmit() {
	let user = {
		id: id.value,
		password: password.value,
	};
	const authStore = useAuthStore();
	// console.log(_value);
	const idid = user.id;
	const pwpw = user.password;
	await authStore.login(idid, pwpw);
}
</script>

<template>
	<div class="q-ma-xl flex">
		<div class="q-mt-xl">
			<p class="q-mb-xl" style="font-size: 100px; color: #808080">Login</p>
			<!-- <AtomLogoTrans></AtomLogoTrans> -->
			<q-form
				@submit="onSubmit"
				@reset="onReset"
				class="q-gutter-md signup-form"
				lazy-validation
			>
				<div class="q-my-lg">
					<q-input
						class="q-my-lg"
						type="text"
						name="id"
						rounded
						outlined
						v-model="id"
						label="아이디"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						class="q-mb-lg"
						name="password"
						rounded
						outlined
						type="password"
						v-model="password"
						label="비밀번호"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
				</div>

				<q-btn
					label="로그인"
					type="submit"
					text-color="white"
					style="background: #00adb5"
					push
				></q-btn>
			</q-form>
		</div>
	</div>
</template>

<style scoped>
@font-face {
	font-family: 'GmarketSansLight';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
.text-brand {
	color: #00adb5 !important;
}
.brand {
	color: #00adb5 !important;
}
.signup-form {
	font-family: 'Elice Digital Baeum', sans-serif;
	font-size: 18px;
}
.role {
	font-size: 16px !important;
	font-family: 'GmarketSansLight' !important;
}
.shadow {
	box-shadow: 0 17px 20px -18px rgba(0, 0, 0, 1);
}
</style>
