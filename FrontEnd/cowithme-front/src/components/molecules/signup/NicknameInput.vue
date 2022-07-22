<template>
	<div>
		<AtomBasicInput
			label="Nickname"
			placeholder="닉네임을 입력해주세요"
		></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'NicknameInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
