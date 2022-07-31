<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

import { useUsersStore, useAlertStore } from 'src/stores';
import IDChkButton from 'src/components/molecules/signup/IDChkButton.vue';

const name = ref('');
const id = ref('');
const role = ref('');
const password = ref('');
const passwordCheck = ref('');
const email = ref('');
const phone = ref('');
const nickname = ref('');
const birthDt = ref('');

const router = useRouter();

async function onSubmit() {
	let user = {
		name: name.value,
		id: id.value,
		password: password.value,
		email: email.value,
		phone: phone.value,
		nickname: nickname.value,
		birthDt: birthDt.value,
		role: role.value,
	};

	const usersStore = useUsersStore();
	const alertStore = useAlertStore();
	try {
		await usersStore.register(user);
		await router.push({ path: '/login' });
		alertStore.success('Registration successful');
		console.log('Registration successful');
	} catch (error) {
		alertStore.error(error);
	}
}
// form 리셋 함수
function onReset() {
	name.value = null;
	id.value = null;
	role.value = null;
	password.value = null;
	passwordCheck.value = null;
	email.value = null;
	phone.value = null;
	nickname.value = null;
	birthDt.value = null;
}
</script>

<template>
	<div
		style="
			width: 800px;
			max-width: 70vw;
			background-color: #303841;
			background-image: linear-gradient(to right, #303841 0%, #596164 100%);
			border-radius: 20px;
		"
		class="q-pa-lg shadow"
	>
		<div class="q-ma-lg">
			<p class="col" style="font-size: 100px; color: white">Register</p>
			<!-- <AtomLogoTrans></AtomLogoTrans> -->
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
						type="text"
						name="name"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						v-model="name"
						label="이름"
						placeholder="국문 5자 이내"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<div class="row q-pr-lg">
						<q-input
							type="text"
							name="id"
							class="q-mx-lg col"
							rounded
							outlined
							v-model="id"
							label="아이디"
							lazy-rules
							color="brand"
							bg-color="white"
						></q-input>
						<IDChkButton class="flex q-pr-lg q-py-sm"></IDChkButton>
					</div>
					<q-input
						name="password"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						type="password"
						v-model="password"
						label="비밀번호"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						class="q-pr-lg q-ma-lg"
						rounded
						outlined
						type="password"
						v-model="passwordCheck"
						label="비밀번호 확인"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
				</div>
				<div class="col q-ml-sm">
					<q-input
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						name="email"
						type="email"
						v-model="email"
						label="이메일"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						name="phone"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						type="tel"
						v-model="phone"
						label="전화번호"
						placeholder="010-0000-0000"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						name="nickname"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						v-model="nickname"
						label="닉네임"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						name="birthDt"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						label="생일"
						v-model="birthDt"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<!-- <q-input
						name="role"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						label="role"
						v-model="role"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input> -->
				</div>
			</div>
			<div class="flex justify-center">
				<div>
					<q-btn
						label="가입하기"
						type="submit"
						text-color="white"
						class="q-px-md q-mr-sm"
						style="background: #00adb5"
						push
					></q-btn>
					<q-btn
						label="Reset"
						type="reset"
						text-color="white"
						style="background-color: orangered"
						push
						class="q-ml-sm"
					></q-btn>
				</div>
				<div id="q-app" class="q-mt-lg">
					<div class="q-pa-md text-white">
						<div class="flex justify-center q-mr-md">
							<q-radio
								dark
								v-model="role"
								val="teacher"
								label="강사"
								color="brand"
							></q-radio>
							<q-radio
								dark
								text-
								v-model="role"
								val="student"
								label="학생"
								color="brand"
							></q-radio>
						</div>

						<div class="role flex justify-center">
							<strong>{{ role }}</strong
							>(으)로 가입합니다.
						</div>
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
</style>
