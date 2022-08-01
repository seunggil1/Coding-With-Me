<template>
	<div v-if="user">
		<p>Hi {{ user.id }}!</p>
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
		const authStore = useAuthStore();
		const { user } = storeToRefs(authStore);
		return { user };
	},
});
</script>

<style></style>
