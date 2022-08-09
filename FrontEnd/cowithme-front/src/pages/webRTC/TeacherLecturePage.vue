<template>
	<q-layout class="scroll" view="lHr lpr fFf">
		<q-drawer v-model="rightDrawerOpen" side="right" overlay bordered>
			<video-side-bar-vue :piniaData="teacherVideo"> </video-side-bar-vue>
		</q-drawer>

		<q-page-container style="font-family: 'OTWelcomeBA'">
			<div class="column main-container">
				<div
					v-if="showSubScribers && teacherVideo.state.session !== undefined"
					class="col-2 q-pl-sm"
				>
					<q-scroll-area style="height: 100%; max-width: 100vw">
						<div class="row no-wrap">
							<div style="width: 11%" class="q-pa-sm">
								<user-video
									:stream-manager="teacherVideo.state.publisher"
									@click="
										teacherVideo.updateMainVideoStreamManager(
											teacherVideo.state.publisher,
										)
									"
								/>
							</div>
							<div
								style="width: 11%"
								class="q-pa-sm"
								v-for="(sub, idx) in teacherVideo.state.subscribers"
								:key="idx"
							>
								<user-video
									:stream-manager="sub"
									@click="teacherVideo.updateMainVideoStreamManager(sub)"
								/>
							</div>
						</div>
					</q-scroll-area>
				</div>
				<!-- teacherVideo.subCamsOpen && teacherVideo.state.session -->
				<div
					:class="
						showSubScribers && teacherVideo.state.session ? 'col-10' : 'col-12'
					"
				>
					<div>
						<q-splitter v-model="splitterModel" style="height: 90vh">
							<template v-slot:before>
								<div class="q-pa-md flex-height">
									<div class="flex" style="background: none">
										<user-video
											:stream-manager="teacherVideo.state.mainStreamManager"
										/>
									</div>
								</div>
							</template>
							<template v-slot:after>
								<div class="q-pa-md flex-height column">
									<div class="col-1" style="background-color: #eeeeee">
										<div
											class="row flex-height"
											style="background-color: #eeeeee"
										>
											<div class="col-8 shadow">
												<q-img
													style="width: 70px"
													class="test"
													src="src/assets/logo/logo.svg"
												/>
												<!-- <div class="text-h4 q-pl-sm q-pb-xs q-pt-xs">IDE</div> -->
											</div>
											<div class="col-2">
												<div
													class="row justify-center flex"
													style="background-color: #eeeeee"
												>
													<q-btn-toggle
														v-model="enableSync"
														push
														no-caps
														unelevated
														color="blue-grey-8"
														toggle-color="orange-14"
														class="col q-pa-sm"
														:options="[
															{ label: 'Sync', value: true },
															{ label: 'Off', value: false },
														]"
													/>
													<!-- <q-btn-toggle
														v-model="enableSync"
														push
														rounded
														toggle-color="primary"
														class="col q-pa-sm"
														:options="[
															{ label: 'Sync', value: true },
															{ label: 'Off', value: false },
														]"
													/> -->
												</div>
											</div>
											<div class="col-2">
												<div
													class="row justify-center flex"
													style="background-color: #eeeeee"
												>
													<q-btn
														style="max-width: 150px; max-height: 60px font-size: 24px"
														class="col q-ma-sm q-pd-md"
														color="secondary"
														push
														label="Run"
														@click="runCode"
														:loading="isRunning"
													/>
												</div>
											</div>
										</div>
									</div>
									<div class="col-7">
										<web-editor
											ref="teacherIde"
											:code="teacherVideo.state.teacherCode"
											language="java"
											:readOnly="false"
										/>
									</div>
									<div class="col-4 row">
										<div class="col" style="background-color: white">
											<q-card flat bordered class="my-card flex-height">
												<q-card-section>
													<div class="text-h6">Input</div>
												</q-card-section>
												<q-separator inset />
												<q-card-section>
													<q-input
														type="textarea"
														float-label="Textarea"
														:max-height="40"
														:min-rows="1"
														v-model="inputData"
													/>
												</q-card-section>
											</q-card>
										</div>
										<div class="col" style="background-color: white">
											<q-card flat bordered class="my-card flex-height">
												<q-card-section>
													<div class="text-h6">Result</div>
												</q-card-section>
												<q-separator inset />
												<q-card-section>
													<q-input
														type="textarea"
														float-label="Textarea"
														:max-height="50"
														:min-rows="1"
														v-model="outputData"
														readonly
													/>
												</q-card-section>
											</q-card>
										</div>
									</div>
								</div>
							</template>
						</q-splitter>
					</div>
				</div>
			</div>
		</q-page-container>

		<q-footer elevated class="bg-grey-8 text-white">
			<q-toolbar>
				<div class="row full-width q-my-sm">
					<div class="col-1"></div>
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
							class="camBtn q-ml-md"
							rounded
							push
							:icon="teacherVideo.isVideo ? 'videocam' : 'videocam_off'"
							:label="teacherVideo.isVideo ? '카메라 끄기' : '카메라 켜기'"
							@click="
								teacherVideo.isVideo
									? teacherVideo.muteVideo()
									: teacherVideo.unmuteVideo()
							"
						/>

						<q-btn
							class="screenBtn q-ml-md"
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
							class="examBtn q-ml-md"
							rounded
							push
							icon="quiz"
							label="시험 시작"
							@click="startExam"
						/>
						<q-btn
							class="leaveBtn q-ml-md"
							rounded
							push
							color="red"
							icon="logout"
							label="나가기"
							@click="leaveSession"
						/>
					</div>
					<div class="col-1" align="end">
						<q-btn
							class="subCamBtn"
							flat
							round
							:icon="showSubScribers ? 'o_view_agenda' : 'view_agenda'"
							@click="showSubScribers = !showSubScribers"
						/>

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
	</q-layout>
