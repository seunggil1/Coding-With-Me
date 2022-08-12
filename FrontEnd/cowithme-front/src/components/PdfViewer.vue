<template>
	<div style="width : 100%">
		<vue-pdf-embed :source="source" :width="width"> </vue-pdf-embed>
	</div>
</template>

<script>
import VuePdfEmbed from 'vue-pdf-embed';
import { ref } from 'vue';
// example
// <pdf-viewer source="https://cdn.filestackcontent.com/wcrjf9qPTCKXV3hMXDwK"></pdf-viewer>
export default {
	components: {
		VuePdfEmbed,
	},
	props: {
		source: String,
	},
	setup() {
		const width = ref(900);
		const zoomIn = () => {
			if(width.value <= 1200)
				width.value += 50;
		};

		const zoomOut = () => {
			if(width.value > 50)
				width.value -= 50;
		}

		return {
			width,
			zoomIn,
			zoomOut,
		}
	},
};
</script>

<style lang="scss" scoped></style>
