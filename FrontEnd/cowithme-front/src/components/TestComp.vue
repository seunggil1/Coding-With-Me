<template>
	<smart-widget-grid :layout="layout">
		<!-- layout[$].i as slot name -->
		<template #0>
			<smart-widget simple>
				<div class="layout-center">
					<h3>Simple Widget Without Header</h3>
				</div>
			</smart-widget>
		</template>
		<template #1>
			<smart-widget title="Default Widget">
				<div class="layout-center">
					<h3>Default Widget With Header</h3>
				</div>
			</smart-widget>
		</template>
		<template #2>
			<smart-widget title="Full Screen" fullscreen>
				<div class="layout-center">
					<h3>Make any widget full screen</h3>
				</div>
			</smart-widget>
		</template>
	</smart-widget-grid>
</template>

<script setup>
import { SmartWidget, SmartWidgetGrid } from 'src/boot/smart.js';

const layout = [
	{ x: 0, y: 0, w: 4, h: 4, i: '0' },
	{ x: 4, y: 0, w: 4, h: 4, i: '1' },
	{ x: 8, y: 0, w: 4, h: 4, i: '2' },
];
</script>

<style lang="scss" scoped></style>
