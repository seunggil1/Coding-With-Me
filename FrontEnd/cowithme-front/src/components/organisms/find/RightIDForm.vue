<template>
	<div class="right">
		<FindIDPWText></FindIDPWText>
		<NameInput></NameInput>
		<BirthInput></BirthInput>
		<FindButton></FindButton>
	</div>
</template>

<script>
import FindIDPWText from 'src/components/molecules/find/FindIDPWText.vue';
import NameInput from 'src/components/molecules/find/NameInput.vue';
import BirthInput from 'src/components/molecules/find/BirthInput.vue';
import FindButton from 'src/components/molecules/find/FindButton.vue';
export default {
	name: 'RightIDForm',
	setup() {
		return {};
	},
	components: { FindIDPWText, NameInput, BirthInput, FindButton },
};
</script>

<style lang="scss" scoped>
.right {
	height: 800px;
	width: 500px;
	background-color: white;
}
</style>
