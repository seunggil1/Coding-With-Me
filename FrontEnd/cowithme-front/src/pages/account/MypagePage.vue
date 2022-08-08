<template>
	<div
		class="box q-ma-lg q-pa-md q-gutter-md"
		style="font-family: 'Elice Digital Baeum'"
	>
		<q-form
			@submit="goEditInfo"
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
						clearable
						outlined
						v-model="name"
						label="이름"
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						type="text"
						name="id"
						clearable
						rounded
						class="q-ma-lg q-pr-lg"
						outlined
						v-model="id"
						label="아이디"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						name="email"
						type="email"
						clearable
						v-model="email"
						label="이메일"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
				</div>
				<div class="col q-ml-sm">
					<q-input
						name="phone"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						type="tel"
						v-model="phone"
						clearable
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
						clearable
						v-model="nickname"
						lazy-rules
						color="brand"
						bg-color="white"
					></q-input>
					<q-input
						name="birthDt"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						type="date"
						v-model="birthDt"
						lazy-rules
						clearable
						color="brand"
						bg-color="white"
					></q-input>
					<!-- <q-input
						name="profilePath"
						class="q-ma-lg q-pr-lg"
						rounded
						outlined
						clearable
						type="file"
						lazy-rules
						hint="프로필 사진 등록"
						color="brand"
						bg-color="white"
						@update:model-value="
							val => {
								file = val[0];
							}
						"
					></q-input> -->
				</div>
			</div>
			<div class="flex justify-center">
				<div>
					<q-btn
						label="수정완료"
						type="submit"
						text-color="white"
						class="q-px-md q-mr-sm"
						style="background: #00adb5"
						push
					></q-btn>
				</div>
			</div>
		</q-form>
		<q-btn style="background: red" @click="goDelete" push>회원탈퇴</q-btn>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useUsersStore } from 'src/stores';
import { onBeforeMount } from 'vue';
import { ref } from 'vue';
// import { api } from 'src/boot/axios.js';
const router = useRouter();

const myInfo = ref([]);
const info = JSON.parse(localStorage.getItem('info'));

const name = ref(`${info.name}`);
const id = ref(`${info.id}`);
const email = ref(`${info.email}`);
const phone = ref(`${info.phone}`);
const nickname = ref(`${info.nickname}`);
const birthDt = ref(`${info.birthDt}`);
const password = ref('');
const file = ref('');
const role = ref(`${info.role}`);

async function goEditInfo() {
	let params = {
		name: name.value,
		id: id.value,
		email: email.value,
		phone: phone.value,
		nickname: nickname.value,
		birthDt: birthDt.value,
		password: password.value,
		profilePath: file.value,
		role: role.value,
	};
	const usersStore = useUsersStore();
	try {
		await usersStore.update(params);
		router.push('/join');
	} catch (error) {
		console.log(error);
	}
}
onBeforeMount(async () => {
	myInfo.value.push(info);
	console.log(myInfo);
});
console.log(myInfo.value);
async function goDelete() {
	let user = {
		name: info.name.value,
		email: info.email,
		password: info.password,
		role: info.role,
		id: info.id,
		birthDt: info.birthDt,
		phone: info.phone,
		nickname: info.nickname,
	};
	const usersStore = useUsersStore();
	await usersStore.delete(user);
	await router.push({ path: '/join' });
}
</script>

<style lang="scss" scoped>
.box {
	height: 100%;
	width: 60%;
	min-width: 700px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
