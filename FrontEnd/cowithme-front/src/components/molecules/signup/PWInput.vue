<template>
	<div>
		<AtomBasicInput
			label="PW"
			placeholder="비밀번호를 입력해주세요"
		></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'PWInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
