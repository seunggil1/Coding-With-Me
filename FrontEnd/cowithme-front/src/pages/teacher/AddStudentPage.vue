<template>
	<div class="q-pa-md" style="max-width: 800px">
		<q-form @submit="goSearchStudent" class="q-gutter-md">
			<q-input filled v-model="name" label="학생 이름" />

			<div>
				<q-btn push label="검색하기" type="submit" color="primary" />
			</div>
		</q-form>
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

		function goSearchStudent(name) {
			api.get(`users/name/${name}`).then(res => {
				studentResult.value = res.data.users;
				console.log(studentResult.value);
			});
		}
		return { name, goSearchStudent, studentResult };
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
