<template>
	<div class="q-pa-md" style="max-width: 800px">
		<q-form @submit="goSearchStudent" class="q-gutter-md">
			<q-input filled v-model="name" label="학생 이름" />

			<div>
				<q-btn push label="검색하기" type="submit" color="primary" />
			</div>
		</q-form>
		<div v-for="res in studentResult" :key="res.userId">
			<q-btn @click="addStudent(res.userId)">
				{{ res.name }}
			</q-btn>
		</div>
	</div>
</template>

<script>
import { api } from 'src/boot/axios';
import { ref } from 'vue';

export default {
	name: 'AddStudentPage',
	setup() {
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
					className: JSON.parse(localStorage.getItem('className')),
					studentId: userId,
					tutorId: 1,
				})
				.then(res => {
					console.log(res.message);
				});
		}
		return { name, goSearchStudent, studentResult, addStudent };
	},
};
</script>

<style lang="scss" scoped>
.box {
	min-height: 300px;
	/* width: 100%; */
	background-color: #eeeeee;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
