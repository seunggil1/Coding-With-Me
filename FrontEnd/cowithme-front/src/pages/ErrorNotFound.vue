<template>
	<div
		class="design-404 fullscreen text-white text-center q-pa-md flex flex-center"
	>
		<div>
			<div class="design-404" style="font-size: 30vh">404</div>

			<div class="design-404 text-h2" style="opacity: 0.4">
				여긴 아무것도 없어요...
			</div>

			<q-btn
				class="design-404 q-mt-xl"
				color="white"
				text-color="white"
				unelevated
				to="/home"
				label="홈 화면으로"
				no-caps
			/>
		</div>
	</div>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
	name: 'ErrorNotFound',
});
</script>

<style scoped>
@font-face {
	font-family: 'OTWelcomeBA' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2') !important;
	font-weight: normal !important;
	font-style: normal !important;
}
.design-404 {
	background-color: #00adb5 !important;
	font-family: 'OTWelcomeBA' !important;
}
</style>
