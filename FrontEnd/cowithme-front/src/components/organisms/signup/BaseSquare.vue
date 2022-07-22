<template>
	<div class="container base row">
		<div class="col">
			<AtomLogo></AtomLogo>
		</div>
		<div class="col">
			<RegisterText></RegisterText>
			<NameInput></NameInput>
			<IDInput></IDInput>
			<IDChkButton></IDChkButton>
			<PWInput></PWInput>
			<PWConfirmInput></PWConfirmInput>
		</div>
		<div class="col">
			<EmailInput></EmailInput>
			<EmailChkButton @click="chkEmail"></EmailChkButton>
			<PhoneInput></PhoneInput>
			<NicknameInput></NicknameInput>
			<BirthInput></BirthInput>
		</div>
		<AtomRadio></AtomRadio>
		<RegisterButton></RegisterButton>
	</div>
</template>

<script>
import RegisterText from 'src/components/molecules/signup/RegisterText.vue';
import NameInput from 'src/components/molecules/signup/NameInput.vue';
import IDInput from 'src/components/molecules/signup/IDInput.vue';
import PWInput from 'src/components/molecules/signup/PWInput.vue';
import PWConfirmInput from 'src/components/molecules/signup/PWConfirmInput.vue';
import EmailInput from '../../molecules/signup/EmailInput.vue';
import PhoneInput from 'src/components/molecules/signup/PhoneInput.vue';
import NicknameInput from 'src/components/molecules/signup/NicknameInput.vue';
import BirthInput from '../../molecules/signup/BirthInput.vue';
import RegisterButton from 'src/components/molecules/signup/RegisterButton.vue';
import AtomRadio from 'src/components/atoms/AtomRadio.vue';
import IDChkButton from 'src/components/molecules/signup/IDChkButton.vue';
import EmailChkButton from 'src/components/molecules/signup/EmailChkButton.vue';
import AtomLogo from 'src/components/atoms/AtomLogo.vue';
import { useQuasar } from 'quasar';
export default {
	name: 'BaseSquare',
	components: {
		RegisterText,
		NameInput,
		IDInput,
		PWInput,
		PWConfirmInput,
		EmailInput,
		PhoneInput,
		NicknameInput,
		BirthInput,
		RegisterButton,
		AtomRadio,
		IDChkButton,
		EmailChkButton,
		AtomLogo,
	},
	setup() {
		const $q = useQuasar();
		function chkEmail() {
			$q.dialog({
				title: '이메일 인증',
				message: '인증번호를 입력해주세요',
				prompt: {
					model: '',
					type: 'text', // optional
				},
				cancel: true,
				persistent: true,
			});
		}
		return { chkEmail };
	},
};
</script>

<style lang="scss" scoped>
.container {
	height: 800px;
	width: 1200px;
	background-color: #303841;
	box-shadow: 0px 0px 20px #303841;
	border-radius: 20px;
	display: flex;
}
</style>
