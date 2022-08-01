<template>
	<div v-if="user">
		<p>Hi {{ name }}!</p>
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
import { api } from 'src/boot/axios.js';
import { ref } from 'vue';

import { useAuthStore } from 'src/stores';

import { defineComponent, onBeforeMount } from 'vue';
import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
import ClassInfo from 'src/components/organisms/home/ClassInfo.vue';
import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
// import AtomLogo from 'src/components/atoms/AtomLogo.vue';

export default defineComponent({
	name: 'IndexPage',
	components: { CalendarInfo, ClassInfo, LectureTimeHistory },
	setup() {
		const name = ref(null);
		const authStore = useAuthStore();
		const { user } = storeToRefs(authStore);
		console.log(user);
		const val = JSON.stringify(user.value);
		console.log(val.id);
		onBeforeMount(() => {
			api
				.get(`/users/id/${val.id}`)
				.then(res => {
					name.value = res.data.name;
					console.log(res.data.name);
				})
				.catch(err => {
					console.log(err);
				});
		});
		return { user, name };
	},
});
</script>

<style></style>
