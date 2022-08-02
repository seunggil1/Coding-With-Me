<script setup>
import { ref } from 'vue';
import { useAuthStore } from 'src/stores';
import { useRouter } from 'vue-router';

// import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

const schema = Yup.object().shape({
	id: Yup.string().required('id is required'),
	password: Yup.string().required('Password is required'),
});
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
			:validation-schema="schema"
			@submit="onSubmit"
			@reset="onReset"
			class="q-gutter-md signup-form"
			lazy-validation
		>
			<div class="flex row">
				<div class="col q-mr-sm">
					<div class="row q-pr-lg">
						<q-input
							name="password"
							class="q-ma-lg q-pr-lg"
							rounded
							outlined
							type="text"
							v-model="id"
							label="아이디"
							lazy-rules
							color="brand"
							bg-color="white"
						></q-input>
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
				</div>
			</div>
			<div class="flex justify-center">
				<div>
					<q-btn
						label="로그인"
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
			</div>
		</q-form>
	</div>
	<!-- <div class="card m-3">
		<h4 class="card-header">Login</h4>
		<div class="card-body">
			<Form
				@submit="onSubmit"
				:validation-schema="schema"
				v-slot="{ errors, isSubmitting }"
			>
				<div class="form-group">
					<label>id</label>
					<Field
						name="id"
						type="text"
						class="form-control"
						:class="{ 'is-invalid': errors.id }"
					/>
					<div class="invalid-feedback">{{ errors.id }}</div>
				</div>
				<div class="form-group">
					<label>Password</label>
					<Field
						name="password"
						type="password"
						class="form-control"
						:class="{ 'is-invalid': errors.password }"
					/>
					<div class="invalid-feedback">{{ errors.password }}</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" :disabled="isSubmitting">
						<span
							v-show="isSubmitting"
							class="spinner-border spinner-border-sm mr-1"
						></span>
						Login
					</button>
					<router-link to="register" class="btn btn-link">Register</router-link>
				</div>
			</Form>
		</div>
	</div> -->
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
