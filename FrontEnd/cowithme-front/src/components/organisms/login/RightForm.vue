<template>
	<div class="right">
		<LoginText></LoginText>
		<IDInput></IDInput>
		<PWInput></PWInput>
		<FindIDPW></FindIDPW>
		<LoginButton></LoginButton>
		<SelectRoleButton></SelectRoleButton>
	</div>
</template>

<script>
import LoginText from 'src/components/molecules/login/LoginText.vue';
import IDInput from 'src/components/molecules/login/IDInput.vue';
import PWInput from 'src/components/molecules/login/PWInput.vue';
import FindIDPW from 'src/components/molecules/login/FindIDPW.vue';
import LoginButton from 'src/components/molecules/login/LoginButton.vue';
import SelectRoleButton from 'src/components/molecules/login/SelectRoleButton.vue';

export default {
	name: 'RightForm',
	setup() {
		return {};
	},
	components: {
		LoginText,
		IDInput,
		PWInput,
		FindIDPW,
		LoginButton,
		SelectRoleButton,
	},
};
</script>

<style lang="scss" scoped>
.right {
	height: 800px;
	width: 500px;
	background-color: white;
}
</style>
