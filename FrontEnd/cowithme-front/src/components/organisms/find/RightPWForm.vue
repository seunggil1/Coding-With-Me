<template>
	<div class="right">
		<div class="container">
			<FindIDPWText></FindIDPWText>
			<div class="form-container">
				<IDInput class="component"></IDInput>
				<EmailInput class="component"></EmailInput>
			</div>
			<FindButton class="find"></FindButton>
		</div>
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
	height: 800px !important;
	width: 500px !important;
	background-color: white !important;
}

.container {
	margin-top: 100px !important;
	margin-left: 50px !important;
}

.form-container {
	margin-top: 50px !important;
	margin-right: 100px !important;
}

.component {
	margin-bottom: 20px !important;
}

.find {
	margin-top: 20px !important;
}
</style>
