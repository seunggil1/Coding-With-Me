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
						<div class="row no-wrap">
							<div style="width: 11%" class="q-pa-sm">
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
								style="width: 11%"
								class="q-pr-sm q-py-sm"
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
				<!-- teacherVideo.subCamsOpen && teacherVideo.state.session -->
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
								<div class="q-px-sm q-pt-sm">
									<div class="flex" style="background: none">
										<user-video
											v-if="piniaCommonVideoData.openvidu.mainStreamManager"
											:stream-manager="
												piniaCommonVideoData.openvidu.mainStreamManager
											"
										/>
									</div>
								</div>
							</template>
							<template v-slot:after>
								<div
									style="font-family: 'Elice Digital Baeum', sans-serif"
									class="q-pt-sm q-pl-sm q-mr-md flex-height column"
								>
									<div class="col-1" style="background-color: #eeeeee">
										<!-- <div
											class="row"
											style="background-color: #eeeeee; width: 400px"
										> -->
										<!-- <div class="col-8 shadow"> -->
										<q-img
											style="width: 70px"
											class="test"
											src="src/assets/logo/logo.svg"
										/>
										<!-- <div class="text-h4 q-pl-sm q-pb-xs q-pt-xs">IDE</div> -->
										<!-- </div> -->
										<!-- <div class="col-2 q-pr-sm flex justify-end"> -->
										<!-- <div
											class="row justify-end flex"
											style="background-color: #eeeeee"
										> -->
										<q-btn-toggle
											v-model="enableSync"
											push
											no-caps
											unelevated
											color="blue-grey-8"
											toggle-color="orange-14"
											class="col q-pa-sm"
											:options="[
												{ label: '코드 보내기', value: 0 },
												{ label: '보내기 중단', value: 1 },
												{ label: '보낸 코드 삭제', value: 2 },
											]"
										/>
										<!-- </div> -->
										<!-- </div> -->
										<!-- <div class="col-2"> -->
										<!-- <div
											class="row justify-end flex"
											style="background-color: #eeeeee"
										> -->

										<q-btn
											style="
												width: 130px;
												max-width: 130px;
												min-width: 130px;
                        						max-height: 90%
												font-size: 18px;
												float: right;
											"
											class="col q-pa-sm q-mt-md q-mr-md"
											color="secondary"
											push
											label="코드 실행하기"
											@click="runCode"
											:loading="isRunning"
										/>

										<q-select
											style="
												width: 130px;
												max-width: 130px;
												min-width: 130px;
                        						max-height: 90%
												font-size: 18px;
												float: right;
											"
											class="col q-pa-sm"
											v-model="selectedLanguage"
											:options="languageList"
											label="에디터 언어"
											color="teal"
										/>

										<!-- </div> -->
										<!-- </div> -->
										<!-- </div> -->
									</div>
									<div class="col-7">
										<web-editor
											ref="teacherIde"
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
							style="font-family: 'Elice Digital Baeum', sans-serif"
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
							label="시험 시작"
							@click="showExamDialog = true"
						/>
						<q-btn
							class="leaveBtn q-ml-md"
							rounded
							push
							color="negative"
							icon="logout"
							label="강의 종료"
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

	<!-- 시험 시작 누를때 뜨는 팝업 -->
	<q-dialog
		style="font-family: 'Elice Digital Baeum', sans-serif"
		v-model="showExamDialog"
	>
		<q-card>
			<q-card-section class="row items-center q-pb-none">
				<div
					style="font-family: 'Elice Digital Baeum', sans-serif"
					class="text-h6"
				>
					시험 선택
				</div>
				<q-space />
				<q-btn icon="close" flat round dense v-close-popup />
			</q-card-section>

			<q-card-section>
				<q-select
					filled
					color="secondary"
					style="font-family: 'Elice Digital Baeum', sans-serif"
					v-model="piniaCommonExamData.testName"
					:options="piniaTeacherVideoData.testNameList"
					label="시험 선택"
				/>
				<q-input
					filled
					color="secondary"
					style="font-family: 'Elice Digital Baeum', sans-serif"
					v-model="timeWithSeconds"
					mask="fulltime"
					:rules="['fulltime']"
				>
					<template v-slot:append>
						<q-icon name="access_time" class="cursor-pointer" />
					</template>
				</q-input>
			</q-card-section>

			<q-card-section>
				<div class="row items-center justify-end">
					<q-btn color="secondary" push label="시험 시작" @click="startExam" />
				</div>
			</q-card-section>
		</q-card>
	</q-dialog>