</template>

<script>
import { onMounted, onBeforeUnmount, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { teacherVideoStore } from 'src/stores/teacherVideo.store.js';
import WebEditor from 'src/components/lectures/WebEditor.vue';
import UserVideo from 'src/components/lectures/UserVideo.vue';
import VideoSideBarVue from 'src/components/lectures/VideoSideBar.vue';
import axios from 'axios';
export default {
	components: {
		WebEditor,
		UserVideo,
		VideoSideBarVue,
	},
	setup() {
		const router = useRouter();
		const teacherVideo = teacherVideoStore();
		let showSubScribers = ref(true);
		let rightDrawerOpen = ref(false);

		// IDE
		const teacherIde = ref(undefined);
		const splitterModel = ref(50);
		watch(splitterModel, () => {
			teacherIde.value.updateEditor();
		});
		watch(rightDrawerOpen, () => {
			teacherIde.value.updateEditor();
		});

		let enableSync = ref(false);
		let repeater;
		watch(enableSync, val => {
			if (val == undefined) return;
			if (val === true) {
				repeater = setInterval(() => {
					teacherIde.value.saveCode();
					teacherVideo.sendCode();
				}, 1000);
			} else {
				clearInterval(repeater);
				repeater = undefined;
			}
		});

		let isRunning = ref('false');
		let inputData = ref('');
		let outputData = ref('');

		const runCode = async () => {
			isRunning.value = true;
			teacherIde.value.saveCode();

			const res = await axios.post(
				'https://i7a304.p.ssafy.io/api/v1/users/compile',
				{
					code: teacherVideo.state.teacherCode,
					lang: 'java',
					testcase: {
						input: inputData.value,
						output: '0',
					},
				},
			);
			if (res.data.status === 'success') {
				outputData.value = `Time : ${res.data.result[0].time}ms\n${res.data.result[0].output}`;
			} else {
				outputData.value = `서버 오류가 발생했습니다.`;
			}
			isRunning.value = false;
		};

		onMounted(() => {
			teacherVideo.joinSession();
			window.addEventListener('resize', teacherIde.value.updateEditor);
		});

		onBeforeUnmount(() => {
			if (repeater) clearInterval(repeater);
			window.removeEventListener('resize', teacherIde.value.updateEditor);
		});

		// 시험 시작
		const startExam = () => {
			router.push('/exam');
		};

		const leaveSession = () => {
			teacherVideo.leaveSession();
			router.push('/');
		};

		return {
			teacherVideo,
			teacherIde,

			showSubScribers,
			rightDrawerOpen,

			// IDE
			splitterModel,
			enableSync,
			isRunning,
			inputData,
			outputData,
			runCode,

			// 시험
			startExam,
			leaveSession,
		};
	},
};
</script>

<style scoped>
@font-face {
	font-family: 'OTWelcomeBA';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2');
	font-weight: normal;
	font-style: normal;
}
.main-container {
	height: 100vh;
	width: 100vw;
	background-color: #303841;
}

.flex {
	width: 100%;
	height: 100%;

	background-color: white;
}

.flex-width {
	width: 100%;
}

.flex-height {
	height: 100%;
}
.scroll::-webkit-scrollbar {
	display: none; /* for Chrome, Safari, and Opera */
}
.scroll {
	overflow: hidden;
}
.test {
	-webkit-filter: drop-shadow(0px 0px 1px rgba(0, 0, 0, 0.7));
	filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7));
}
</style>
