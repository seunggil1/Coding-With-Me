<template>
	<div
		class="left flex justify-center"
		style="
			background-color: #303841;
			background-image: linear-gradient(to right, #303841 0%, #596164 100%);
			height: 100%;
		"
	>
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
	background-color: #303841 !important;
}
</style>
