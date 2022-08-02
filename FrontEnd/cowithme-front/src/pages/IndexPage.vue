<template>
	<div>
		<div v-if="info3.role == '강사'">
			<p>강사</p>
		</div>
		<div v-if="info3.role == '학생'">
			<!-- <p>Hi {{ info.user.name }}!</p>
		<p>You're logged in!</p> -->
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
	</div>
</template>

<script>
import { storeToRefs } from 'pinia';
// import { api } from 'src/boot/axios.js';
// import { ref } from 'vue';

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
		// console.log(user.value);
		// console.log(info.value);
		const info2 = Object.assign({}, info.value);
		const info3 = JSON.parse(JSON.stringify(info2.user));
		console.log(info3);
		return { user, info3 };
	},
});
</script>

<style></style>
