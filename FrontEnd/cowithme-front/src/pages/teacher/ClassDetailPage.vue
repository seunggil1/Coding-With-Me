<template>
	<div class="q-pa-md">
		<q-btn>{{ className }}의 강의 생성하기</q-btn>
		<!-- <VueLogo></VueLogo> -->
		<div class="box2 q-mb-md">
			<p>강의 목록</p>
			<q-btn>강의 시작(WebRTC 토큰 설정되면)</q-btn>
		</div>
		<div class="flex row">
			<div class="box col-6">
				학생 목록 및 추가
				<q-btn @click="goAddStudent">학생 추가</q-btn>
			</div>
			<div class="q-ma-sm"></div>
			<div class="box col-6">
				시험 목록 및 생성
				<router-link :to="{ name: 'makeExam', params: { classId: classId } }">
					<q-btn>시험 생성</q-btn>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
// import VueLogo from 'src/assets/logo/logo2.svg';
import { useRouter } from 'vue-router';
// import { api } from 'src/boot/axios.js';
// import { onBeforeMount } from 'vue';
// import { ref } from 'vue';

export default {
	name: 'ClassDetailPage',
	// components: { VueLogo },
	props: {
		classId: {
			type: String,
			// required: true,
		},
		className: {
			type: String,
			// required: true,
		},
		userId: {
			type: String,
			// required: true,
		},
	},
	setup() {
		// const students = ref([]);
		const router = useRouter();

		// onBeforeMount(async () => {
		// 	const userId = props.userId;
		// 	const className = props.className;
		// 	try {
		// 		console.log(props.className);

		// 		const temp = await api.get(`/tutor/${userId}/classes/${className}`);
		// 		students.value.push(...temp.students);
		// 	} catch (error) {
		// 		console.log(error);
		// 	}
		// });
		// function () {
		// 	api

		// 		.get(`/tutor/${userId}/classes/${className}`)
		// 		.then(res => {
		// 			students.value = res.data.students;
		// 			console.log(students.value);
		// 		})
		// 		.catch(err => {
		// 			console.log(err);
		// 		});
		// },
		// // const userId = localStorage.getItem('userId');

		async function goAddStudent() {
			await router.push({ path: '/addStudent' });
		}

		return { goAddStudent };
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
