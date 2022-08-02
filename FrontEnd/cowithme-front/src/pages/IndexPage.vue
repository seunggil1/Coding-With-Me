<template>
	<div>
		<div v-if="info2.role == '강사'">
			<div class="q-pa-md" style="font-family: 'Elice Digital Baeum'">
				<AtomPlusButton @click="makeClass" flat></AtomPlusButton>
				<div class="q-gutter-md">
					<div class="class-info">
						<div class="col q-ma-lg">
							<!-- 반, 강의제목, 날짜 -->
							<div class="info" style="margin-bottom: 20px">
								<p style="font-size: 40px">예시 반(없어질 거)</p>
								<p style="font-size: 18px">제목: 프로젝트 1</p>
								<p style="font-size: 18px">날짜: 2022.07.25</p>
							</div>
						</div>
						<div class="col">
							<AtomBasic1Button
								class="button"
								push
								label="강의 입장"
								style="font-family: 'Elice Digital Baeum'"
							></AtomBasic1Button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div v-if="info2.role == '학생'">
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
// import { storeToRefs } from 'pinia';
// import { api } from 'src/boot/axios.js';
// import { ref } from 'vue';

// import { useAuthStore } from 'src/stores';

import { defineComponent } from 'vue';
import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
import ClassInfo from 'src/components/organisms/home/ClassInfo.vue';
import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
import AtomPlusButton from 'src/components/atoms/AtomPlusButton.vue';
import { useRouter } from 'vue-router';

// import AtomLogo from 'src/components/atoms/AtomLogo.vue';

export default defineComponent({
	name: 'IndexPage',
	components: { CalendarInfo, ClassInfo, LectureTimeHistory, AtomPlusButton },
	setup() {
		const router = useRouter();

		async function makeClass() {
			await router.push({ path: '/makeClass' });
		}
		// const name = ref(null);
		// const authStore = useAuthStore();
		// const { user, info } = storeToRefs(authStore);
		// console.log(user.value);
		// console.log(info.value);
		var user2 = null;
		var info2 = null;
		const user = localStorage.getItem('user');
		const info = localStorage.getItem('info');
		if (typeof user !== 'undefined') {
			user2 = JSON.parse(user);
		}
		if (typeof info !== 'undefined') {
			info2 = JSON.parse(info);
		}
		console.log(user2);
		console.log(info2);

		return {
			user2,
			info2,
			makeClass,
		};
	},
});
</script>

<style>
@font-face {
	font-family: 'GmarketSansLight';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
.class-info {
	height: 300px;
	width: 700px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 0px rgba(0, 0, 0, 0.2);
}

.button {
	margin-top: 60px;
	margin-left: 20px;
}
</style>
