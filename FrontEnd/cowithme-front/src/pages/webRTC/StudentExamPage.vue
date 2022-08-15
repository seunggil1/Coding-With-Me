<template>
	<q-layout
		class="scroll"
		style="font-family: 'Elice Digital Baeum'"
		view="lHr lpr fFf"
	>
		<q-drawer
			:width="600"
			v-model="rightDrawerOpen"
			side="right"
			bordered
			show-if-above
		>
			<student-side-bar></student-side-bar>
		</q-drawer>

		<q-page-container>
			<div class="column main-container">
				<q-splitter
					v-model="splitterModel"
					style="height: 92vh; overflow-y: hidden"
				>
					<template v-slot:before>
						<div class="q-pa-md flex-height">
							<div class="column flex-height">
								<div class="col-1">
									<q-card
										class="my-card bg-secondary text-white"
										style="height: 70px"
									>
										<q-card-section style="width: 900px; height: 70px">
											<div style="height: 100%">
												<span
													class="text-h6 q-ml-sm"
													style="font-family: 'Elice Digital Baeum'"
												>
													시험 문제
												</span>
												<q-btn class="q-mb-md" @click="zoomOut">
													<span style="font-size: "> - </span>
												</q-btn>
												<q-btn class="q-mb-md" @click="zoomIn">
													<span class=""> + </span>
												</q-btn>
											</div>
										</q-card-section>
									</q-card>
								</div>
								<div class="col-11">
									<pdf-viewer
										ref="examPaper"
										v-if="piniaCommonExamData.testID"
										:source="`${HOST}/files/download/${piniaCommonExamData.testID}`"
									/>
								</div>
							</div>
						</div>
					</template>
					<template v-slot:after>
						<div class="q-pa-md flex-height column">
							<div class="col-2" style="background-color: #eeeeee">
								<div class="row" style="background-color: #eeeeee">
									<div class="col-2">
										<div class="column q-px-md q-pt-lg">
											<q-select
												v-model="selectedLanguage"
												:options="languageList"
												label="에디터 언어"
												color="teal"
											/>
										</div>
									</div>
									<div class="col-7">
										<q-card flat bordered class="my-card full width">
											<div
												class="q-mx-lg q-mt-md"
												style="font-size: 18px; font-weight: bold"
											>
												<p>문제 번호</p>
											</div>

											<q-card-section>
												<div style="background-color: #eeeeee">
													<q-scroll-area style="height: 50px; max-width: 100vw">
														<div class="row no-wrap">
															<div
																style="height: 30px"
																class="q-px-sm"
																v-for="idx in piniaCommonExamData.testQuCnt"
																:key="idx"
															>
																<q-btn
																	color="primary"
																	v-if="selectedProblem != idx"
																	:label="idx"
																	@click="
																		if (selectedProblem != idx)
																			selectedProblem = idx;
																	"
																/>
																<q-btn
																	color="primary"
																	v-if="selectedProblem == idx"
																	:label="idx"
																	disable
																/>
															</div>
														</div>
													</q-scroll-area>
												</div>
											</q-card-section>
										</q-card>
									</div>
									<div class="col-3">
										<q-card flat bordered class="my-card">
											<q-card-section>
												<q-btn
													style="max-width: 150px; max-height: 50px font-size: 24px"
													color="orange-9"
													push
													label="제출하기"
													@click="submitCode"
													:loading="isSubmitting"
												/>
											</q-card-section>
											<q-card-section>
												<q-btn
													style="font-family: 'Elice Digital Baeum'; max-width: 150px; max-height: 50px font-size: 24px"
													color="secondary"
													push
													label="코드 실행하기"
													@click="runCode"
													:loading="isRunning"
												/>
											</q-card-section>
										</q-card>
									</div>
								</div>
							</div>
							<div class="col-6">
								<web-editor
									ref="studentIde"
									:code="'import java.util.*;\nimport java.io.*;\n\npublic class Main{\n    public static void main(String[] args) throws IOException {\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\n       \n        int a = Integer.parseInt(re.readLine());\n        int b = Integer.parseInt(re.readLine());\n\n        System.out.println(a+b);\n        re.close();\n    }\n}'"
									language="java"
									:readOnly="false"
								/>
							</div>
							<div class="col-4">
								<q-scroll-area style="height: 100%; max-width: 100vw">
									<div class="column no-wrap">
										<span
											v-if="piniaCommonExamData.testCase[selectedProblem - 1]"
										>
											<div
												class="col"
												v-for="(tc, idx) in piniaCommonExamData.testCase[
													selectedProblem - 1
												]"
												:key="idx"
											>
												<q-card flat bordered class="my-card">
													<q-card-section>
														<div class="row">
															<div class="col">
																<q-card flat bordered class="my-card">
																	<q-card-section>
																		<div class="text-h6">Input</div>
																	</q-card-section>

																	<q-card-section class="q-pt-none">
																		<q-input
																			v-model="tc.input"
																			type="textarea"
																			float-label="Textarea"
																			:max-height="50"
																			:min-rows="2"
																			readonly
																			placeholder="7 3"
																		/>
																	</q-card-section>
																</q-card>
															</div>
															<div class="col">
																<q-card flat bordered class="my-card">
																	<q-card-section>
																		<div class="text-h6">Answer</div>
																	</q-card-section>

																	<q-card-section class="q-pt-none">
																		<q-input
																			v-model="tc.output"
																			disable
																			type="textarea"
																			float-label="Textarea"
																			:max-height="50"
																			:min-rows="2"
																			placeholder="10"
																		/>
																	</q-card-section>
																</q-card>
															</div>
															<div class="col">
																<q-card flat bordered class="my-card">
																	<q-card-section>
																		<div class="text-h6">Your result</div>
																	</q-card-section>

																	<q-card-section class="q-pt-none">
																		<q-input
																			v-model="
																				resultList[selectedProblem - 1][idx]
																			"
																			type="textarea"
																			float-label="Textarea"
																			:max-height="50"
																			:min-rows="2"
																			readonly
																			placeholder=""
																		/>
																	</q-card-section>
																</q-card>
															</div>
														</div>
													</q-card-section>
												</q-card>
											</div>
										</span>
									</div>
								</q-scroll-area>
							</div>
						</div>
					</template>
				</q-splitter>
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
							v-if="!piniaCommonVideoData.displayInfo.screenShareEnable"
							class="screenBtn q-ml-md"
							rounded
							push
							icon="screen_share"
							label="화면공유"
							@click="piniaCommonVideoData.startScreenShare()"
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
import {
	onMounted,
	// onBeforeUnmount,
	ref,
	watch,
	onUnmounted,
	reactive,
	computed,
} from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
import { studentVideoData } from 'src/stores/Video/student.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { studentExamData } from 'src/stores/ExamProgress/student.js';
import WebEditor from 'src/components/lectures/WebEditor.vue';
import StudentSideBar from 'src/components/lectures/exam/StudentSideBar.vue';
// import UserVideo from 'src/components/lectures/UserVideo.vue';
// import VideoSideBarVue from 'src/components/lectures/VideoSideBar.vue';
import PdfViewer from 'src/components/PdfViewer.vue';
import { compileLang } from 'src/components/lectures/WebEditorAsset';
import { api } from 'src/boot/axios.js';

