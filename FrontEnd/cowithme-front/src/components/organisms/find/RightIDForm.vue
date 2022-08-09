<template>
	<div class="right">
		<div class="container">
			<FindIDPWText></FindIDPWText>
			<div class="form-container">
				<NameInput class="component"></NameInput>
				<BirthInput class="component"></BirthInput>
			</div>
			<FindButton class="find"></FindButton>
		</div>
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
