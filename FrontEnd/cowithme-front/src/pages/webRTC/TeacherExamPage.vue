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
					v-if="teacherVideo.state.session !== undefined"
					class="col-2 q-pl-sm"
				>
					<q-scroll-area class="type2" style="height: 100%; max-width: 100vw">
						<div class="row no-wrap">
							<div style="width: 11%" class="q-pa-sm">
								<user-video :stream-manager="teacherVideo.state.publisher" />
							</div>
							<div
								style="width: 11%"
								class="q-pa-sm"
								v-for="(sub, idx) in teacherVideo.state.subscribers"
								:key="idx"
							>
								<user-video :stream-manager="sub" />
							</div>
						</div>
					</q-scroll-area>
				</div>
				<!-- teacherVideo.subCamsOpen && teacherVideo.state.session -->
				<div :class="teacherVideo.state.session ? 'col-10' : 'col-12'">
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
									<div class="col-1" style="background-color: #eeeeee">
										<q-scroll-area style="height: 100%; max-width: 100vw">
											<div class="row no-wrap">
												<div
													style="width: 6%"
													class="q-pa-sm"
													v-for="idx in piniaCommonExamData.testQuCnt"
													:key="idx"
												>
													<q-btn
														push
														color="teal"
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
														].testcase"
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
import { ref, onMounted } from 'vue';
import { teacherVideoStore } from 'src/stores/teacherVideo.store.js';
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
		const teacherVideo = teacherVideoStore(); // store 가져오기
		const piniaCommonExamData = commonExamData();
		const piniaTeacherExamData = teacherExamData();

		const selectedProblem = ref(1);
		// 세션 나가기
		const leaveSession = () => {
			teacherVideo.leaveSession();
			router.push('/');
		};

		const stopExam = () => {};

		onMounted(() => {
			teacherVideo.joinSession();
			piniaCommonExamData.getTestInfo(
				teacherVideo.state.classId,
				piniaCommonExamData.testName,
			);
		});

		return {
			HOST,
			splitterModel,
			rightDrawerOpen,

			router,
			teacherVideo,
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
