<template>
	<div>
		<div v-if="info2.role == '강사'">
			<div class="q-pa-md" style="font-family: 'Elice Digital Baeum'">
				<AtomPlusButton @click="makeClass" flat></AtomPlusButton>
				<div class="q-gutter-md q-ml-sm">
					<div
						class="flex column justify-between box q-pa-lg"
						v-for="clas in classes"
						:key="clas.classId"
					>
						<h3>{{ clas.className }}</h3>
						<p>{{ clas.classDescription }}</p>
						<router-link
							:to="{
								name: 'classDetail',
								params: {
									classId: clas.classId,
									className: clas.className,
									userId: info2.userId,
								},
							}"
							style="text-decoration: none; color: inherit"
							><q-btn push>반 관리하기</q-btn>
						</router-link>
					</div>
				</div>
			</div>
		</div>
		<div v-if="info2.role == '학생'">
			<ClassInfo></ClassInfo>
			<!-- <div>{{ classStore.userClass.className }}</div> -->
			<div>{{ testTest.value }}</div>
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
import { ref } from 'vue';
import { onBeforeMount } from 'vue';
import { fetchWrapper } from 'src/helpers';
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';
// import { useClassStore } from 'src/stores';

import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
import ClassInfo from 'src/components/organisms/home/ClassInfo.vue';
import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
import AtomPlusButton from 'src/components/atoms/AtomPlusButton.vue';

export default defineComponent({
	name: 'IndexPage',
	components: { CalendarInfo, ClassInfo, LectureTimeHistory, AtomPlusButton },
	setup() {
		// const classStore = useClassStore();
		const classes = ref([]);
		const HOST = 'http://i7a304.p.ssafy.io:8080/api/v1';
		const baseUrl = `${HOST}`;
		const testTest = ref([]);

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
		// console.log(user2);
		// console.log(info2);

		onBeforeMount(async () => {
			if (info2.role == '강사') {
				// 강사일 경우 반 정보를 불러옴
				try {
					const userId = info2.userId;
					const temp = await fetchWrapper.get(
						`${baseUrl}/tutor/${userId}/classes`,
					);
					classes.value.push(...temp.classes);
					// console.log(temp.classes);
					// console.log(classes.value);
					// console.log(classes.value[0]);
				} catch (error) {
					console.log(error);
				}
			}
		});

		onBeforeMount(async () => {
			try {
				var res = await fetchWrapper.get(`${baseUrl}/users/2/class`);
				testTest.value.push(res.value);
				// console.log(testTest);
			} catch (error) {
				console.log(error);
			}
			// classStore.getClass(info2.userId);
		});

		// async function goClassDetail() {
		// 	await router.push({
		// 		path: '/classDetail',
		// 		params: { classId: clas.classId },
		// 	});
		// }

		return {
			user2,
			info2,
			makeClass,
			classes,
			testTest,
			// goClassDetail,
		};
	},
});
</script>

<style scoped>
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
.box {
	height: 300px;
	width: 60%;
	min-width: 700px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
