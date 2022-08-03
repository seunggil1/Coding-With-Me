<template>
	<div>
		<div>선생님이 반을 만들 수 있다.</div>
		<div id="q-app" style="min-height: 100vh">
			<div class="q-pa-md" style="max-width: 400px">
				<q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
					<q-input
						filled
						v-model="name"
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
		</div>
	</div>
</template>

<script>
import { ref } from 'vue';
import { useQuasar } from 'quasar';

export default {
	setup() {
		const $q = useQuasar();

		const name = ref(null);
		const age = ref(null);
		const accept = ref(false);

		return {
			name,
			age,
			accept,

			onSubmit() {
				if (accept.value !== true) {
					$q.notify({
						color: 'red-5',
						textColor: 'white',
						icon: 'warning',
						message: 'You need to accept the license and terms first',
					});
				} else {
					$q.notify({
						color: 'green-4',
						textColor: 'white',
						icon: 'cloud_done',
						message: 'Submitted',
					});
				}
			},

			onReset() {
				name.value = null;
				age.value = null;
				accept.value = false;
			},
		};
	},
};
</script>

<style scoped></style>
