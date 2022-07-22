<template>
	<div>
		<AtomBasicInput
			label="Email"
			placeholder="이메일을 입력해주세요"
		></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'EmailInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
