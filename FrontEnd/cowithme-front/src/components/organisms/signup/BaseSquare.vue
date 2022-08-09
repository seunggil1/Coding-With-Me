<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUsersStore, useAlertStore } from 'src/stores';
import IDChkButton from 'src/components/molecules/signup/IDChkButton.vue';
import { api } from 'src/boot/axios.js';
// import EmailChkButton from 'src/components/molecules/signup/EmailChkButton.vue';

// 회원가입 정보
const name = ref('');
const id = ref('');
const role = ref('');
const password = ref('');
const passwordCheck = ref('');
const email = ref('');
const phone = ref('');
const nickname = ref('');
const birthDt = ref('');

// router 사용 선언
const router = useRouter();

// 회원가입하러 가자
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
function idCheck() {
	api
		.get(`/users/idcheck/${id.value}`)
		.then(res => {
			console.log(res.data.message);
			openGood('bottom');
		})
		.catch(err => {
			console.log(err);
			openBad('bottom');
		});
}
// 이메일 인증
// function emailCheck() {
// 	api.get(`/users/vemail/code`);.then(res=> {console.log(res.data.message)};
//   )
//     .catch(err => {console.log(err)});
// }

// id check dialog
const dialogGood = ref(false);
const dialogBad = ref(false);

const position = ref('top');

function openGood(pos) {
	position.value = pos;
	dialogGood.value = true;
}
function openBad(pos) {
	position.value = pos;
	dialogBad.value = true;
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
			background-image: linear-gradient(to right, #274046 0%, #bdc3c7 100%);
			border-radius: 20px;
			margin-top: 2%;
		"
		class="q-pa-lg shadow"
	>
		<!-- 중복되지 않은 id일 때 -->
		<q-dialog v-model="dialogGood" :position="position">
			<q-card style="width: 350px; background-color: green">
				<q-card-section class="row items-center no-wrap">
					<div style="color: white">사용해도 좋은 id입니다.</div>
				</q-card-section>
			</q-card>
		</q-dialog>
		<!-- 중복된 id일 때 -->
		<q-dialog v-model="dialogBad" :position="position">
			<q-card style="width: 350px; background-color: orangered">
				<q-card-section class="row items-center no-wrap">
					<div style="color: white">
						중복된 id입니다. 다른 id를 입력해주세요.
					</div>
				</q-card-section>
			</q-card>
		</q-dialog>

		<div class="q-ma-lg">
			<p
				class="col"
				style="
					font-size: 100px;
					color: white;
					font-family: 'Elice Digital Baeum', sans-serif;
				"
			>
				회원가입
			</p>
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
						lazy-rules
						color="brand"
						bg-color="white"
						:rules="[val => (val && val.length > 0) || '이름을 입력해주세요.']"
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
							:rules="[
								val => (val && val.length > 0) || '아이디를 입력해주세요.',
							]"
						></q-input>
						<IDChkButton @click="idCheck" class="q-pr-lg q-py-sm"></IDChkButton>
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
						:rules="[
							val => (val && val.length > 0) || '비밀번호를 입력해주세요.',
						]"
					></q-input>
					<q-input
						class="q-pr-lg q-mx-lg"
						rounded
						outlined
						type="password"
						v-model="passwordCheck"
						label="비밀번호 확인"
						lazy-rules
						color="brand"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '비밀번호를 입력해주세요.',
							val =>
								(val && val === password) || '비밀번호가 일치하지 않습니다.',
						]"
					></q-input>
				</div>
				<div class="col q-ml-sm">
					<q-input
						class="q-mx-lg q-mt-lg col-7 q-pr-lg"
						rounded
						outlined
						name="email"
						type="email"
						v-model="email"
						label="이메일"
						lazy-rules
						color="brand"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '이메일을 입력해주세요.',
							val =>
								(val && val.includes('@')) ||
								'이메일 형식이 올바르지 않습니다.',
						]"
					></q-input>
					<q-input
						name="phone"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						type="tel"
						v-model="phone"
						label="전화번호"
						mask="###-####-####"
						unmasked-value
						lazy-rules
						color="brand"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '전화번호를 입력해주세요.',
						]"
					></q-input>
					<q-input
						name="nickname"
						class="q-mx-lg q-mb-lg q-pr-lg"
						rounded
						outlined
						v-model="nickname"
						label="닉네임"
						lazy-rules
						color="brand"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '닉네임을 입력해주세요.',
						]"
					></q-input>
					<q-input
						name="birthDt"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						type="date"
						v-model="birthDt"
						lazy-rules
						color="brand"
						bg-color="white"
						:rules="[
							val => (val && val.length > 0) || '생년월일을 입력해주세요.',
						]"
					></q-input>
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
				<div id="q-app" class="q-mt-lg" style="color: #274046">
					<div class="q-pa-md">
						<div class="flex justify-center q-mr-md">
							<q-radio
								dark
								checked-icon="task_alt"
								unchecked-icon="panorama_fish_eye"
								v-model="role"
								val="강사"
								label="강사"
								color="brand"
							></q-radio>
							<q-radio
								dark
								checked-icon="task_alt"
								unchecked-icon="panorama_fish_eye"
								v-model="role"
								val="학생"
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
