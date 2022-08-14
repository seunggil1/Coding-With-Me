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

						<div class="class-description">
							<p style="color: white; margin: 18px">
								{{ clas.classDescription }}
							</p>
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
			<div
				class="q-pa-xl col flex justify-center"
				style="font-family: 'Elice Digital Baeum'"
			>
				<div class="q-gutter-md row">
					<div class="class-info hvr-grow">
						<div v-if="isInClass">
							<div class="col q-ma-lg">
								<!-- 반, 강의제목, 날짜 -->
								<div class="info" style="margin-bottom: 20px">
									<p style="font-size: 40px">
										{{ testTest.className }} 의 강의
									</p>
									<p style="font-size: 18px">강사: {{ testTest.tutorName }}</p>
								</div>
							</div>
							<div class="col">
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
						<div
							v-else
							class="row justify-center items-center"
							style="height: 100%"
						>
							<h5>강사님을 통해 반 등록을 먼저 진행해주세요!</h5>
						</div>
					</div>

					<div class="row">
						<q-date
							class="hvr-grow test"
							v-model="date"
							:events="events"
							event-color="orange"
							color="orange-14"
							style="font-family: 'Elice Digital Baeum'"
							landscape
						/>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { api } from 'src/boot/axios.js';
import { onMounted, ref } from 'vue';
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';

// import CalendarInfo from 'src/components/organisms/home/CalendarInfo.vue';
// import ClassInfo from 'src/components/organisms/home/ClassInfo.vue';
// import LectureTimeHistory from 'src/components/organisms/home/LectureTimeHistory.vue';
import AtomPlusButton from 'src/components/atoms/AtomPlusButton.vue';
import AtomBasic1Button from 'src/components/atoms/AtomBasic1Button.vue';
import { useClassStore } from 'src/stores';
import { studentVideoStore } from 'src/stores/studentVideo.store';
import { commonVideoData } from 'src/stores/Video/common.js';

export default defineComponent({
	name: 'IndexPage',
	components: {
		// CalendarInfo,
		// ClassInfo,
		// LectureTimeHistory,
		AtomPlusButton,
		AtomBasic1Button,
	},
	setup() {
		// console.log(this.$router.options.routes);
		// const classStore = useClassStore();
		const classes = ref([]);
		const testTest = ref([]);
		const HOST = 'https://i7a304.p.ssafy.io/api/v1';
		const baseUrl = `${HOST}`;
		const piniaCommonVideoData = commonVideoData();
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

		const isInClass = ref(false);
		onMounted(async () => {
			if (info2.role == '학생') {
				try {
					const res = await api.get(`/users/${userId}/class`);
					testTest.value = res.data.result;
					console.log(res.data);
					localStorage.setItem('testTest', JSON.stringify(res.data));
					isInClass.value = true;
					getActiveLecture();
				} catch (error) {
					console.log('학생인데 반 없음', error);
				}
			}
		});

		async function makeClass() {
			await router.push({ path: '/makeClass' });
		}
		const classStore = useClassStore();

		async function goSetClassInfo(classInfo, classId, className, userId) {
			classStore.setClassInfo(classInfo, classId, className, userId);
		}

		console.log('@@@@', localStorage);

		const studentVideo = studentVideoStore();

		async function enterLecture() {
			if (!activeLecture.value) {
				console.log('활성화된 강의 없음');
				return;
			}

			let id = JSON.parse(localStorage.getItem('user')).id;
			studentVideo.state.id = id; // 로그인 아이디
			let uid = JSON.parse(localStorage.getItem('info')).userId;
			studentVideo.state.userId = uid;
			let name = JSON.parse(localStorage.getItem('info')).name;
			studentVideo.state.myUserName = name;
			let classId = JSON.parse(localStorage.getItem('testTest')).result.classId;
			studentVideo.state.classId = classId;
			studentVideo.state.mySessionId = activeLecture.value.conferenceName;

			piniaCommonVideoData.userInfo.userId = id;
			piniaCommonVideoData.userInfo.userKey = uid;
			piniaCommonVideoData.userInfo.userName = name;
			piniaCommonVideoData.userInfo.classKey = classId;
			piniaCommonVideoData.userInfo.conferenceName = activeLecture.value.conferenceName;
			await piniaCommonVideoData.getConferenceKey();
			
			console.log('id', studentVideo.state.id);
			console.log('uid', studentVideo.state.userId);
			console.log('myUserName', studentVideo.state.myUserName);
			console.log('classId', studentVideo.state.classId);
			console.log('mySessionId', studentVideo.state.mySessionId);

			try {
				const response = await api.post(`/records/attendances`, {
					conferenceId: piniaCommonVideoData.userInfo.conferenceKey,
					userId: uid,
				});
				console.log('출입기록 생성 성공', response.data);
			} catch (error) {
				console.log('출입기록 생성 에러', error);
			}

			router.push('/studentlecture');
		}

		const activeLecture = ref({});
		const isActiveLecture = ref(false);
		const getActiveLecture = async () => {
			try {
				let classId = JSON.parse(localStorage.getItem('testTest')).result
					.classId;
				const response = await api.get(`/conferences/${classId}/active`);
				console.log(response.data);
				activeLecture.value = response.data.conference;
				// newCode
				piniaCommonVideoData.userInfo.conferenceKey = response.data.conference.conferenceId;
				//
				isActiveLecture.value = true;
			} catch (error) {
				console.log('active 강의 불러오기 에러(active 강의 없음)', error);
				isActiveLecture.value = false;
			}
		};

		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();

		today = yyyy + '/' + mm + '/' + dd;

		return {
			isInClass,
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
			splitterModel: ref(30),
			date: ref('2022/08/12'),
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
	height: 300px !important;
	min-width: 500px !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 0px rgba(0, 0, 0, 0.2) !important;
}

.button {
	margin-top: 60px !important;
	margin-left: 20px !important;
}
.box {
	height: 300px !important;
	width: 50% !important;
	min-width: 500px !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
.class-description {
	background-color: #ff8d29 !important;
	width: 60% !important;
	height: 40% !important;
	border-radius: 10px !important;
	box-shadow: 1px 1px 3px 1px #dadce0 !important;
}
[v-cloak] {
	display: none !important;
}
.test {
	-webkit-filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
	filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
}
</style>
