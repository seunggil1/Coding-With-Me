<template>
	<div>시험 생성 form</div>
	<p>{{ classId }}</p>
</template>

<script>
export default {
	props: {
		classId: {
			type: String,
			// required: true,
		},
	},
	name: 'MakeExamPage',
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
