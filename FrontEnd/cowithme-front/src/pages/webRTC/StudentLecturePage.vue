<template>
	<q-layout class="scroll" view="lHr lpr fFf">
		<q-drawer v-model="rightDrawerOpen" side="right" overlay bordered>
			<video-side-bar> </video-side-bar>
		</q-drawer>

		<q-page-container style="font-family: 'OTWelcomeBA'">
			<div class="column main-container">
				<div
					v-if="
						showSubScribers &&
						piniaCommonVideoData.openvidu.session !== undefined
					"
					class="col-2"
				>
					<q-scroll-area style="height: 100%; max-width: 100vw">
						<div class="row no-wrap q-ml-sm">
							<div style="width: 12%" class="q-pa-sm">
								<user-video
									:stream-manager="piniaCommonVideoData.openvidu.publisher"
									@click="
										piniaCommonVideoData.updateMainVideoStreamManager(
											piniaCommonVideoData.openvidu.publisher,
										)
									"
								/>
							</div>
							<div
								style="width: 12%"
								class="q-pa-sm"
								v-for="(sub, idx) in piniaCommonVideoData.openvidu.subscribers"
								:key="idx"
							>
								<user-video
									:stream-manager="sub"
									@click="
										piniaCommonVideoData.updateMainVideoStreamManager(sub)
									"
								/>
							</div>
						</div>
					</q-scroll-area>
				</div>
				<div
					:class="
						showSubScribers && piniaCommonVideoData.openvidu.session
							? 'col-10'
							: 'col-12'
					"
				>
					<div>
						<q-splitter v-model="splitterModel" style="height: 90vh">
							<template v-slot:before>
								<div
									class="q-px-sm q-pt-md flex-height"
									style="background-color: #303841"
								>
									<!-- 모드 1 : 왼쪽은 강사 얼굴, 오른쪽은 학생 IDE -->
									<span v-if="mode == 1">
										<div class="flex" style="background-color: #303841">
											<user-video
												style="background-color: #303841"
												:stream-manager="
													piniaCommonVideoData.openvidu.mainStreamManager
												"
											/>
										</div>
									</span>

									<!-- 모드 2 : 왼쪽은 강사 IDE, 오른쪽은 학생 IDE  -->
									<span v-if="mode == 2">
										<div class="q-px-sm flex-height column">
											<div
												class="col-1"
												style="
													background-color: #eeeeee;
													font-family: 'Elice Digital Baeum', sans-serif;
												"
											>
												<p class="q-pa-md" style="font-size: 26px">강사 IDE</p>
											</div>
											<div class="col-10">
												<web-editor
													ref="teacherIde"
													:code="piniaStudentVideoData.teacherCode"
													language="java"
													:readOnly="true"
												/>
											</div>
										</div>
									</span>
								</div>
							</template>
							<template v-slot:after>
								<div
									class="q-pt-md q-pl-sm q-mr-md flex-height column"
									style="font-family: 'Elice Digital Baeum', sans-serif"
								>
									<div class="col-1" style="background-color: #eeeeee">
										<div
											class="row flex-height"
											style="background-color: #eeeeee"
										>
											<div class="col-8">
												<q-img
													style="width: 70px"
													class="test"
													src="src/assets/logo/logo.svg"
												/>
											</div>
											<div class="col-2">
												<!-- <div
													class="row justify-center flex"
													style="background-color: #eeeeee"
												> -->
												<q-select
													style="
														width: 130px;
														max-width: 130px;
														min-width: 130px;
														max-height: 90%
														font-size: 18px;
														float: right;
													"
													class="q-pa-sm"
													v-model="selectedLanguage"
													:options="languageList"
													label="에디터 언어"
													color="teal"
												/>
												<!-- </div> -->
											</div>
											<div class="col-2">
												<q-btn
													style="
														width: 130px;
														max-width: 130px;
														min-width: 130px;
														max-height: 90%
														font-size: 18px;
														float: right;
													"
													class="q-pa-sm q-mt-md q-mr-md"
													color="secondary"
													push
													label="코드 실행하기"
													@click="runCode"
													:loading="isRunning"
												/>
											</div>
										</div>
									</div>
									<div class="col-7">
										<web-editor
											ref="studentIde"
											:code="piniaCommonVideoData.displayInfo.code"
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

		<q-footer
			style="font-family: 'Elice Digital Baeum', sans-serif"
			elevated
			class="bg-grey-8 text-white"
		>
			<q-toolbar>
				<div class="row full-width q-my-sm">
					<div class="col-1"></div>
					<div class="col-10" align="center">
						<q-btn
							class="micBtn"
							rounded
							push
							:icon="
								piniaCommonVideoData.displayInfo.audioEnable ? 'mic' : 'mic_off'
							"
							:label="
								piniaCommonVideoData.displayInfo.audioEnable
									? '음소거'
									: '음소거 해제'
							"
							@click="
								piniaCommonVideoData.displayInfo.audioEnable
									? piniaCommonVideoData.muteAudio()
									: piniaCommonVideoData.unmuteAudio()
							"
						/>

						<q-btn
							class="camBtn q-ml-md"
							rounded
							push
							:icon="
								piniaCommonVideoData.displayInfo.videoEnable
									? 'videocam'
									: 'videocam_off'
							"
							:label="
								piniaCommonVideoData.displayInfo.videoEnable
									? '카메라 끄기'
									: '카메라 켜기'
							"
							@click="
								piniaCommonVideoData.displayInfo.videoEnable
									? piniaCommonVideoData.muteVideo()
									: piniaCommonVideoData.unmuteVideo()
							"
						/>

						<q-btn
							class="screenBtn q-ml-md"
							rounded
							push
							icon="screen_share"
							:label="
								piniaCommonVideoData.displayInfo.screenShareEnable
									? '화면공유 중지'
									: '화면공유'
							"
							@click="
								piniaCommonVideoData.displayInfo.screenShareEnable
									? piniaCommonVideoData.stopScreenShare()
									: piniaCommonVideoData.startScreenShare()
							"
						/>

						<q-btn
							class="examBtn q-ml-md"
							rounded
							push
							icon="quiz"
							label="시험 입장"
							v-if="piniaStudentVideoData.enableTest"
							@click="startExam"
						/>
						<q-btn
							class="examBtn q-ml-md"
							rounded
							push
							icon="quiz"
							label="시험 입장"
							v-if="!piniaStudentVideoData.enableTest"
							disable
							@click="startExam"
						/>

						<q-btn
							class="leaveBtn q-ml-md"
							rounded
							push
							color="negative"
							icon="logout"
							label="나가기"
							@click="leaveSession"
						/>
					</div>
					<div class="col-1" align="end">
						<q-btn
							class="modeBtn"
							flat
							round
							icon="swap_horiz"
							@click="mode == 1 ? (mode = 2) : (mode = 1)"
						>
						</q-btn>

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
import { useRouter } from 'vue-router';
import { onMounted, onBeforeUnmount, ref, watch, computed, reactive } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
import { studentVideoData } from 'src/stores/Video/student.js';
import { compileLang } from 'src/components/lectures/WebEditorAsset';
import WebEditor from 'src/components/lectures/WebEditor.vue';
import UserVideo from 'src/components/lectures/UserVideo.vue';
import VideoSideBar from 'src/components/lectures/VideoSideBar.vue';
import axios from 'axios';
export default {
	components: {
		WebEditor,
		UserVideo,
		VideoSideBar,
	},
	setup() {
		const router = useRouter();
		const piniaCommonVideoData = commonVideoData();
		const piniaStudentVideoData = studentVideoData();

		let showSubScribers = ref(true);
		let rightDrawerOpen = ref(false);

		// IDE
		const teacherIde = ref(undefined);
		const studentIde = ref(undefined);

		const updateEditor = () => {
			if (teacherIde.value) teacherIde.value.updateEditor();
			studentIde.value.updateEditor();
		};

		const getTeacherCode = () => {
			if (teacherIde.value)
				teacherIde.value.updateCode(piniaStudentVideoData.teacherCode);
		};

		const selectedLanguage = ref('java');
		const selectedLanguageIdx = computed(() => {
			return compileLang.findIndex(val => val.name === selectedLanguage.value);
		});

		watch(selectedLanguageIdx, () => {
			studentIde.value.updateCode(compileLang[selectedLanguageIdx.value].code);
		});
		const languageList = reactive(compileLang.map(val => val.name));


		// 모드 변환
		const mode = ref(1);

		watch(mode, () => {
			if (teacherIde.value) teacherIde.value.saveCode(false);
			studentIde.value.saveCode(true);
		});

		const splitterModel = ref(50);
		watch(splitterModel, () => {
			updateEditor();
		});
		watch(rightDrawerOpen, () => {
			updateEditor();
		});

		let isRunning = ref('false');
		let inputData = ref('');
		let outputData = ref('');

		const runCode = async () => {
			isRunning.value = true;
			studentIde.value.saveCode(true);

			const res = await axios.post(
				'https://i7a304.p.ssafy.io/api/v1/users/compile',
				{
					code: piniaCommonVideoData.displayInfo.code,
					lang: compileLang.find(item => item.name == selectedLanguage.value)
						.language,
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

		let repeater = undefined;
		const redirectToExam = ref(false);
		onMounted(async () => {
			await piniaCommonVideoData.joinSession();
			await piniaStudentVideoData.addEventListener();
			window.addEventListener('resize', updateEditor);
			repeater = setInterval(getTeacherCode, 2000);
		});

		onBeforeUnmount(() => {
			window.removeEventListener('resize', updateEditor);
			if (repeater) {
				clearInterval(repeater);
				repeater = undefined;
			}
		});

		// 시험 시작
		const startExam = () => {
			piniaStudentVideoData.enableTest = false;
			redirectToExam.value = true;
			router.push({ path: '/studentexam' });
		};

		const leaveSession = async () => {
			piniaCommonVideoData.leaveSession();
			router.push({ path: '/home' });
		};

		return {
			router,
			piniaCommonVideoData,
			piniaStudentVideoData,
			showSubScribers,
			rightDrawerOpen,


			// IDE
			teacherIde,
			studentIde,
			updateEditor,
			getTeacherCode,
			selectedLanguage,
			selectedLanguageIdx,
			languageList,

			// 모드 변환
			mode,
			splitterModel,
			isRunning,
			inputData,
			outputData,
			runCode,
			repeater,

			// 시험
			startExam,
			leaveSession,
		};
	},
};
</script>

<style scoped>
@font-face {
	font-family: 'OTWelcomeBA' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2') !important;
	font-weight: normal !important;
	font-style: normal !important;
}
.main-container {
	height: 100vh !important;
	width: 100vw !important;
	background-color: #303841 !important;
}
.flex {
	width: 100% !important;
	height: 100% !important;
	background-color: white !important;
}
.flex-width {
	width: 100% !important;
}
.flex-height {
	height: 100% !important;
}
.scroll::-webkit-scrollbar {
	display: none !important; /* for Chrome, Safari, and Opera */
}
.scroll {
	overflow: hidden !important;
}
.test {
	-webkit-filter: drop-shadow(0px 0px 1px rgba(0, 0, 0, 0.7)) !important;
	filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
}
</style>
