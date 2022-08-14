<template>
	<!-- v-if="session" -->
	<q-layout class="scroll" view="lHr lpr fFf">
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
			<div class="column main-container">
				<div
					v-if="piniaCommonVideoData.openvidu.session !== undefined"
					class="col-2 q-pl-sm"
				>
					<q-scroll-area class="type2" style="height: 100%; max-width: 100vw">
						<div class="row no-wrap">
							<div style="width: 11%" class="q-pa-sm">
								<user-video :stream-manager="piniaCommonVideoData.openvidu.publisher" />
							</div>
							<div
								style="width: 11%"
								class="q-pa-sm"
								v-for="(sub, idx) in piniaCommonVideoData.openvidu.subscribers"
								:key="idx"
							>
								<user-video :stream-manager="sub" />
							</div>
						</div>
					</q-scroll-area>
				</div>

				<div :class="piniaCommonVideoData.openvidu.session ? 'col-10' : 'col-12'">
					<div>
						<q-splitter v-model="splitterModel" style="height: 90vh">
							<template v-slot:before>
								<div class="q-pa-md flex-height">
									<div class="flex" style="background: none">
										<pdf-viewer
											v-if="piniaCommonExamData.testID"
											:source="`${HOST}/files/download/${piniaCommonExamData.testID}`"
										/>
									</div>
								</div>
							</template>
							<template v-slot:after>
								<div class="q-pa-md q-mr-lg flex-height column">
									<div
										class="col-1"
										style="background-color: #eeeeee; border-radius: 5px"
									>
										<q-scroll-area style="height: 100%; max-width: 100vw">
											<div class="row no-wrap">
												<p
													style="
														font-family: 'Elice Digital Baeum', sans-serif;
														font-size: 1.5rem;
														color: #000000;
													"
													class="q-pa-md"
												>
													Test Cases
												</p>
												<div
													class="q-pl-sm q-pt-md"
													v-for="idx in piniaCommonExamData.testQuCnt"
													:key="idx"
												>
													<q-btn
														push
														color="secondary"
														:label="idx"
														:disable="selectedProblem == idx"
														@click="
															if (selectedProblem != idx) selectedProblem = idx;
														"
													/>
												</div>
											</div>
										</q-scroll-area>
									</div>
									<div class="col-11">
										<q-scroll-area style="height: 100%; max-width: 100vw">
											<div class="column no-wrap">
												<span
													v-if="
														piniaCommonExamData.testCase[selectedProblem - 1]
													"
												>
													<div
														class="col"
														v-for="(tc, idx) in piniaCommonExamData.testCase[
															selectedProblem - 1
														]"
														:key="idx"
													>
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
																			:min-rows="5"
																			disable
																			placeholder="7 3"
																		/>
																	</q-card-section>
																</q-card>
															</div>
															<div class="col">
																<q-card flat bordered class="my-card">
																	<q-card-section>
																		<div class="text-h6">Output</div>
																	</q-card-section>

																	<q-card-section class="q-pt-none">
																		<q-input
																			v-model="tc.output"
																			disable
																			type="textarea"
																			float-label="Textarea"
																			:max-height="50"
																			:min-rows="5"
																			placeholder="10"
																		/>
																	</q-card-section>
																</q-card>
															</div>
														</div>
													</div>
												</span>
											</div>
										</q-scroll-area>
									</div>
								</div>
							</template>
						</q-splitter>
					</div>
				</div>
			</div>
		</q-page-container>
		<!-- 메인캠 부분 end -->

		<!-- 하단바 부분 start -->
		<q-footer
			style="font-family: 'Elice Digital Baeum', sans-serif"
			elevated
			class="bg-grey-8 text-white"
		>
			<q-toolbar>
				<div class="q-my-sm row full-width">
					<div class="col-1"></div>
					<div class="col-10" align="center">
						<q-btn
							class="micBtn q-ml-md"
							rounded
							push
							:icon="piniaCommonVideoData.displayInfo.audioEnable ? 'mic' : 'mic_off'"
							:label="piniaCommonVideoData.displayInfo.audioEnable ? '음소거' : '음소거 해제'"
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
							:icon="piniaCommonVideoData.displayInfo.videoEnable ? 'videocam' : 'videocam_off'"
							:label="piniaCommonVideoData.displayInfo.videoEnable ? '카메라 끄기' : '카메라 켜기'"
							@click="
								piniaCommonVideoData.displayInfo.videoEnable
									? piniaCommonVideoData.muteVideo()
									: piniaCommonVideoData.unmuteVideo()
							"
						/>

						<q-btn
							class="examBtn q-ml-md"
							rounded
							push
							icon="quiz"
							label="시험 종료"
							@click="stopExam"
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
import { ref, onMounted } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
import { teacherVideoData } from 'src/stores/Video/teacher.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { teacherExamData } from 'src/stores/ExamProgress/teacher.js';
import { useRouter } from 'vue-router';
// import { api } from 'src/boot/axios.js';

import UserVideo from 'src/components/lectures/UserVideo.vue';
import TeacherVideoSideBar from 'src/components/lectures/exam/TeacherSideBar.vue';
import PdfViewer from 'src/components/PdfViewer.vue';

export default {
	components: {
		UserVideo,
		TeacherVideoSideBar,
		PdfViewer,
	},
	props: {},

	setup() {
		const HOST = 'https://i7a304.p.ssafy.io/api/v1';
		const splitterModel = ref(50);
		const rightDrawerOpen = ref(true);
		const router = useRouter();

		const piniaCommonVideoData = commonVideoData();

		const piniaTeacherVideoData = teacherVideoData();
		const piniaCommonExamData = commonExamData();
		const piniaTeacherExamData = teacherExamData();

		const selectedProblem = ref(1);

		
		// 세션 나가기
		const leaveSession = () => {
			piniaCommonVideoData.leaveSession().then(()=>{
				router.push({ path: '/home' });
			})
		};

		const stopExam = () => {};

		onMounted(async () => {
			piniaTeacherExamData.initSubmitStudentList();

			await piniaCommonExamData.getTestInfo(
				piniaCommonVideoData.userInfo.classKey,
				piniaCommonExamData.testName,
			);
		});

		return {
			HOST,
			splitterModel,
			rightDrawerOpen,

			router,

			piniaCommonVideoData,
			piniaTeacherVideoData,
			piniaCommonExamData,
			piniaTeacherExamData,

			selectedProblem,

			leaveSession,
			stopExam,
		};
	},
};
</script>

<style scoped>
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
/* 스크롤바 설정*/
.type2::-webkit-scrollbar {
	width: 6px;
}

/* 스크롤바 막대 설정*/
.type2::-webkit-scrollbar-thumb {
	height: 17%;
	background-color: rgba(33, 133, 133, 1);
	border-radius: 10px;
}

/* 스크롤바 뒷 배경 설정*/
.type2::-webkit-scrollbar-track {
	background-color: rgba(33, 133, 133, 0.33);
}
</style>
