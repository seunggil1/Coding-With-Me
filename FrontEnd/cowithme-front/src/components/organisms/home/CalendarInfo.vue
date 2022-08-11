<template>
	<div class="q-pa-md">
		<div class="q-gutter-md row items-start">
			<q-date class="calendar"></q-date>
		</div>
	</div>
</template>

<script>
export default {
	name: 'CalendarInfo',
	setup() {
		return {};
	},
};
</script>

<style>
.calendar {
	width: 500px !important;
	height: 385px !important;
}
</style>
