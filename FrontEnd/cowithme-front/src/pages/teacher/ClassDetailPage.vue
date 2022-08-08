<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum">
		<q-btn
			@click="openMakeLecture = true"
			class="q-mb-md"
			push
			style="background: #ff5722"
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
				<div class="box q-px-lg q-mr-md">
					<h3>{{ className }}의 학생</h3>
					<div v-for="student in students" :key="student.userId">
						{{ student.name }}({{ student.nickname }})
					</div>
					<router-link
						:to="{
							name: 'addStudent',
							params: {
								classId: classId,
								className: className,
							},
						}"
					>
						<q-btn style="text-decoration: none; color: inherit" push>
							학생 추가
						</q-btn>
					</router-link>
				</div>
			</div>
			<div class="col-6">
				<div class="box q-px-lg q-ml-md">
					<h3>{{ className }}의 시험</h3>
					<div v-for="test in tests" :key="test.testId">
						{{ test.testId }}번 {{ test.testName }}
					</div>
					시험 목록 및 생성
					<router-link
						:to="{
							name: 'makeExam',
							params: { classId: classId },
						}"
						style="text-decoration: none; color: inherit"
					>
						<q-btn push>시험 생성</q-btn>
					</router-link>
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
	// props: {
	// 	classId: {
	// 		type: Number,
	// 	},
	// 	className: {
	// 		type: String,
	// 	},
	// 	userId: {
	// 		type: String,
	// 	},
	// 	clas: {
	// 		type: String,
	// 	},
	// },
	setup() {
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

		// 학생 목록 가져오기
		api
			.get(`/tutor/1/classes/${className}`)
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
		};
	},
};
</script>

<style scoped>
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
</style>
