<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum">
		<div class="flex row justify-between">
			<q-btn
				@click="openstartLecture = true"
				class="q-mb-md hvr-grow"
				glossy
				rounded
				size="25px"
				push
				style="
					background: #ff5722;
					color: white;
					font-family: 'MICEGothic Bold';
				"
				>{{ className }} 강의 시작하기</q-btn
			>
			<div class="flex items-center">
				<q-btn
					@click="openEditClass = true"
					class="gt-xs"
					size="26px"
					flat
					color="grey"
					dense
					round
					icon="settings"
				>
				</q-btn>
			</div>
		</div>

		<q-dialog
			class="brand"
			style="font-family: 'Elice Digital Baeum'"
			v-model="openstartLecture"
			persistent
		>
			<q-card class="q-pa-md" style="min-width: 500px; min-height: 400px">
				<q-card-section>
					<div class="text-h6">강의명</div>
				</q-card-section>
				<q-card-section class="q-pt-none">
					<q-input color="secondary" dense v-model="lectureName" autofocus />
				</q-card-section>

				<q-card-actions align="right" class="text-primary">
					<q-btn
						class="brand"
						flat
						label="시작하기"
						@click="startLecture"
						v-close-popup
					/>
					<q-btn class="brand" flat label="취소" v-close-popup />
				</q-card-actions>
			</q-card>
		</q-dialog>
		<q-dialog
			style="font-family: 'Elice Digital Baeum'"
			v-model="openEditClass"
			persistent
		>
			<q-card class="q-pa-md" style="min-width: 500px; min-height: 400px">
				<q-card-section>
					<div class="text-h6">반 이름 수정하기</div>
				</q-card-section>
				<q-card-section class="q-pt-none">
					<q-input color="secondary" dense v-model="newclassName" autofocus />
				</q-card-section>
				<q-card-section>
					<div class="text-h6">반 정보 수정하기</div>
				</q-card-section>
				<q-card-section class="q-pt-none">
					<q-input
						color="secondary"
						dense
						v-model="classDescription"
						autofocus
					/>
				</q-card-section>

				<q-card-actions align="right" class="text-primary">
					<q-btn
						color="secondary"
						flat
						label="수정 완료"
						@click="editClassInfo"
						v-close-popup
					/>
					<q-btn class="brand" flat label="취소" v-close-popup />
				</q-card-actions>
			</q-card>
		</q-dialog>
		<div class="box2 q-mb-md hvr-grow">
			<q-carousel
				v-if="pagingLectures.length != 0"
				v-model="slide"
				transition-prev="slide-right"
				transition-next="slide-left"
				swipeable
				animated
				control-color="black"
				padding
				arrows
				height="300px"
				class="shadow-1 rounded-borders"
			>
				<q-carousel-slide
					v-for="(lectures, index) in pagingLectures"
					:key="index"
					:name="1 + index"
					class="row no-wrap items-center justify-center"
				>
					<q-card
						class="lecture-card q-mx-lg"
						v-for="lecture in lectures[0]"
						:key="lecture"
					>
						<q-card-section>
							<div class="text-h6">{{ lecture.conferenceName }}</div>
							<div class="text-subtitle2">{{ lecture.date }}</div>
						</q-card-section>
						<q-card-section> </q-card-section>
						<q-separator />
						<q-card-section>
							<div>종료된 강의입니다.</div>
						</q-card-section>
					</q-card>
				</q-carousel-slide>
			</q-carousel>
			<div v-else class="q-pa-lg">
				<p style="font-size: 34px">생성된 강의 이력이 없습니다</p>
			</div>
		</div>
		<div class="flex row">
			<div class="col-6">
				<div class="box q-px-lg q-py-lg q-mr-md hvr-grow">
					<div class="row">
						<div class="col-10">
							<p style="font-size: 18px; font-family: 'MICEGothic Bold'">
								{{ className }}의 학생
							</p>
							<q-chip
								class="hvr-grow"
								color="secondary"
								text-color="white"
								icon="face"
								removable
								v-for="student in students"
								:key="student.userId"
								:ref="skipUnwrap.itemRefs"
								v-model="skipUnwrap.itemRefs"
							>
								{{ student.name }}({{ student.nickname }})
							</q-chip>
						</div>
						<div class="col-2">
							<router-link
								:to="{
									name: 'addStudent',
									params: {
										classId: classId,
										className: className,
									},
								}"
								style="text-decoration: none; color: inherit"
							>
								<q-icon
									style="float: right"
									color="grey"
									name="edit"
									size="26px"
								></q-icon>
							</router-link>
						</div>
					</div>
				</div>
			</div>
			<div class="col-6">
				<div class="box q-px-lg q-py-lg q-ml-md hvr-grow">
					<div class="row">
						<div class="col-10">
							<p style="font-size: 18px; font-family: 'MICEGothic Bold'">
								{{ className }}의 시험
							</p>
							<q-list v-for="test in tests" :key="test.testId">
								<q-item>
									<q-item-section>
										<q-item-label>
											<q-expansion-item
												expand-separator
												icon="quiz"
												:caption="`${test.testId}번 시험`"
												:label="`${test.testName}`"
											>
												<q-uploader
													:url="`https://i7a304.p.ssafy.io/api/v1/files/upload/${test.testId}`"
													style="max-width: 300px"
													id="testFile"
													color="secondary"
													label="시험 파일(PDF)"
													field-name="files"
													class="q-mt-lg"
												></q-uploader>
											</q-expansion-item>
										</q-item-label>
									</q-item-section>
									<q-item-section side>
										<div class="text-grey-8">
											<q-btn
												@click="downloadPDF(test.testId, test.testName)"
												class="gt-xs"
												size="12px"
												flat
												color="dark"
												dense
												round
												icon="download"
											/>
											<router-link
												:to="{
													name: 'testDetail',
													params: {
														testId: test.testId,
														testName: test.testName,
													},
												}"
												style="text-decoration: none; color: inherit"
											>
												<q-btn
													class="gt-xs"
													size="12px"
													flat
													color="grey"
													dense
													round
													icon="settings"
												/>
											</router-link>
											<q-btn
												@click="deleteTest(test.testName)"
												class="gt-xs q-pb-sm"
												size="12px"
												flat
												color="negative"
												dense
												round
												icon="delete"
											/>
										</div>
									</q-item-section>
								</q-item>
								<q-separator spaced inset />
							</q-list>
						</div>
						<div class="col-2">
							<router-link
								:to="{
									name: 'makeExam',
									params: { classId: classId },
								}"
								style="text-decoration: none; color: inherit"
							>
								<q-icon
									style="float: right"
									color="grey"
									name="edit"
									size="26px"
								></q-icon>
							</router-link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import fileDownload from 'js-file-download';
