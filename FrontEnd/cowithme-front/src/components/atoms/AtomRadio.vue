<template>
	<div class="q-gutter-sm">
		<q-radio v-model="auth" val="teacher" label="강사"></q-radio>
		<q-radio v-model="auth" val="student" label="학생"></q-radio>
		<div class="q-px-sm flex justify-center">
			<strong>{{ auth }}</strong
			>으로 가입합니다.
		</div>
	</div>
</template>

<script>
import { ref } from 'vue';

export default {
	name: 'AtomRadio',
	setup() {
		return {
			auth: ref('teacher'),
		};
	},
};
</script>

<style lang="scss" scoped></style>
