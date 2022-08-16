<template>
	<div class="main-container row justify-center items-center">
		<Vue3Lottie
			:animationData="LoadingJSON"
			:height="800"
			:width="800"
			:speed="1.5"
		/>
	</div>
</template>

<script>
import { Vue3Lottie } from 'vue3-lottie';
import 'vue3-lottie/dist/style.css';
import LoadingJSON from 'src/assets/loading/loading.json';

export default {
	components: {
		Vue3Lottie,
	},
	setup() {
		return { LoadingJSON };
	},
};
</script>

<style scoped>
.main-container {
	height: 100vh !important;
	width: 100vw !important;
	/* background-color: #303841 !important; */
}
</style>
