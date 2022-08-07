<template>
  <div class="layout-container" v-if="session">
		<div class="left-container">
			<div v-if="main">
				<SubVideos></SubVideos>
				<MainVideo></MainVideo>
			</div>
			<div v-else class="main-videos">
				<div class="pub" v-if="pub">
					<UserVideo :stream-manager="pub"></UserVideo>
				</div>
				<template v-if="subs">
					<div class="sub" v-for="sub in subs" :key="sub">
						<UserVideo :stream-manager="sub"></UserVideo>
					</div>
				</template>
			</div>
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
import UserVideo from 'src/components/lectures/UserVideo.vue';
import { useVideoStore } from 'src/stores/video.js'

export default {
  components: {
		SubVideos,
    MainVideo,
		VideoBottomBar,
		SideBar,
		UserVideo,
	},
  setup () {
    const video = useVideoStore(); // store 가져오기
		const session = video.state.session;
		const pub = video.state.publisher;
		const subs = video.state.subscribers;
		const main = video.state.mainStreamManager;
    return {
      video,
      pub,
      main,
			subs,
			session,
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
.main-videos {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100%;
}
</style>