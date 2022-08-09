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
	height: 800px !important;
	width: 500px !important;
	background-color: #303841 !important;
}
</style>
