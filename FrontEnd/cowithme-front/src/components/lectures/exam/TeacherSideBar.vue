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
					<q-chip outline color="positive" text-color="white"> 온라인 </q-chip>
					<q-chip outline color="negative" text-color="white">
						오프라인
					</q-chip>
				</div>
				<!-- 제출, 미제출 인원 -->
				<div class="q-pa-md col-5">
					<q-chip outline color="positive" text-color="white">
						제출 완료
					</q-chip>
					<q-chip outline color="negative" text-color="white"> 미제출 </q-chip>
				</div>
			</div>
		</div>

		<!-- 채팅 -->
		<div class="col-7" style="font-family: 'Elice Digital Baeum', sans-serif">
			<div
				class="column justify-center"
				style="height: 95vh; font-family: 'OTWelcomeBA'"
			>
				<q-scroll-area class="col-10 q-pa-xl" style="width: 100%">
					<span
						v-for="(chat, index) in piniaCommonVideoData.displayInfo.chatting"
						:key="index"
					>
						<q-chat-message
							v-if="chat.sender === piniaCommonVideoData.userInfo.userName"
							:name="chat.sender"
							:text="[chat.message]"
							stamp="just second"
							sent
							bg-color="amber-7"
							style="font-family: 'Elice Digital Baeum', sans-serif"
						/>
						<q-chat-message
							v-if="chat.sender !== piniaCommonVideoData.userInfo.userName"
							:name="chat.sender"
							style="font-family: 'Elice Digital Baeum', sans-serif"
							:text="[chat.message]"
							stamp="just second"
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
import { ref, onMounted, onUnmounted } from 'vue';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { commonVideoData } from 'src/stores/Video/common.js';
import { teacherVideoData } from 'src/stores/Video/teacher.js';
export default {
	components: {},

	props: {},
	setup() {
		const myChatInput = ref('');
		const piniaCommonExamData = commonExamData();
		const piniaCommonVideoData = commonVideoData();
		const piniaTeacherVideoData = teacherVideoData();

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

		return {
			myChatInput,
			piniaCommonVideoData,
			piniaTeacherVideoData,
			piniaCommonExamData,
		};
	},
};
</script>

<style lang="scss" scoped></style>
