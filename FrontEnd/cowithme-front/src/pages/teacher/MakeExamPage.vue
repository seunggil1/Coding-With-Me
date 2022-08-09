<template>
	<div class="q-pa-lg q-gutter-md" style="font-family: 'Elice Digital Baeum'">
		<div class="flex row q-mt-lg">
			<div class="q-pa-md col" style="max-width: 400px">
				<h3 style="font-family: 'MICEGothic Bold'">시험 만들기</h3>
				<q-input
					rounded
					outlined
					class="q-mb-sm"
					v-model="examInfo.testName"
					label="시험 이름"
				></q-input>
				<q-input
					type="number"
					rounded
					outlined
					class="q-mb-sm"
					v-model.number="examInfo.testQno"
					label="문제 갯수"
				></q-input>

				<!-- <q-input
					@update:model-value="
						val => {
							file = val[0];
						}
					"
					rounded
					outlined
					type="file"
					hint="문제 파일 업로드(pdf)"
				></q-input> -->
			</div>
			<q-form @submit="onSubmit" class="col">
				<q-card flat bordered class="my-card">
					<q-card-section>
						<q-splitter v-model="splitterModel" style="height: 400px">
							<template v-slot:before>
								<q-tabs v-model="problemTab" vertical class="text-teal">
									<q-tab name="0" label="가이드" />
									<q-tab
										v-for="(item, index) in examInfo.testcaseList"
										:key="index"
										:name="item.qno.toString()"
										:label="item.qno.toString() + '번 문제'"
									/>
								</q-tabs>
							</template>

							<template v-slot:after>
								<q-tab-panels
									v-model="problemTab"
									animated
									swipeable
									vertical
									transition-prev="jump-up"
									transition-next="jump-up"
								>
									<q-tab-panel name="0">
										<div class="text-h4 q-mb-md">Test Cases</div>
										<p>
											왼쪽 탭은 문제 번호를 나타내고, 여기는 각 문제 마다 테스트
											케이스를 작성하는 공간입니다.
										</p>
										<p>
											1개 테스트 케이스마다 input, output을 각각 작성하시면
											되며, 여러 개의 테스트 케이스를 작성할 수 있습니다.
										</p>
										<p>
											채점할 때 각 학생이 작성한 코드의 출력결과가 output이랑
											일치하면 정답처리 됩니다.
										</p>
										<p>
											강사는 학생이 각 문제를 풀 때 입력 값에 맞는 답안을
											출력하는 코드를 작성해달라고 안내해주시면 됩니다.
										</p>
										<p>
											Java의 경우 main문이 public class Main안에 있어야 합니다.
										</p>
										<div class="row">
											<div class="col">
												<q-card flat bordered class="my-card">
													<q-card-section>
														<div class="text-h6">Input</div>
													</q-card-section>

													<q-card-section class="q-pt-none">
														<q-input
															type="textarea"
															float-label="Textarea"
															:max-height="80"
															:min-rows="7"
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
															disable
															type="textarea"
															float-label="Textarea"
															:max-height="80"
															:min-rows="7"
															placeholder="10"
														/>
													</q-card-section>
												</q-card>
											</div>
										</div>
									</q-tab-panel>

									<q-tab-panel
										v-for="(item, index) in examInfo.testcaseList"
										:key="index"
										:name="item.qno.toString()"
									>
										<p>
											<q-btn
												push
												color="primary"
												label="+ 추가"
												@click="addTestCase(problemTab * 1)"
											/>
										</p>
										<q-card
											flat
											bordered
											class="my-card"
											v-for="(eachTestCase, testCaseIndex) in item.testcase"
											:key="testCaseIndex"
										>
											<q-card-section>
												<div class="text-h6">
													{{ testCaseIndex + 1 }}번째 테스트 케이스
													<q-btn
														push
														color="brown-5"
														label="삭제"
														@click="
															deleteTestCase(problemTab * 1, testCaseIndex)
														"
													/>
												</div>
											</q-card-section>

											<q-card-section class="q-pt-none">
												<div class="row">
													<div class="col">
														<q-card flat bordered class="my-card">
															<q-card-section>
																<div class="text-h6">Input</div>
															</q-card-section>

															<q-card-section class="q-pt-none">
																<q-input
																	v-model="eachTestCase.input"
																	type="textarea"
																	float-label="Textarea"
																	:max-height="100"
																	:min-rows="7"
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
																	v-model="eachTestCase.output"
																	type="textarea"
																	float-label="Textarea"
																	:max-height="100"
																	:min-rows="7"
																/>
															</q-card-section>
														</q-card>
													</div>
												</div>
											</q-card-section>
										</q-card>
									</q-tab-panel>
								</q-tab-panels>
							</template>
						</q-splitter>
					</q-card-section>
				</q-card>

				<div>
					<q-btn
						push
						label="시험 저장하기"
						type="submit"
						class="q-mt-md"
						style="background: #00adb5; color: white; float: right"
					></q-btn>
				</div>
			</q-form>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';
