<template>
	<div>
		<h2>{{ message }}</h2>
		<button @click="increment">클릭</button>
	</div>
</template>

<script>
import { ref } from 'vue';

export default {
	setup() {
		const message = ref('COding WITH ME!');
		const increment = () => {
			message.value = message.value + '!';
		};
		return { message, increment };
	},
};
</script>

<style lang="scss" scoped></style>
