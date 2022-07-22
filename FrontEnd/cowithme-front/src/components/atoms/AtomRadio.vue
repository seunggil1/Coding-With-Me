<template>
	<div class="q-gutter-sm">
		<q-radio v-model="auth" val="teacher" label="강사"></q-radio>
		<q-radio v-model="auth" val="student" label="학생"></q-radio>
	</div>
</template>

<script>
import { ref } from 'vue';

export default {
	name: 'AtomButton',
	setup() {
		return {
			auth: ref('teacher'),
		};
	},
};
</script>

<style lang="scss" scoped></style>
