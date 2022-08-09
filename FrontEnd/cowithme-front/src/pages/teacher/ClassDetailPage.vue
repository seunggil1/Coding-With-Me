<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum">
		<q-btn
			@click="openMakeLecture = true"
			class="q-mb-md"
			glossy
			rounded
			size="25px"
			push
			style="background: #ff5722; color: white; font-family: 'MICEGothic Bold'"
			>{{ className }}의 강의 만들기</q-btn
		>
		<q-dialog v-model="openMakeLecture" persistent>
			<q-card class="q-pa-md" style="min-width: 500px; min-height: 400px">
				<q-card-section>
					<div class="text-h6">강의명</div>
				</q-card-section>
				<q-card-section class="q-pt-none">
					<q-input dense v-model="lectureName" autofocus />
				</q-card-section>

				<q-card-actions align="right" class="text-primary">
					<q-btn flat label="만들기" @click="makeLecture" v-close-popup />
					<q-btn flat label="취소" v-close-popup />
				</q-card-actions>
			</q-card>
		</q-dialog>
		<div class="box2 q-mb-md">
			<q-btn push>만들어져 있는 강의들 리스트(아직 연결 안 됨)</q-btn>
		</div>
		<div class="flex row">
			<div class="col-6">
				<div class="box q-px-lg q-py-lg q-mr-md">
					<div class="row">
						<div class="col-10">
							<p style="font-size: 26px; font-family: 'MICEGothic Bold'">
								{{ className }}의 학생
							</p>
							<q-chip
								color="primary"
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
				<div class="box q-px-lg q-py-lg q-ml-md">
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
													:url="`http://i7a304.p.ssafy.io:8080/api/v1/files/upload/${test.testId}`"
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
// import { onMounted } from 'vue';/

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
		const openMakeLecture = ref(false);
		const lectureName = ref('');
		function makeLecture() {
			if (lectureName.value == '' || lectureName.value == null) {
				alert('값을 입력해주세요');
				return;
			}
			let id = JSON.parse(localStorage.getItem('user')).id;
			api
				.post('/conferences', {
					classId: parseInt(localStorage.getItem('classId')),
					conferenceName: lectureName.value,
					ownerId: id,
					thumbnailPath: null,
				})
				.then(res => {
					console.log(res);
					if (res.data.message === 'Success') {
						alert('강의 개설이 완료되었습니다');
					} else {
						alert('다시 시도해주세요');
					}
					lectureName.value = '';
					return;
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
			openMakeLecture,
			lectureName,
			makeLecture,
			tests,
			files,
			itemRefs,
			skipUnwrap,
		};
	},
};
</script>

<style scoped>
@font-face {
	font-family: 'MICEGothic Bold';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2');
	font-weight: 700;
	font-style: normal;
}
.box {
	min-height: 300px;
	/* width: 100%; */
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
.box2 {
	height: 300px;
	width: 100%;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
.brand {
	color: #00adb5 !important;
}
</style>
