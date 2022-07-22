<template>
	<div>
		<AtomBasic1Button label="회원가입하기"></AtomBasic1Button>
	</div>
</template>

<script>
import AtomBasic1Button from 'src/components/atoms/AtomBasic1Button.vue';
export default {
	name: 'RegisterButton',
	components: { AtomBasic1Button },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