import { useRouter } from 'vue-router';
import { api } from 'src/boot/axios.js';
import { commonVideoData } from 'src/stores/Video/common.js';
import { teacherVideoData } from 'src/stores/Video/teacher.js';
import { ref } from 'vue';

export default {
	name: 'ClassDetailPage',
	setup() {
		const itemRefs = ref([]);
		const skipUnwrap = { itemRefs };

		const files = ref(null);

		const students = ref([]);
		const tests = ref([]);
		const router = useRouter();
		const openstartLecture = ref(false);
		const lectureName = ref('');

		const piniaCommonVideoData = commonVideoData();
		const piniaTeacherVideoData = teacherVideoData();

		function startLecture() {
			if (lectureName.value == '' || lectureName.value == null) {
				alert('값을 입력해주세요');
				return;
			}

			piniaCommonVideoData.userInfo.userId = JSON.parse(
				localStorage.getItem('user'),
			).id;
			piniaCommonVideoData.userInfo.userKey = parseInt(
				localStorage.getItem('userId'),
			);
			piniaCommonVideoData.userInfo.userName = JSON.parse(
				localStorage.getItem('info'),
			).name;
			piniaCommonVideoData.userInfo.classKey = parseInt(
				localStorage.getItem('classId'),
			);
			piniaCommonVideoData.userInfo.conferenceName = lectureName.value;
			piniaCommonVideoData.userInfo.className = className;
			lectureName.value = '';

			piniaTeacherVideoData.createSession().then(async () => {
				await piniaCommonVideoData.getConferenceKey();
				await piniaCommonVideoData.getStudentList();
				await router.push({ path: '/teacherlecture' });
			});
		}

		// 해당 반의 학생 리스트를

		const className = localStorage.getItem('className');
		const classId = localStorage.getItem('classId');
		const userId = localStorage.getItem('userId');

		// 학생 목록 가져오기
		api
			.get(`/tutor/${userId}/classes/${className}`)
			.then(res => {
				students.value = res.data.students;
				console.log(res.data);
			})
			.catch(err => {
				console.log(err);
			});

		// 해당 반의 시험 목록 가져오기
		api
			.get(`/tests/${classId}`)
			.then(res => {
				tests.value = res.data.testList;
				// console.log(res.data);
			})
			.catch(err => {
				console.log(err);
			});
		// 시험 삭제
		function deleteTest(testName) {
			api.get(`tests/${classId}/${testName}`).then(res => {
				// console.log(testName);
				console.log(res.data);
				api
					.delete(`tests`, {
						data: {
							classId: classId,
							testName: testName,
							testQno: res.data.test.testQno,
							testcaseList: res.data.test.testcase.testcaseList,
						},
					})
					.then(res => {
						console.log(res);
						window.location.reload();
					})
					.catch(err => {
						console.log(err);
					});
			});
		}

		// 학생을 추가하기
		async function goAddStudent() {
			await router.push({ path: '/addStudent' });
		}

		// 페이지네이션 관련
		const totalPage = ref(0);
		const totalLecture = ref(0);
		const pagingLectures = ref([]);
		const lectures = ref([]);

		function getLectures() {
			api
				.get(`conferences/${classId}/not-active`)
				.then(res => {
					// console.log(res.data);
					lectures.value = res.data.conference;
					console.log('강의 목록 가져오기', lectures.value);
					totalLecture.value = lectures.value.length; // 전체 강의 수
					totalPage.value = Math.ceil(totalLecture.value / 5); // 나오는 페이지 수
					let max = totalLecture.value - 1;
					let min = max - 4;
					if (min < 0) {
						min = 0;
					}
					for (let i = 0; i < totalPage.value; i++) {
						let temp = [];
						temp.push(lectures.value.slice(min, max + 1).reverse());
						pagingLectures.value.push(temp);
						max -= 5;
						if (min - 5 < 0) {
							min = 0;
						} else {
							min -= 5;
						}
					}
				})
				.catch(err => {
					console.log('강의 목록 없거나 에러', err);
				});
		}
		getLectures();

		function downloadPDF(testId, testName) {
			api
				.get(`files/download/${testId}`, {
					responseType: 'blob', // Important
				})
				.then(res => {
					console.log(res);
					fileDownload(res.data, `${testName} 시험문제.pdf`);
				})
				.catch(err => {
					console.log(err);
				});
		}
		// 반 정보 수정
		const classDescription = ref('');
		const newclassName = ref('');
		const openEditClass = ref(false);
		function editClassInfo() {
			api
				.put(`tutor/classes`, {
					classDescription: classDescription.value,
					className: className,
					newclassName: newclassName.value,
					tutorId: userId,
				})
				.then(res => {
					localStorage.setItem('className', newclassName.value);
					console.log(res);
				})
				.catch(err => {
					console.log(err);
				})
				.finally(() => {
					window.location.reload();
				});
		}

		return {
			classDescription,
			newclassName,
			editClassInfo,
			totalPage,
			pagingLectures,
			totalLecture,
			lectures,
			goAddStudent,
			students,
			classId,
			className,
			openstartLecture,
			lectureName,
			startLecture,
			tests,
			deleteTest,
			files,
			itemRefs,
			skipUnwrap,
			slide: ref(1),
			downloadPDF,
			openEditClass,
		};
	},
};
</script>

<style scoped>
@import url('src/css/app.css');
.hvr-grow {
	/* vertical-align: middle; */
	transform: translateZ(0) !important;
	box-shadow: 0 0 1px rgba(0, 0, 0, 0) !important;
	backface-visibility: hidden !important;
	-moz-osx-font-smoothing: grayscale !important;
	transition-duration: 0.3s !important;
	transition-property: transform !important;
}

.hvr-grow:hover,
.hvr-grow:focus,
.hvr-grow:active {
	transform: scale(1.01) !important;
}

@font-face {
	font-family: 'MICEGothic Bold' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2') !important;
	font-weight: 700 !important;
	font-style: normal !important;
}
.box {
	min-height: 300px;
	/* width: 100%; */
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
.box2 {
	height: 300px !important;
	width: 100% !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
.brand {
	color: #00adb5 !important;
}
.lecture-card {
	width: 15% !important;
	height: 85% !important;
}
.box3 {
	width: 100% !important;
	height: 300px !important;
}
</style>
