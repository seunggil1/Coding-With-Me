<template>
	<div>
		<AtomBasic1Button label="로그인"></AtomBasic1Button>
	</div>
</template>

<script>
import AtomBasic1Button from 'src/components/atoms/AtomBasic1Button.vue';
export default {
	name: 'LoginButton',
	components: { AtomBasic1Button },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
