<template>
	<div class="right">
		<FindIDPWText></FindIDPWText>
		<IDInput></IDInput>
		<EmailInput></EmailInput>
		<FindButton></FindButton>
	</div>
</template>

<script>
import FindIDPWText from 'src/components/molecules/find/FindIDPWText.vue';
import IDInput from 'src/components/molecules/find/IDInput.vue';
import EmailInput from 'src/components/molecules/find/EmailInput.vue';
import FindButton from 'src/components/molecules/find/FindButton.vue';
export default {
	name: 'RightPWForm',
	setup() {
		return {};
	},
	components: { FindIDPWText, IDInput, EmailInput, FindButton },
};
</script>

<style lang="scss" scoped>
.right {
	height: 800px;
	width: 500px;
	background-color: white;
}
</style>
