<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUsersStore, useAlertStore } from 'src/stores';
import IDChkButton from 'src/components/molecules/signup/IDChkButton.vue';
import { api } from 'src/boot/axios.js';
import { useQuasar } from 'quasar';

const $q = useQuasar();

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
	if (role.value == '' || role.value == null) {
		$q.notify({
			type: 'negative',
			message: '강사 또는 학생을 선택해주세요',
		});
		return;
	}
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
		$q.notify({
			type: 'positive',
			message: '회원가입이 완료 되었습니다.',
		});
		await router.push({ path: '/login' });
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
</script>

<template>
	<div
		style="
			width: 800px;
			max-width: 70vw;
			max-height: 90vh;
			background-color: #303841;
			background-image: linear-gradient(to right, #274046 0%, #bdc3c7 100%);
			border-radius: 20px;
			margin-top: 2%;
		"
		class="q-pa-lg shadow hvr-grow"
	>
		<!-- 중복되지 않은 id일 때 -->
		<q-dialog v-model="dialogGood" :position="position">
			<q-card style="width: 350px; background-color: green">
				<q-card-section class="row items-center no-wrap">
					<div style="font-family: 'Elice Digital Baeum'; color: white">
						사용해도 좋은 id입니다.
					</div>
				</q-card-section>
			</q-card>
		</q-dialog>
		<!-- 중복된 id일 때 -->
		<q-dialog v-model="dialogBad" :position="position">
			<q-card style="width: 350px; background-color: orangered">
				<q-card-section class="row items-center no-wrap">
					<div style="font-family: 'Elice Digital Baeum'; color: white">
						중복된 id입니다. 다른 id를 입력해주세요.
					</div>
				</q-card-section>
			</q-card>
		</q-dialog>

		<div class="q-ma-lg">
			<p
				class="col"
				style="
					font-size: 50px;
					color: white;
					font-family: 'Elice Digital Baeum', sans-serif;
				"
			>
				Sign In
			</p>
		</div>

		<q-form @submit="onSubmit" class="q-gutter-md signup-form" lazy-validation>
			<div class="flex row">
				<div class="col q-mr-sm">
					<q-input
						autofocus
						type="text"
						name="name"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						v-model="name"
						label="이름"
						lazy-rules
						color="secondary"
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
							color="secondary"
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
						color="secondary"
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
						color="secondary"
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
						color="secondary"
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
						color="secondary"
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
						color="secondary"
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
						color="secondary"
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
						style="background: #ff5722"
						push
					></q-btn>
					<router-link
						:to="{ name: 'login' }"
						style="text-decoration: none; color: inherit"
					>
						<q-btn
							label="로그인하기"
							text-color="white"
							style="background-color: #00adb5"
							push
							class="q-ml-sm"
						></q-btn>
					</router-link>
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
								color="secondary"
							></q-radio>
							<q-radio
								dark
								checked-icon="task_alt"
								unchecked-icon="panorama_fish_eye"
								v-model="role"
								val="학생"
								label="학생"
								color="secondary"
							></q-radio>
						</div>

						<div
							style="font-family: 'Elice Digital Baeum'"
							class="role flex justify-center"
						>
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
@import url('src/css/app.css');

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

.shadow {
	box-shadow: 0 17px 20px -18px rgba(0, 0, 0, 1) !important;
}
</style>
