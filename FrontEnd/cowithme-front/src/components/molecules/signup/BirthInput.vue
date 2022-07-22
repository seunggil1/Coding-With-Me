<template>
	<div>
		<AtomBasicInput label="Birth Date" type="date" stack-label></AtomBasicInput>
	</div>
</template>

<script>
import AtomBasicInput from 'src/components/atoms/AtomBasicInput.vue';
export default {
	name: 'BirthInput',
	components: { AtomBasicInput },
	setup() {
		return {};
	},
};
</script>

<style></style>
