<template>
  <div class="layout-container">
		<div class="left-container">
			<SubVideos></SubVideos>
			<MainVideo v-show="main"></MainVideo>
			<VideoBottomBar></VideoBottomBar>
		</div>
		<div class="right-container">
			<SideBar></SideBar>
		</div>
	</div>
</template>

<script>
import SubVideos from 'src/components/lectures/SubVideos.vue';
import MainVideo from 'src/components/lectures/MainVideo.vue';
import VideoBottomBar from 'src/components/lectures/VideoBottomBar.vue';
import SideBar from 'src/components/lectures/SideBar.vue'
import { useVideoStore } from 'src/stores/video.js'

export default {
  components: {
		SubVideos,
    MainVideo,
		VideoBottomBar,
		SideBar,
	},
  setup () {
    const video = useVideoStore(); // store 가져오기
		const pub = video.state.publisher;
		const main = video.state.mainStreamManager;
    return {
      video,
      pub,
      main,
    }
  }
}
</script>

<style>
.layout-container {
	display: flex;
	height: 100vh;
	background-color: #303841;
}
.left-container {
	display: flex;
	flex-direction: column;
	width: 80%;
	/* border: 1px solid red; */
}
.right-container {
	display: flex;
	flex-direction: column;
	width: 20%;
	/* border: 1px solid red; */
}
</style>