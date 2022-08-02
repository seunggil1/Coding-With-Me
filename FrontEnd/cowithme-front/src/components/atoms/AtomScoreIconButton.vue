<template>
	<div class="q-py-xs q-gutter-sm">
		<q-btn
			size="23px"
			class="btn-size"
			align="left"
			flat
			label="성적 조회"
			icon="history_edu"
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
	font-family: 'OTWelcomeBA';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2');
	font-weight: normal;
	font-style: normal;
}
</style>