import { reactive, ref, watch } from 'vue';
// import { useExamStore } from 'src/stores';
import { api } from 'src/boot/axios';
// import { api } from 'src/boot/axios';
export default {
	name: 'MakeExamPage',
	setup() {
		// pdf 제출 관련 메서드
		const files = ref(null);
		const router = useRouter();

		const classId = localStorage.getItem('classId');
		// 현재 보여줄 탭. 0은 가이드, 1부터 각각 문제 번호.
		let problemTab = ref('0');

		// 문제 번호| 테스트 케이스 나누는 간격 (드래그로 조절 가능)
		let splitterModel = ref(20);

		// 모든 정보가 여기 다 있다.
		let examInfo = reactive({
			testName: 'examName',
			testQno: 1, //문제 갯수
			testcaseList: [
				{
					qno: 1, //문제 번호
					testcase: [
						// 각 문제별 testCase
						{
							input: '5\n10',
							output: '15',
						},
						{
							input: '1\n3',
							output: '4',
						},
					],
				},
			],
		});

		// examInfo안의 데이터에 대해 반응하려면 이렇게 해야됨.
		watch([() => examInfo.testQno], () => {
			if (examInfo.testcaseList.length > examInfo.testQno) {
				examInfo.testcaseList.length = examInfo.testQno;
			} else {
				while (examInfo.testcaseList.length != examInfo.testQno) {
					examInfo.testcaseList.push({
						qno: examInfo.testcaseList.length + 1, //문제 번호
						testcase: [
							// 각 문제별 testCase
							{
								input: '',
								output: '',
							},
						],
					});
				}
			}
		});

		// 몇 번 문제에 testcase 추가 해주세요!.
		const addTestCase = testQno => {
			examInfo.testcaseList[testQno - 1].testcase.push({
				input: '',
				output: '',
			});
		};

		// 몇 번 문제에 몇 번째 testCase 삭제해주세요!
		const deleteTestCase = (testQno, testCaseNo) => {
			examInfo.testcaseList[testQno - 1].testcase.splice(testCaseNo, 1);
			// console.log(examInfo.testcaseList[testQno - 1].testcase);
		};

		// const onSubmit = async () => {
		// 	const examStore = useExamStore();
		// 	examInfo['classId'] = classId * 1;
		// 	var testId = await examStore.makeExam(examInfo);
		// 	var res = await examStore.uploadExam(testId, file.value);
		// 	console.log(res);
		// };
		// async function onSubmit() {
		// 	const examStore = useExamStore();
		// 	examInfo['classId'] = classId * 1;
		// 	const res = await examStore.makeExam(examInfo);
		// 	console.log(res);
		// 	// router.push({ path: '/classDetail/' + classId });
		// }

		// PDF 업로드
		examInfo['classId'] = classId * 1;
		console.log(files.value);
		function onSubmit() {
			api.post(`/tests`, examInfo).then(res => {
				console.log(res.data);
				var testId = res.data.testId;
				api
					.post(`/tests/${testId}/upload`, { files: files.value })
					.then(res => {
						console.log(res.data);
						router.push({ path: '/classDetail/' + classId });
					})
					.catch(err => {
						console.log(err);
					});
			});
		}

		return {
			splitterModel,
			problemTab,
			examInfo,

			addTestCase,
			deleteTestCase,
			onSubmit,
		};
	},
};
</script>

<style lang="scss" scoped>
@font-face {
	font-family: 'MICEGothic Bold';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2');
	font-weight: 700;
	font-style: normal;
}
</style>
