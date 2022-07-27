<script setup>
import { Form, Field } from 'vee-validate';
// import * as Yup from 'yup';

import { useUsersStore, useAlertStore } from 'src/stores';

// const schema = Yup.object().shape({
// 	firstName: Yup.string().required('First Name is required'),
// 	lastName: Yup.string().required('Last Name is required'),
// 	username: Yup.string().required('Username is required'),
// 	// password: Yup.string()
// 	// 	.required('Password is required')
// 	// 	.min(6, 'Password must be at least 6 characters'),
// });

async function onSubmit(values) {
	const usersStore = useUsersStore();
	const alertStore = useAlertStore();
	try {
		await usersStore.register(values);
		await router.push('/account/login');
		alertStore.success('Registration successful');
	} catch (error) {
		alertStore.error(error);
	}
}
</script>

<template>
	<div class="card m-3">
		<h4 class="card-header">Register</h4>
		<div class="card-body">
			<Form @submit="onSubmit" :validation-schema="schema">
				<div class="form-group">
					<label>birthDt</label>
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
	</div>
</template>
