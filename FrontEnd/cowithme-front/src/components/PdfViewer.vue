<template>
	<div>
		<vue-pdf-embed :source="source"> </vue-pdf-embed>
	</div>
</template>

<script>
import VuePdfEmbed from 'vue-pdf-embed';

// example
// <pdf-viewer source="https://cdn.filestackcontent.com/wcrjf9qPTCKXV3hMXDwK"></pdf-viewer>
export default {
	components: {
		VuePdfEmbed,
	},
	props: {
		source: String,
	},
	setup() {},
};
</script>

<style lang="scss" scoped></style>
