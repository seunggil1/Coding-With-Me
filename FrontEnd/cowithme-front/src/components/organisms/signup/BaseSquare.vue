<script setup>
import { ref } from 'vue';
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

import { useUsersStore, useAlertStore } from 'src/stores';

const date = ref('');

const schema = Yup.object().shape({
	birthDt: Yup.string().required('Birthday is required'),
	email: Yup.string().required('Email is required'),
	id: Yup.string().required('Id is required'),
	name: Yup.string().required('Name is required'),
	nickname: Yup.string().required('Nickname is required'),
	password: Yup.string()
		.required('Password is required')
		.min(6, 'Password must be at least 6 characters'),
	phone: Yup.string().required('Phone number is required'),
});

async function onSubmit(values) {
	const usersStore = useUsersStore();
	const alertStore = useAlertStore();
	try {
		await usersStore.register(values);
		// await router.push('/account/login');
		alertStore.success('Registration successful');
	} catch (error) {
		alertStore.error(error);
	}
}
</script>

<template>
	<div class="q-pa-md" style="max-width: 400px">
		<q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
			<q-input
				filled
				v-model="date"
				label="Your name *"
				hint="Name and surname"
				lazy-rules
				:rules="[val => (val && val.length > 0) || 'Please type something']"
			></q-input>

			<q-input
				filled
				type="number"
				v-model="age"
				label="Your age *"
				lazy-rules
				:rules="[
					val => (val !== null && val !== '') || 'Please type your age',
					val => (val > 0 && val < 100) || 'Please type a real age',
				]"
			></q-input>

			<q-toggle
				v-model="accept"
				label="I accept the license and terms"
			></q-toggle>

			<div>
				<q-btn label="Submit" type="submit" color="primary"></q-btn>
				<q-btn
					label="Reset"
					type="reset"
					color="primary"
					flat
					class="q-ml-sm"
				></q-btn>
			</div>
		</q-form>
	</div>
	<!-- <div class="card m-3">
		<h4 class="card-header">회원가입</h4>
		<div class="card-body">
			<Form @submit="onSubmit" :validation-schema="schema">
				<div class="form-group">
					<label>생년월일</label>
					<Field name="birthDt" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>email</label>
					<Field name="email" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>id</label>
					<Field name="id" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>name</label>
					<Field name="name" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<button class="btn btn-primary" :disabled="isSubmitting">
						<span
							v-show="isSubmitting"
							class="spinner-border spinner-border-sm mr-1"
						></span>
						Register
					</button>
					<router-link to="login" class="btn btn-link">Cancel</router-link>
				</div>
				<div class="form-group">
					<label>nickname</label>
					<Field name="nickname" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>Username</label>
					<Field name="username" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>password</label>
					<Field name="password" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>phone</label>
					<Field name="phone" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label>role</label>
					<Field name="role" type="text" class="form-control" />
				</div>
			</Form>
		</div>
	</div> -->
</template>
