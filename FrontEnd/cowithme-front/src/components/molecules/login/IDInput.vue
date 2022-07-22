<template>
	<div>
		<AtomBasicInput
			label="ID"
			placeholder="아이디를 입력해주세요"
		></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'IDInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
