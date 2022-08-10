<template>
	<div>
		<div v-if="info2.role == '강사'">
			<div class="q-pa-md" style="font-family: 'Elice Digital Baeum'">
				<AtomPlusButton @click="makeClass" flat></AtomPlusButton>
				<div class="q-gutter-md q-ml-sm">
					<div
						class="flex column justify-between box q-pa-lg hvr-grow"
						v-for="clas in classes"
						:key="clas.classId"
					>
						<p style="font-size: 40px; font-family: 'MICEGothic Bold'">
							{{ clas.className }}
						</p>
						<div class="class-description q-pa-md">
							<p style="color: white">{{ clas.classDescription }}</p>
						</div>
						<router-link
							:to="{
								name: 'classDetail',
								params: {
									classId: clas.classId,
								},
							}"
							style="text-decoration: none; color: inherit"
							><q-btn
								@click="
									goSetClassInfo(
										clas,
										clas.classId,
										clas.className,
										info2.userId,
									)
								"
								style="background: #00adb5; color: white; font-size: 16px"
								push
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
								v-if="isActiveLecture"
								class="button"
								push
								label="강의 입장"
								style="font-family: 'Elice Digital Baeum'"
								@click="enterLecture"
							></AtomBasic1Button>
						</div>
					</div>
				</div>
			</div>
			<!-- <div>{{ classStore.userClass.className }}</div> -->
			<div class="q-pt-lg">
				<q-splitter v-model="splitterModel" style="height: 450px">
					<template v-slot:before>
						<div class="q-pa-md">
							<CalendarInfo
								class="hvr-grow"
								v-model="date"
								:events="events"
								event-color="orange"
								style="font-family: 'Elice Digital Baeum'"
							></CalendarInfo>
						</div>
					</template>

					<template v-slot:after>
						<q-tab-panels
							v-model="date"
							animated
							transition-prev="jump-up"
							transition-next="jump-up"
						>
							<q-tab-panel name="2019/02/01">
								<!-- <div class="text-h4 q-mb-md">2019/02/01</div> -->
								<!-- <p>
									Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis
									praesentium cumque magnam odio iure quidem, quod illum numquam
									possimus obcaecati commodi minima assumenda consectetur culpa
									fuga nulla ullam. In, libero.
								</p>
								<p>
									Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis
									praesentium cumque magnam odio iure quidem, quod illum numquam
									possimus obcaecati commodi minima assumenda consectetur culpa
									fuga nulla ullam. In, libero.
								</p> -->
								<LectureTimeHistory
									class="hvr-grow"
									style="font-family: 'Elice Digital Baeum'"
								></LectureTimeHistory>
							</q-tab-panel>
						</q-tab-panels>
					</template>
				</q-splitter>
				<!-- <CalendarInfo
					class="hvr-grow"
					style="font-family: 'Elice Digital Baeum'"
				></CalendarInfo>
				<div class="col-8">
					<LectureTimeHistory
						class="hvr-grow"
						style="font-family: 'Elice Digital Baeum'"
					></LectureTimeHistory>
				</div> -->
			</div>
		</div>
	</div>
</template>

<script>
// import { app.css}  from './app.css'
import { api } from 'src/boot/axios.js';
import { ref } from 'vue';
// import { onBeforeMount } from 'vue';
// import { fetchWrapper } from 'src/helpers';
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';
// import { useClassStore } from 'src/stores';

import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
// import ClassInfo from 'src/components/organisms/home/ClassInfo.vue';
import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
import AtomPlusButton from 'src/components/atoms/AtomPlusButton.vue';
import AtomBasic1Button from 'src/components/atoms/AtomBasic1Button.vue';
import { useClassStore } from 'src/stores';
import { studentVideoStore } from 'src/stores/studentVideo.store';

export default defineComponent({
	name: 'IndexPage',
	components: {
		CalendarInfo,
		// ClassInfo,
		LectureTimeHistory,
		AtomPlusButton,
		AtomBasic1Button,
	},
	setup() {
		// const classStore = useClassStore();
		const classes = ref([]);
		const testTest = ref([]);
		const HOST = 'https://i7a304.p.ssafy.io/api/v1';
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
			// classes.value.push(...temp.classes);
			// console.log(temp.classes);
			// console.log(classes.value);
			// console.log(classes.value[0]);
		}

		if (info2.role == '학생') {
			api
				.get(`/users/${userId}/class`)
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
		const classStore = useClassStore();

		async function goSetClassInfo(classInfo, classId, className, userId) {
			classStore.setClassInfo(classInfo, classId, className, userId);
		}

		console.log('@@@@', localStorage);

		let classId = localStorage.getItem('classId');
		const studentVideo = studentVideoStore();

		function enterLecture() {
			if (!activeLecture.value) {
				console.log('활성화된 강의 없음');
				return;
			}
			let id = JSON.parse(localStorage.getItem('user')).id;
			studentVideo.state.id = id; // 로그인 아이디
			let uid = parseInt(localStorage.getItem('userId'));
			studentVideo.state.userId = uid;
			let name = JSON.parse(localStorage.getItem('info')).name;
			studentVideo.state.myUserName = name;
			let classId = parseInt(localStorage.getItem('classId'));
			studentVideo.state.classId = classId;
			studentVideo.state.mySessionId = activeLecture.value.conferenceName;

			console.log('id', studentVideo.state.id);
			console.log('uid', studentVideo.state.userId);
			console.log('myUserName', studentVideo.state.myUserName);
			console.log('classId', studentVideo.state.classId);
			console.log('mySessionId', studentVideo.state.mySessionId);

			studentVideo.joinSession();
			router.push('/studentlecture');
		}

		const activeLecture = ref({});
		const isActiveLecture = ref(false);
		const getActiveLecture = async () => {
			try {
				const response = await api.get(`/conferences/${classId}/active`);
				console.log(response.data);
				activeLecture.value = response.data.conference;
				isActiveLecture.value = true;
			} catch (error) {
				console.log('active 강의 불러오기 에러', error);
				isActiveLecture.value = false;
			}
		};
		getActiveLecture();

		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();

		today = yyyy + '/' + mm + '/' + dd;

		return {
			activeLecture,
			isActiveLecture,
			getActiveLecture,
			enterLecture,
			user2,
			info2,
			makeClass,
			classes,
			testTest,
			goSetClassInfo,
			splitterModel: ref(39),
			date: ref('2019/02/01'),
			events: ['2019/02/01', '2019/02/05', '2019/02/06'],
		};
	},
});
</script>

<style scoped>
@import url('src/css/app.css');

@font-face {
	font-family: 'MICEGothic Bold';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2');
	font-weight: 700;
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
	height: 300px !important;
	width: 60% !important;
	min-width: 700px !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
.class-description {
	background-color: #ff8d29 !important;
	width: 60% !important;
	border-radius: 10px !important;
	box-shadow: 1px 1px 3px 1px #dadce0 !important;
}
</style>
