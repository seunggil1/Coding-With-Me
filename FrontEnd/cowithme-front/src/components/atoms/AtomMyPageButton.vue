<template>
	<div class="q-py-xs q-gutter-sm">
		<q-btn
			size="23px"
			class="btn-size"
			align="left"
			flat
			label="마이페이지"
			icon="account_circle"
			style="font-family: 'OTWelcomeBA'"
		></q-btn>
	</div>
</template>

<script>
export default {
	name: 'AtomButton',
	setup() {
		return {};
	},
};
</script>

<style>
.btn-size {
	width: 299px;
}
@font-face {
	font-family: 'OTWelcomeBA' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2') !important;
	font-weight: normal !important;
	font-style: normal !important;
}
</style>
