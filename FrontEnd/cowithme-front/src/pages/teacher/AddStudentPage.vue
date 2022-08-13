<template>
	<div
		class="q-pa-lg"
		style="font-family: 'Elice Digital Baeum', sans-serif; max-width: 800px"
	>
		<div
			class="box q-ma-lg q-pa-md q-gutter-md"
			style="font-family: 'Elice Digital Baeum'"
		>
			<q-form @submit="goSearchStudent" class="q-gutter-md row">
				<q-input
					class="col-7"
					rounded
					outlined
					type="text"
					color="secondary"
					bg-color="white"
					v-model="name"
					label="학생 이름"
				/>
				<div class="col-3 flex items-center">
					<q-btn
						push
						label="검색하기"
						type="submit"
						style="background: #00adb5; color: white; font-size: 16px"
					></q-btn>
				</div>
			</q-form>
			<div class="q-mt-lg row">
				<div class="col-2" v-for="res in studentResult" :key="res.userId">
					<q-btn push @click="addStudent(res.userId)"> + {{ res.name }} </q-btn>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { api } from 'src/boot/axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
	name: 'AddStudentPage',
	props: {
		classId: {
			type: Number,
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
	setup() {
		console.log('@@@@@', localStorage);

		const router = useRouter();

		const name = ref('');
		const studentResult = ref([]);

		function goSearchStudent() {
			api.get(`users/name/${name.value}`).then(res => {
				studentResult.value = res.data.users;
			});
		}

		function addStudent(userId) {
			api
				.post(`/tutor/classes/student`, {
					className: localStorage.getItem('className'),
					studentId: userId,
					tutorId: parseInt(localStorage.getItem('userId')),
				})
				.then(res => {
					console.log(res.message);
					console.log(JSON.parse(localStorage.getItem('classId')));
					router.push({
						name: '',
					});
				});
		}
		return { name, goSearchStudent, studentResult, addStudent };
	},
};
</script>

<style lang="scss" scoped>
.box {
	min-height: 300px !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
</style>
