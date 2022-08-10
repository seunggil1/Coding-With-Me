<template>
	<!-- v-if="session" -->
	<q-layout view="lHr lpr fFf">
		<!-- 오른쪽 참여자 + 채팅 부분 start -->
		<q-drawer
			:width="600"
			show-if-above
			v-model="rightDrawerOpen"
			side="right"
			elevated
		>
			<teacher-video-side-bar> </teacher-video-side-bar>
		</q-drawer>

		<!-- 오른쪽 참여자 + 채팅 부분 end -->

		<!-- 메인캠 부분 start -->
		<q-page-container>
			<div class="row main-container justify-center items-center">
				<div class="main-cam-box row justify-center items-center">
					<div v-if="pub">
						<UserVideo class="video" :stream-manager="pub"></UserVideo>
					</div>
					<template v-if="subs">
						<div v-for="sub in subs" :key="sub">
							<UserVideo class="video" :stream-manager="sub"></UserVideo>
						</div>
					</template>
				</div>
			</div>
		</q-page-container>
		<!-- 메인캠 부분 end -->

		<!-- 하단바 부분 start -->
		<q-footer elevated class="bg-grey-8 text-white">
			<q-toolbar>
				<div class="row full-width">
					<div class="col-1">
						<q-avatar>
							<img src="src/assets/logo/logo.svg" />
						</q-avatar>
					</div>
					<div class="col-10" align="center">
						<q-btn
							class="micBtn"
							rounded
							push
							:icon="teacherVideo.isAudio ? 'mic' : 'mic_off'"
							:label="teacherVideo.isAudio ? '음소거' : '음소거 해제'"
							@click="
								teacherVideo.isAudio
									? teacherVideo.muteAudio()
									: teacherVideo.unmuteAudio()
							"
						/>

						<q-btn
							class="camBtn"
							rounded
							push
							:icon="teacherVideo.isVideo ? 'videocam_off' : 'videocam'"
							:label="teacherVideo.isVideo ? '카메라 끄기' : '카메라 켜기'"
							@click="
								teacherVideo.isVideo
									? teacherVideo.muteVideo()
									: teacherVideo.unmuteVideo()
							"
						/>

						<q-btn
							class="screenBtn"
							rounded
							push
							icon="screen_share"
							:label="teacherVideo.isScreen ? '화면공유 중지' : '화면공유'"
							@click="
								teacherVideo.isScreen
									? teacherVideo.stopScreenShare()
									: teacherVideo.startScreenShare()
							"
						/>

						<q-btn
							class="examBtn"
							rounded
							push
							icon="quiz"
							label="시험 종료"
							@click="startExam"
						/>
						<q-btn
							class="leaveBtn"
							rounded
							push
							color="red"
							icon="logout"
							label="나가기"
							@click="leaveSession"
						/>
						<!-- <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        </q-popup-proxy> -->
					</div>
					<div class="col-1" align="end">
						<q-btn
							class="drawerBtn"
							flat
							@click="rightDrawerOpen = !rightDrawerOpen"
							round
							:icon="
								rightDrawerOpen
									? 'keyboard_double_arrow_right'
									: 'keyboard_double_arrow_left'
							"
						/>
					</div>
				</div>
			</q-toolbar>
		</q-footer>
		<!-- 하단바 부분 end -->
	</q-layout>
</template>

<script>
import { ref } from 'vue';
import { teacherVideoStore } from 'src/stores/teacherVideo.store.js';
import { useRouter } from 'vue-router';
import TeacherVideoSideBar from 'src/components/lectures/exam/TeacherSideBar.vue';

export default {
	components: {
		TeacherVideoSideBar,
	},
	props: {},

	setup(props) {
		const rightDrawerOpen = ref(true);
		const router = useRouter();
		const teacherVideo = teacherVideoStore(); // store 가져오기
		const session = teacherVideo.state.session;
		const pub = teacherVideo.state.publisher;
		const subs = teacherVideo.state.subscribers;
		const main = teacherVideo.state.mainStreamManager;

		// 우측 바 펼치기, 접기
		const clickRightDrawer = () => {
			teacherVideo.setRightDrawer();
		};
		// 마이크 켜기, 끄기
		const clickMic = () => {
			if (video.isAudio) {
				teacherVideo.muteAudio();
			} else {
				teacherVideo.unmuteAudio();
			}
		};
		// 카메라 켜기, 끄기
		const clickVideo = () => {
			if (teacherVideo.isVideo) {
				teacherVideo.muteVideo();
			} else {
				teacherVideo.unmuteVideo();
			}
		};
		// 화면 공유 켜기, 끄기
		const clickScreenShare = () => {
			if (teacherVideo.isScreen) {
				teacherVideo.stopScreenShare();
			} else {
				teacherVideo.startScreenShare();
			}
		};
		// 세션 나가기
		const leaveSession = () => {
			teacherVideo.leaveSession();
			router.push('/');
		};

		return {
			rightDrawerOpen,

			router,
			teacherVideo,
			session,
			pub,
			subs,
			main,

			clickRightDrawer,
			clickMic,
			clickVideo,
			clickScreenShare,
			leaveSession,
		};
	},
};
</script>

<style scoped>
div {
	box-sizing: border-box !important;
}
.parti-chat-box {
	height: 94.71vh !important;
	border: 1px solid black !important;
}
.drawer-container,
.main-container {
	height: 94.71vh !important;
}
.main-cam-box {
	width: 95% !important;
	height: 95% !important;
	border: 1px solid red !important;
}
.sub-cams {
	border: 1px solid red !important;
	height: 100% !important;
}
.sub-cam {
	height: 100% !important;
}
.micBtn,
.camBtn,
.screenBtn,
.leaveBtn,
.examBtn {
	margin-left: 20px !important;
}
.participant-box,
.chat-box {
	width: 95% !important;
	height: 95% !important;
}
.textarea {
	width: 100% !important;
	height: 100% !important;
	resize: none !important;
}
video {
	width: 250px !important;
}
</style>