</template>

<script>
import {
	onMounted,
	onBeforeUnmount,
	ref,
	watch,
	computed,
	reactive,
} from 'vue';
import { useRouter } from 'vue-router';
import { teacherVideoData } from 'src/stores/Video/teacher.js';
import { commonVideoData } from 'src/stores/Video/common.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
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
		const piniaTeacherVideoData = teacherVideoData();
		const piniaCommonExamData = commonExamData();

		let showSubScribers = ref(true);
		let rightDrawerOpen = ref(false);

		// IDE
		const teacherIde = ref(undefined);
		const splitterModel = ref(40);
		watch(splitterModel, () => {
			teacherIde.value.updateEditor();
		});
		watch(rightDrawerOpen, () => {
			teacherIde.value.updateEditor();
		});

		const selectedLanguage = ref('java');
		const selectedLanguageIdx = computed(() => {
			return compileLang.findIndex(val => val.name === selectedLanguage.value);
		});

		watch(selectedLanguageIdx, () => {
			teacherIde.value.updateCode(compileLang[selectedLanguageIdx.value].code);
		});
		const languageList = reactive(compileLang.map(val => val.name));

		let enableSync = ref(1);
		let repeater;
		watch(enableSync, val => {
			if (val == undefined) return;
			if (val === 0) {
				repeater = setInterval(() => {
					teacherIde.value.saveCode(true);
					piniaTeacherVideoData.sendCode();
				}, 1000);
			} else if (val === 1) {
				clearInterval(repeater);
				repeater = undefined;
			} else if (val == 2) {
				if (repeater) clearInterval(repeater);
				repeater = undefined;
				piniaTeacherVideoData.sendCode(true);
			} else {
				console.error('unknown mode');
			}
		});

		let isRunning = ref('false');
		let inputData = ref('');
		let outputData = ref('');

		const runCode = async () => {
			isRunning.value = true;
			teacherIde.value.saveCode(true);

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

		onMounted(async () => {
			await piniaCommonVideoData.joinSession();
			await piniaTeacherVideoData.addEventListener();
			await piniaTeacherVideoData.getTestList();

			window.addEventListener('resize', teacherIde.value.updateEditor);
		});

		onBeforeUnmount(() => {
			if (repeater) clearInterval(repeater);
			window.removeEventListener('resize', teacherIde.value.updateEditor);
		});

		// 시험 시작

		const showExamDialog = ref(false);
		const timeWithSeconds = ref('00:50:00');
		const startExam = () => {
			if (piniaCommonExamData.testName == '시험을 선택해주세요') return;
			let data = timeWithSeconds.value.split(':').map(s => {
				return parseInt(s);
			});
			const second = 3600 * data[0] + 60 * data[1] + 1 * data[2];
			for (let testInfo of piniaTeacherVideoData.testList) {
				if (piniaCommonExamData.testName == testInfo.testName) {
					piniaCommonExamData.testID = testInfo.testId;
					piniaCommonExamData.setTimeLimit(data[0], data[1], data[2]);
					piniaTeacherVideoData
						.sendTestInfo(testInfo.testId, testInfo.testName, second)
						.then(async () => {
							await piniaTeacherVideoData.sendRedirectInfo('exam');
							await router.push({ path: '/teacherexam' });
						});
					break;
				}
			}
		};

		async function leaveSession() {
			await piniaTeacherVideoData.sendRedirectInfo('leave');
			await piniaCommonVideoData.leaveSession();
			await router.push({
				path: `/classDetail/${piniaCommonVideoData.userInfo.classKey}`,
			});
		}

		return {
			timeWithSeconds,

			piniaCommonVideoData,
			piniaCommonExamData,
			piniaTeacherVideoData,

			teacherIde,

			showSubScribers,
			rightDrawerOpen,

			// IDE
			splitterModel,
			selectedLanguage,
			selectedLanguageIdx,
			languageList,

			enableSync,
			isRunning,
			inputData,
			outputData,
			runCode,

			// 시험
			showExamDialog,
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
	-webkit-filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
	filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
}
</style>
