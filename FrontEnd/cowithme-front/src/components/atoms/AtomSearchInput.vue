<template>
	<q-input rounded outlined color="brand" type="search">
		<template v-slot:append>
			<q-icon name="search"></q-icon>
		</template>
	</q-input>
</template>

<script>
export default {
	setup() {
		return {};
	},
};
</script>

<style>
.text-brand {
	color: #00adb5 !important;
}
</style>
