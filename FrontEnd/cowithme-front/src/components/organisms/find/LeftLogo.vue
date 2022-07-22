<template>
	<div class="left row justify-center">
		<AtomLogo></AtomLogo>
	</div>
</template>

<script>
import AtomLogo from 'src/components/atoms/AtomLogo.vue';
export default {
	components: { AtomLogo },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped>
.left {
	height: 800px;
	width: 500px;
	background-color: #303841;
}
</style>
