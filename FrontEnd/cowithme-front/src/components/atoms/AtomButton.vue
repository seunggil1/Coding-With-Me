<template>
	<q-btn
		:loading="progress[1].loading"
		:percentage="progress[1].percentage"
		color="secondary"
		@click="startComputing(1)"
		icon="cloud_upload"
	></q-btn>
</template>

<script>
export default {
	name: 'AtomButton',
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
