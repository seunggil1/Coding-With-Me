<template>
	<div>
		<pdf-viewer source="https://cdn.filestackcontent.com/wcrjf9qPTCKXV3hMXDwK"></pdf-viewer>
	</div>
</template>

<script>
// import { SmartWidget, SmartWidgetGrid } from 'src/boot/smart.js';
import PdfViewer from './PdfViewer.vue';

export default {
	components: {
		PdfViewer
	},
};
const layout = [
	{ x: 0, y: 0, w: 4, h: 4, i: '0' },
	{ x: 4, y: 0, w: 4, h: 4, i: '1' },
	{ x: 8, y: 0, w: 4, h: 4, i: '2' },
];
</script>

<style lang="scss" scoped></style>
