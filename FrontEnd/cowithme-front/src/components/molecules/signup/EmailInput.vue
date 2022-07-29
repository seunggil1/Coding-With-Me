<template>
	<div>
		<q-input
			v-model="email"
			type="email"
			label="Email"
			placeholder="이메일을 입력해주세요"
			filled
			color="brand"
			bg-color="white"
		></q-input>
	</div>
</template>

<script>
import { ref } from 'vue';
// import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'EmailInput',
	// components: { AtomBasicInput },
	setup() {
		return { email: ref('') };
	},
};
</script>

<style></style>
