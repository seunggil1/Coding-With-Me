<template>
	<div>
		<AtomBasicInput
			label="PW Confirm"
			placeholder="비밀번호를 한번 더 입력해주세요"
		></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'PWConfirmInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
