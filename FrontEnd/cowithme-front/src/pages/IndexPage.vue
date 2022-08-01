<template>
	<div v-if="user">
		<p>Hi {{ info.user.name }}!</p>
		<p>You're logged in!</p>
		<ClassInfo></ClassInfo>
		<div class="row">
			<CalendarInfo style="font-family: 'GmarketSansMedium'"></CalendarInfo>
			<div class="col-8">
				<LectureTimeHistory
					style="font-family: 'GmarketSansMedium'"
				></LectureTimeHistory>
			</div>
		</div>
	</div>
</template>

<script>
import { storeToRefs } from 'pinia';
// import { api } from 'src/boot/axios.js';
import { ref } from 'vue';

import { useAuthStore } from 'src/stores';

import { defineComponent } from 'vue';
import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
import ClassInfo from 'src/components/organisms/home/ClassInfo.vue';
import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
// import AtomLogo from 'src/components/atoms/AtomLogo.vue';

export default defineComponent({
	name: 'IndexPage',
	components: { CalendarInfo, ClassInfo, LectureTimeHistory },
	setup() {
		// const name = ref(null);
		const authStore = useAuthStore();
		const { user, info } = storeToRefs(authStore);
		console.log(user.value);
		const target_copy = Object.assign({}, user.value);
		console.log(target_copy);
		// const val = JSON.stringify(user.value);

		// onMounted(() => {
		// 	api
		// 		.get(`/users/id/${target_copy.id}`)
		// 		.then(res => {
		// 			console.log(res.data);
		// 			name.value = res.data.user.name;
		// 			console.log(name);
		// 		})
		// 		.catch(err => {
		// 			console.log(err);
		// 		});
		// });
		return { user, info };
	},
});
</script>

<style></style>
