<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum">
		<q-btn class="q-mb-md" push style="background: #ff5722"
			>{{ className }}의 강의 만들기</q-btn
		>
		<div class="box2 q-mb-md">
			<q-btn push>만들어져 있는 강의들 리스트(아직 연결 안 됨)</q-btn>
		</div>
		<div class="flex row">
			<div class="box q-pa-md col-5">
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
			<div class=""></div>
			<div class="box col-5 q-pa-md">
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
		return { goAddStudent, students, classId, className, tests };
	},
};
</script>

<style scoped>
.box {
	min-height: 300px;
	/* width: 100%; */
	background-color: #eeeeee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
.box2 {
	height: 300px;
	width: 100%;
	background-color: #eeeeee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
