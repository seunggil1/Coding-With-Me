<template>
	<div class="q-py-xs q-gutter-sm">
		<q-btn
			size="23px"
			class="btn-size brand"
			align="left"
			flat
			label="성적 조회"
			icon="school"
		></q-btn>
	</div>
</template>

<script>
export default {
	name: 'AtomScoreIconButton',
	setup() {
		return {};
	},
};
</script>

<style>
.btn-size {
	width: 299px !important;
}
</style>
