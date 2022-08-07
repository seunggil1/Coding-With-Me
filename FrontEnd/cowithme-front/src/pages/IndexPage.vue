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
									clas: JSON.stringify(clas),
									classId: Number(clas.classId),
									className: clas.className,
									userId: info2.userId,
								},
							}"
							style="text-decoration: none; color: inherit"
							><q-btn style="background: #00adb5; color: white" push
								>반 관리하기</q-btn
							>
						</router-link>
					</div>
				</div>
			</div>
		</div>
		<div v-if="info2.role == '학생'">
			<div class="q-pa-md" style="font-family: 'Elice Digital Baeum'">
				<div class="q-gutter-md">
					<div class="class-info">
						<div class="col q-ma-lg">
							<!-- 반, 강의제목, 날짜 -->
							<div class="info" style="margin-bottom: 20px">
								<p style="font-size: 40px">{{ testTest.className }} 의 강의</p>
								<p style="font-size: 18px">교수: {{ testTest.tutorName }}</p>
							</div>
						</div>
						<div class="col">
							<p>강의 리스트와 강의 입장 버튼들</p>
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
import { api } from 'src/boot/axios.js';
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';

import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
import AtomPlusButton from 'src/components/atoms/AtomPlusButton.vue';
import AtomBasic1Button from 'src/components/atoms/AtomBasic1Button.vue';

export default defineComponent({
	name: 'IndexPage',
	components: {
		CalendarInfo,
		LectureTimeHistory,
		AtomPlusButton,
		AtomBasic1Button,
	},
	setup() {
		const classes = ref([]);
		const testTest = ref([]);
		const HOST = 'http://i7a304.p.ssafy.io:8080/api/v1';
		const baseUrl = `${HOST}`;

		const router = useRouter();

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

		const userId = info2.userId;
		console.log(userId);
		if (info2.role == '강사') {
			// 강사일 경우 반 정보를 불러옴
			api
				.get(`${baseUrl}/tutor/${userId}/classes`)
				.then(res => {
					console.log(res);
					console.log(res.data);
					classes.value = res.data.classes;
					localStorage.setItem('classes', JSON.stringify(res.data.classes));
				})
				.catch(err => {
					console.log(err);
				});
		}

		if (info2.role == '학생') {
			api
				.get(`${baseUrl}/users/${userId}/class`)
				.then(res => {
					testTest.value = res.data.result;
					console.log(res.data);
					localStorage.setItem('testTest', JSON.stringify(res.data));
				})
				.catch(err => {
					console.log(err);
				});
		}
		async function makeClass() {
			await router.push({ path: '/makeClass' });
		}

		return {
			user2,
			info2,
			makeClass,
			classes,
			testTest,
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
.box {
	height: 300px;
	width: 60%;
	min-width: 700px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
