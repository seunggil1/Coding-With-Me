<template>
	<div class="left row justify-center">
		<AtomLogo class="hvr-grow"></AtomLogo>
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
@import url('src/css/app.css');

.left {
	height: 800px !important;
	width: 500px !important;
	background-color: #303841 !important;
}
</style>
