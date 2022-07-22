<template>
	<div>
		<AtomBasicInput
			label="Name"
			placeholder="이름을 입력해주세요"
		></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'NameInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
