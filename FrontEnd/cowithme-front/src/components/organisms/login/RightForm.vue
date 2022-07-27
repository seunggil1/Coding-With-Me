<template>
	<div class="right">
		<LoginText class="component"></LoginText>
		<div class="card m-3">
			<h4 class="card-header">Login</h4>
			<div class="card-body">
				<Form @submit="onSubmit" :validation-schema="schema">
					<div class="form-group">
						<label>Username</label>
						<Field name="id" type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label>Password</label>
						<Field name="password" type="password" class="form-control" />
					</div>
					<div class="form-group">
						<button class="btn btn-primary" :disabled="isSubmitting">
							<span
								v-show="isSubmitting"
								class="spinner-border spinner-border-sm mr-1"
							></span>
							Login
						</button>
					</div>
				</Form>
			</div>
		</div>
		<!-- <div id="q-app" style="min-height: 100vh">
				<div class="q-pa-md" style="max-width: 400px">
					<q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
						<q-input
							filled
							v-model="id"
							name="id"
							label="Your name *"
							hint="Name and surname"
							lazy-rules
							:rules="[
								val => (val && val.length > 0) || 'Please type something',
							]"
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
			</div> -->

		<!-- <div class="form-container">
				<IDInput name="id" class="component"></IDInput>
				<PWInput name="password" class="component"></PWInput>
			</div>
			<FindIDPW class="find"></FindIDPW>
			<LoginButton class="login-button"></LoginButton> -->
	</div>
</template>

<script>
import { Form, Field } from 'vee-validate';

import * as Yup from 'yup';
import { useAuthStore } from 'src/stores';
// import { ref } from 'vue';

const schema = Yup.object().shape({
	id: Yup.string().required('id is required'),
	password: Yup.string().required('Password is required'),
});

import LoginText from 'src/components/molecules/login/LoginText.vue';
// import IDInput from 'src/components/molecules/login/IDInput.vue';
// import PWInput from 'src/components/molecules/login/PWInput.vue';
// import FindIDPW from 'src/components/molecules/login/FindIDPW.vue';
// import LoginButton from 'src/components/molecules/login/LoginButton.vue';
// import SelectRoleButton from 'src/components/molecules/login/SelectRoleButton.vue';

export default {
	name: 'RightForm',
	setup() {
		(async function onSubmit(values) {
			const authStore = useAuthStore();
			const { id, password } = values;
			await authStore.login(id, password);
		})();
	},
	components: {
		LoginText,
		// IDInput,
		// PWInput,
		// FindIDPW,
		// LoginButton,
		// SelectRoleButton,
	},
};
</script>

<style lang="scss" scoped>
.right {
	height: 800px;
	width: 500px;
	background-color: white;
}

.container {
	margin-top: 100px;
	margin-left: 50px;
}

.form-container {
	margin-top: 50px;
	margin-right: 50px;
}

.component {
	margin-bottom: 20px;
}

.find {
	margin-top: 20px;
}

.login-button {
	margin-top: 50px;
}

.role-button {
	margin-top: 30px;
}
</style>
