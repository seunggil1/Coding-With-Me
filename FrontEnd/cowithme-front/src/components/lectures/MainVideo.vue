<template>
	<div class="main-video">
		<UserVideo :streamManager="props.streamManager"></UserVideo>
	</div>
</template>

<script>
import UserVideo from 'src/components/lectures/UserVideo.vue';
export default {
	props: {
		streamManager: Object,
	},
	setup(props) {
		return { props };
	},
	components: { UserVideo },
};
</script>

<style>
.main-video {
	width: 100%;
	height: 70vh;
	border: 1px solid #eeeeee;
	display: flex;
	justify-content: center;
	align-items: center;
}
video {
	width: 100%;
	height: 100%;
}
</style>
