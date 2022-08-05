<template>
	<div>
		<p>시험 생성 form</p>
		<p>{{ classId }}</p>
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
</template>

<script>
// import { ref } from 'vue';
export default {
	props: {
		classId: {
			type: String,
			// required: true,
		},
	},
	name: 'MakeExamPage',
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