export default {
	components: {
		WebEditor,
		// UserVideo,
		// VideoSideBarVue,
		PdfViewer,
		StudentSideBar,
	},
	setup() {
		const HOST = 'https://i7a304.p.ssafy.io/api/v1';
		// router
		const router = useRouter();
		// pinia
		const piniaCommonVideoData = commonVideoData();
		const piniaStudentVideoData = studentVideoData();
		const piniaCommonExamData = commonExamData();
		const piniaStudentExamData = studentExamData();

		// footer
		const rightDrawerOpen = ref(false);

		// layout
		const splitterModel = ref(47);
		const selectedProblem = ref(1);
		watch(
			[splitterModel, selectedProblem],
			(
				[newSplitterModel, newSelectedProblem],
				[oldSplitterModel, oldSelectedProblem],
			) => {
				if (newSplitterModel != oldSplitterModel) {
					studentIde.value.updateEditor();
				} else if (newSelectedProblem != oldSelectedProblem) {
					piniaStudentExamData.code[oldSelectedProblem - 1] =
						studentIde.value.getCode();
					studentIde.value.updateCode(
						piniaStudentExamData.code[newSelectedProblem - 1],
					);
				}
			},
		);

		// Pdf
		const examPaper = ref(undefined);

		const zoomIn = () => examPaper.value.zoomIn();
		const zoomOut = () => examPaper.value.zoomOut();

		// IDE
		const studentIde = ref(undefined);
		const isRunning = ref(false);
		const isSubmitting = ref(false);

		const selectedLanguage = ref('java');
		const selectedLanguageIdx = computed(() => {
			return compileLang.findIndex(val => val.name === selectedLanguage.value);
		});

		watch(selectedLanguageIdx, () => {
			studentIde.value.updateCode(compileLang[selectedLanguageIdx.value].code);
		});
		const languageList = reactive(compileLang.map(val => val.name));
		const resultList = ref([[]]);
		const runCode = async () => {
			isRunning.value = true;

			piniaStudentExamData.code[selectedProblem.value - 1] =
				studentIde.value.getCode();

			let request = {
				code: piniaStudentExamData.code[selectedProblem.value - 1],
				lang: compileLang.find(item => item.name == selectedLanguage.value)
					.language,
				qno: selectedProblem.value,
				testId: piniaCommonExamData.testID,
			};
			try {
				resultList.value[selectedProblem.value - 1] = [];
				for (let [index, item] of (
					await api.post('/tests/compile', request)
				).data.result.entries()) {
					resultList.value[selectedProblem.value - 1].push(
						`Time : ${item.time}ms\nCorrect : ${item.success}\n${item.output}`,
					);
				}
			} catch (error) {
				console.log(error);
			}
			isRunning.value = false;
		};
		const submitCode = async () => {
			piniaStudentExamData.code[selectedProblem.value - 1] =
				studentIde.value.getCode();
			isSubmitting.value = true;
			let request = {
				classId: piniaCommonVideoData.userInfo.classKey,
				lang: compileLang.find(item => item.name == selectedLanguage.value)
					.language,
				testName: piniaCommonExamData.testName,
				userId: piniaCommonVideoData.userInfo.userKey,
				answers: [],
			};

			for (let i = 1; i <= piniaCommonExamData.testQuCnt; i++) {
				request.answers.push({
					qno: i,
					code: piniaStudentExamData.code[i - 1],
				});
			}

			try {
				let response = await api.post('/records/tests', request);
			} catch (error) {}
			await piniaStudentExamData.submit();
			isSubmitting.value = false;
		};

		onMounted(() => {
			piniaCommonExamData
				.getTestInfo(
					piniaCommonVideoData.userInfo.classKey,
					piniaCommonExamData.testName,
				)
				.then(() => {
					piniaStudentExamData.initCode();
					resultList.value = Array.apply(
						null,
						Array(piniaCommonExamData.testQuCnt),
					).map(() => []);
				});

			window.addEventListener('resize', () => studentIde.value.updateEditor());
		});

		onUnmounted(() => {});

		const leaveSession = async () => {
			router.push({ path: '/' });
		};

		return {
			HOST,
			// router
			router,

			// pinia
			piniaCommonVideoData,
			piniaStudentVideoData,
			piniaCommonExamData,
			piniaStudentExamData,

			// footer
			rightDrawerOpen,

			// layout
			splitterModel,
			selectedProblem,

			// Pdf
			examPaper,
			zoomIn,
			zoomOut,

			// IDE
			studentIde,
			isRunning,
			isSubmitting,
			selectedLanguage,
			languageList,
			resultList,
			selectedLanguageIdx,
			runCode,
			submitCode,
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
.font-OT {
	font-family: 'OTWelcomeBA' !important;
	font-size: 24px;
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
