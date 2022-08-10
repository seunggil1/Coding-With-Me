<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum">
		<q-btn
			@click="openstartLecture = true"
			class="q-mb-md hvr-grow"
			glossy
			rounded
			size="25px"
			push
			style="background: #ff5722; color: white; font-family: 'MICEGothic Bold'"
			>{{ className }}의 강의 시작하기</q-btn
		>
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
					<q-input class="brand" dense v-model="lectureName" autofocus />
				</q-card-section>

				<q-card-actions align="right" class="text-primary">
					<q-btn
						class="brand"
						flat
						label="만들기"
						@click="startLecture"
						v-close-popup
					/>
					<q-btn class="brand" flat label="취소" v-close-popup />
				</q-card-actions>
			</q-card>
		</q-dialog>
		<div class="box2 q-mb-md hvr-grow">
			<q-btn push>만들어져 있는 강의들 리스트(아직 연결 안 됨)</q-btn>
		</div>
		<div class="flex row">
			<div class="col-6">
				<div class="box q-px-lg q-py-lg q-mr-md hvr-grow">
					<div class="row">
						<div class="col-10">
							<p style="font-size: 26px; font-family: 'MICEGothic Bold'">
								{{ className }}의 학생
							</p>
							<q-chip
								class="hvr-grow"
								color="grey"
								text-color="white"
								icon="face"
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
								<q-icon style="float: right" name="edit" size="26px"></q-icon>
							</router-link>
						</div>
					</div>
				</div>
			</div>
			<div class="col-6">
				<div class="box q-px-lg q-py-lg q-ml-md hvr-grow">
					<div class="row">
						<div class="col-10">
							<p style="font-size: 26px; font-family: 'MICEGothic Bold'">
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
													style="max-width: 300px; color: #00adb5"
													id="testFile"
													color="#00adb5"
													label="시험 파일(PDF)"
													field-name="files"
													class="q-mt-lg"
												></q-uploader>
											</q-expansion-item>
										</q-item-label>
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
								<q-icon style="float: right" name="edit" size="26px"></q-icon>
							</router-link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';
import { api } from 'src/boot/axios.js';
import { teacherVideoStore } from 'src/stores/teacherVideo.store';

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

		const teacherVideo = teacherVideoStore();

		function startLecture() {
			if (lectureName.value == '' || lectureName.value == null) {
				alert('값을 입력해주세요');
				return;
			}
			let id = JSON.parse(localStorage.getItem('user')).id;
			teacherVideo.state.id = id; // 로그인 아이디
			let uid = parseInt(localStorage.getItem('userId'));
			teacherVideo.state.userId = uid;
			let name = JSON.parse(localStorage.getItem('info')).name;
			teacherVideo.state.myUserName = name;
			let classId = parseInt(localStorage.getItem('classId'));
			teacherVideo.state.classId = classId;
			teacherVideo.state.mySessionId = lectureName.value;
			lectureName.value = '';

			console.log('id', teacherVideo.state.id);
			console.log('uid', teacherVideo.state.userId);
			console.log('myUserName', teacherVideo.state.myUserName);
			console.log('classId', teacherVideo.state.classId);
			console.log('mySessionId', teacherVideo.state.mySessionId);

			teacherVideo.createSession().then(() => {
				router.push({ path: '/teacherlecture' }).catch(()=>{
					router.push({ path: '/teacherlecture' }).catch(()=>{
						router.push({ path: '/teacherlecture' });
					});
				});
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

		// 학생을 추가하기
		async function goAddStudent() {
			await router.push({ path: '/addStudent' });
		}

		return {
			goAddStudent,
			students,
			classId,
			className,
			openstartLecture,
			lectureName,
			startLecture,
			tests,
			files,
			itemRefs,
			skipUnwrap,
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
</style>
