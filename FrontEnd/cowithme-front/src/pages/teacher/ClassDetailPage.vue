<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum">
		<q-btn class="q-mb-md" push style="background: #ff5722"
			>{{ className }}의 강의 만들기</q-btn
		>
		<div class="box2 q-mb-md">
			<q-btn push>만들어져 있는 강의들 리스트(아직 연결 안 됨)</q-btn>
		</div>
		<div class="flex row">
			<div class="box col-6">
				<div v-for="student in students" :key="student.userId">
					{{ student.name }}({{ student.nickname }})
				</div>
				<router-link :to="{ name: 'addStudent', params: { classId: classId } }">
					<q-btn push>학생 추가</q-btn>
				</router-link>
			</div>
			<div class=""></div>
			<div class="box col-6">
				시험 목록 및 생성
				<router-link
					:to="{ name: 'makeExam', params: { classId: classId } }"
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

import { ref } from 'vue';
// import AtomBasic2Button from 'src/components/atoms/AtomBasic2Button.vue';

export default {
	name: 'ClassDetailPage',
	props: {
		classId: {
			type: String,
		},
		className: {
			type: String,
		},
		userId: {
			type: String,
		},
		clas: {
			type: String,
		},
	},
	setup(props) {
		const students = ref([]);
		const router = useRouter();
		localStorage.setItem('students', JSON.stringify(students.value));
		localStorage.setItem('className', JSON.stringify(props.className));
		localStorage.setItem('classId', JSON.stringify(props.classId));
		localStorage.setItem('userId', props.userId);
		// 해당 반의 학생 리스트를 불러옵니다.
		api
			.get(`/tutor/1/classes/${props.className}`)
			.then(res => {
				students.value = res.data.students;
				console.log(res.data);
			})
			.catch(err => {
				console.log(err);
			});
		console.log(students);
		async function goAddStudent() {
			await router.push({ path: '/addStudent' });
		}
		return { goAddStudent, students };
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
