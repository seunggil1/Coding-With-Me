<script setup>
import { ref } from 'vue';
import { useAuthStore } from 'src/stores';
import { useRouter } from 'vue-router';

const id = ref('');
const password = ref('');

const router = useRouter();

async function onSubmit() {
	let idpw = {
		id: id.value,
		pw: password.value,
	};
	console.log(idpw.id, idpw.pw);
	const authStore = useAuthStore();
	await authStore.login(idpw.id, idpw.pw);
	await router.push({ path: '/' });
}
// form 리셋 함수
function onReset() {
	id.value = null;
	password.value = null;
}
</script>

<template>
	<div style="" class="q-pa-lg">
		<div class="q-ma-lg">
			<p class="col" style="font-size: 100px; color: white">Login</p>
		</div>
		<q-form
			@submit="onSubmit"
			@reset="onReset"
			class="q-gutter-md signup-form"
			lazy-validation
		>
			<div class="flex row">
				<div class="col q-mr-sm">
					<q-input
						name="password"
						class="loginInput q-ma-lg q-pr-lg"
						rounded
						outlined
						type="text"
						v-model="id"
						label="아이디"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>

					<q-input
						name="password"
						class="loginInput q-ma-lg q-pr-lg"
						rounded
						outlined
						type="password"
						v-model="password"
						label="비밀번호"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<div
						class="q-ma-lg q-pr-lg"
						style="margin-top: 100px; margin-left: 30px"
					>
						<q-btn
							label="로그인"
							type="submit"
							text-color="white"
							class=""
							style="background: #00adb5"
							push
						></q-btn>
						<router-link
							:to="{ name: 'join' }"
							style="text-decoration: none; color: inherit"
						>
							<q-btn
								label="회원가입"
								text-color="white"
								style="background-color: #ff5722"
								push
								class="q-ml-sm"
							></q-btn
						></router-link>
					</div>
				</div>
			</div>
		</q-form>
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
.loginInput {
	width: 50%;
}
</style>
