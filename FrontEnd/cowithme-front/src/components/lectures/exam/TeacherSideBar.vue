<template>
	<div
		class="row parti-chat-box"
		style="height: 95vh; font-family: 'Elice Digital Baeum', sans-serif"
	>
		<!-- 시험 정보 -->
		<div class="col-5" style="box-shadow: 2px 0px 3px #ff5722">
			<div class="column" style="height: 100%">
				<!-- 시간 표시 -->
				<div class="col-1">
					<q-banner
						dense
						inline-actions
						class="text-white q-pa-md"
						style="
							background-color: #ff5722;
							font-family: 'Elice Digital Baeum', sans-serif;
						"
					>
						<div class="flex items-center">
							<div>{{ piniaCommonExamData.formattedTime }}</div>
						</div>

						<template v-slot:action>
							<q-icon name="access_time" class="cursor-pointer"> </q-icon>
						</template>
					</q-banner>
				</div>
				<!-- 시험 제목 -->
				<div class="col-1">
					<div
						class="q-px-md q-pb-md text-h6"
						style="
							font-family: 'Elice Digital Baeum', sans-serif;
							border-bottom: 2px solid #ff5722;
							font-weight: bold;
						"
					>
						시험: {{ piniaCommonExamData.testName }}
					</div>
				</div>
				<!-- 오프라인, 온라인 인원 -->
				<div class="q-pa-md col-5" style="border-bottom: 2px solid #ff5722">
					<q-btn-toggle
						v-model="showOnline"
						push
						no-caps
						unelevated
						color="grey-5"
						toggle-color="teal"
						class="col q-px-sm"
						:options="[
							{ label: '온라인', value: true },
							{ label: '오프라인', value: false },
						]"
					/>

					<q-scroll-area style="height: 30vh" class="q-pa-sm">
						<div
							v-for="(student, index) in piniaCommonVideoData.displayInfo
								.studentList"
							:key="index"
						>
							<span
								style="font-size: 18px"
								v-if="
									piniaCommonVideoData.displayInfo.studentListIsActive[
										index
									] === showOnline
								"
							>
								{{ student }}
							</span>
						</div>
					</q-scroll-area>
				</div>
				<!-- 제출, 미제출 인원 -->
				<div class="q-pa-md col-5">
					<q-btn-toggle
						v-model="showSubmit"
						push
						no-caps
						unelevated
						color="grey-5"
						toggle-color="teal"
						class="col q-px-sm"
						:options="[
							{ label: '제출', value: true },
							{ label: '미제출', value: false },
						]"
					/>

					<q-scroll-area style="height: 40vh" class="q-pa-sm">
						<div
							v-for="(student, index) in piniaCommonVideoData.displayInfo
								.studentList"
							:key="index"
						>
							<span
								style="font-size: 18px"
								v-if="
									piniaTeacherExamData.submitStudentList[index] === showSubmit
								"
							>
								{{ student }}
							</span>
						</div>
					</q-scroll-area>
				</div>
			</div>
		</div>

		<!-- 채팅 -->
		<div class="col-7" style="font-family: 'Elice Digital Baeum', sans-serif">
			<div
				class="column justify-center"
				style="height: 95vh; font-family: 'OTWelcomeBA'"
			>
				<q-scroll-area ref="chatting" class="col-10 q-pa-xl" style="width: 100%">
					<span
						v-for="(chat, index) in piniaCommonVideoData.displayInfo.chatting"
						:key="index"
					>
						<q-chat-message
							v-if="chat.sender === piniaCommonVideoData.userInfo.userName"
							:name="chat.sender"
							:text="[chat.message]"
							sent
							bg-color="amber-7"
							style="font-family: 'Elice Digital Baeum', sans-serif"
						/>
						<q-chat-message
							v-if="chat.sender !== piniaCommonVideoData.userInfo.userName"
							:name="chat.sender"
							style="font-family: 'Elice Digital Baeum', sans-serif"
							:text="[chat.message]"
							bg-color="amber-9"
						/>
					</span>
				</q-scroll-area>

				<div class="q-px-md justify-center col-2">
					<q-input
						bottom-slots
						v-model="myChatInput"
						label="메시지를 입력하세요"
						:dense="true"
						color="secondary"
						style="font-family: 'Elice Digital Baeum', sans-serif"
						@keydown.enter.prevent="
							piniaCommonVideoData.sendMessage(myChatInput);
							myChatInput = '';
						"
					>
						<template v-slot:append>
							<q-icon
								v-if="myChatInput !== ''"
								name="close"
								@click="myChatInput = ''"
								class="cursor-pointer"
							/>
						</template>

						<template v-slot:after>
							<q-btn
								round
								dense
								flat
								icon="send"
								@click="
									piniaCommonVideoData.sendMessage(myChatInput);
									myChatInput = '';
								"
							/>
						</template>
					</q-input>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { teacherExamData } from 'src/stores/ExamProgress/teacher.js';
import { commonVideoData } from 'src/stores/Video/common.js';
import { teacherVideoData } from 'src/stores/Video/teacher.js';
export default {
	components: {},

	props: {},
	setup() {
		const myChatInput = ref('');
		const piniaCommonExamData = commonExamData();
		const piniaTeacherExamData = teacherExamData();
		const piniaCommonVideoData = commonVideoData();
		const piniaTeacherVideoData = teacherVideoData();
		const showOnline = ref(true);
		const showSubmit = ref(true);

		let timer;
		onMounted(() => {
			timer = setInterval(() => {
				if (piniaCommonExamData.timeLimit > 0)
					piniaCommonExamData.timeLimit -= 1;
			}, 1000);
		});
		onUnmounted(() => {
			if (timer) clearInterval(timer);
		});

		const chatting = ref(undefined);
		watch(piniaCommonVideoData.displayInfo.chatting, () => {
			setTimeout(() => {
				chatting.value.setScrollPercentage("vertical", 1);
				console.log(chatting.value.getScrollPercentage());
			}, 500);
		});	

		return {
			myChatInput,
			piniaCommonVideoData,
			piniaTeacherExamData,
			piniaTeacherVideoData,
			piniaCommonExamData,
			showOnline,
			showSubmit,
			chatting
		};
	},
};
</script>

<style lang="scss" scoped></style>
