<template>
	<div class="q-py-xs q-gutter-sm">
		<q-btn
			size="24px"
			class="btn-size"
			align="left"
			flat
			label="스터디 검색"
			icon="search"
			style="font-family: 'Elice Digital Baeum'"
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
	font-family: 'GmarketSansLight';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>
