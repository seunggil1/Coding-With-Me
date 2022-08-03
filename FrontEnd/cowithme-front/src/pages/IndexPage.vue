<template>
	<div>
		<div v-if="info2.role == '강사'">
			<div class="q-pa-md" style="font-family: 'Elice Digital Baeum'">
				<AtomPlusButton @click="makeClass" flat></AtomPlusButton>
				<div>
					{{ classes }}
				</div>
				<div class="q-gutter-md q-ml-sm">
					<div class="class-info">
						<div class="q-ma-lg">
							<!-- 반, 강의제목, 날짜 -->
							<div class="info q-ma-md" style="margin-top: 20px">
								<p style="font-size: 40px">예시 반(없어질 거)</p>
								<p style="font-size: 18px">제목: 프로젝트 1</p>
								<p style="font-size: 18px">날짜: 2022.07.25</p>
								<q-btn>학생 관리</q-btn>
							</div>
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
import { onBeforeMount } from 'vue';
import { fetchWrapper } from 'src/helpers';

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
		var classes = null;
		const HOST = 'http://i7a304.p.ssafy.io:8080/api/v1';

		const baseUrl = `${HOST}`;

		// const authStore = useAuthStore();
		// const { auth } = storeToRefs(authStore);
		const router = useRouter();

		async function makeClass() {
			await router.push({ path: '/makeClass' });
		}
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

		onBeforeMount(async () => {
			if (info2.role == '강사') {
				// 강사일 경우 반 정보를 불러옴
				try {
					const userId = info2.userId;
					console.log(userId);
					classes = await fetchWrapper.get(
						`${baseUrl}/tutor/${userId}/classes`,
					);
					console.log(classes);
					console.log(classes[0]);
					// console.log(this.classes);
				} catch (error) {
					console.log(error);
				}
			}
		});

		return {
			user2,
			info2,
			makeClass,
			classes,
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
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}

.button {
	margin-top: 60px;
	margin-left: 20px;
}
</style>
