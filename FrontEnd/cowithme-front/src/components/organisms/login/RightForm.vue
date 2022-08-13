<script setup>
import { ref } from 'vue';
import { useAuthStore } from 'src/stores';
import { useRouter } from 'vue-router';
import { useQuasar } from 'quasar';

const id = ref('');
const password = ref('');
const $q = useQuasar();

const router = useRouter();

async function onSubmit() {
	let idpw = {
		id: id.value,
		pw: password.value,
	};
	// console.log(idpw.id, idpw.pw);
	const authStore = useAuthStore();
	await authStore.login(idpw.id, idpw.pw);
	console.log(authStore.isLoginFail);
	if (authStore.isLoginFail) {
		$q.notify({
			type: 'negative',
			message: '아이디 또는 비밀번호를 확인해주세요',
		});
		id.value = '';
		password.value = '';
		return;
	}
	await router.push({ path: '/home' });
}
</script>

<template>
	<div class="top-right-form q-pa-lg">
		<div class="q-ma-lg">
			<p class="col login-text">Login</p>
		</div>
		<q-form @submit="onSubmit" class="q-gutter-md signup-form" lazy-validation>
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
						color="secondary"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '아이디를 입력해주세요.',
						]"
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
						color="secondary"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '비밀번호를 입력해주세요.',
						]"
					></q-input>
					<div class="q-pr-lg" style="margin-left: 30px">
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
								style="background-color: #303841"
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
.text-brand {
	color: #00adb5 !important;
}
.brand {
	color: #00adb5 !important;
}
.signup-form {
	font-family: 'Elice Digital Baeum', sans-serif !important;
	font-size: 18px !important;
}
.role {
	font-size: 16px !important;
	font-family: 'GmarketSansLight' !important;
}
.shadow {
	box-shadow: 0 17px 20px -18px rgba(0, 0, 0, 1) !important;
}
.loginInput {
	width: 50% !important;
}
.top-right-form {
	height: 100% !important;
}
.login-text {
	font-size: 100px !important;
	color: white !important;
	font-family: 'Elice Digital Baeum', sans-serif !important;
}
@font-face {
	font-family: 'MICEGothic Bold' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2') !important;
	font-weight: 700 !important;
	font-style: normal !important;
}
</style>
