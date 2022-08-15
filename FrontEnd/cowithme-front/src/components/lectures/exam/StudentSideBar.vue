<template>
	<div
		class="row parti-chat-box"
		style="
			height: 95vh;
			font-family: 'Elice Digital Baeum', sans-serif;
			overflow-y: hidden;
		"
	>
		<div class="col-5" style="box-shadow: 2px 0px 3px #ff5722">
			<div class="column" style="height: 100%">
				<div class="col-1">
					<q-banner
						inline-actions
						class="text-white q-pa-md text-h6"
						style="
							background-color: #ff5722;
							font-family: 'Elice Digital Baeum', sans-serif;
						"
					>
						{{ piniaCommonExamData.formattedTime }}

						<template v-slot:action>
							<q-icon name="access_time" class="cursor-pointer"> </q-icon>
						</template>
					</q-banner>
				</div>

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
				<div class="q-pa-md col-5" style="border-bottom: 2px solid #ff5722">
					<user-video
						v-if="piniaCommonVideoData.openvidu.publisher"
						:stream-manager="piniaCommonVideoData.openvidu.publisher"
					/>
				</div>
				<div class="q-pa-sm col-5" style="border-bottom: 2px solid #ff5722">
					<user-video
						v-if="piniaCommonVideoData.openvidu.screenPublisher"
						:stream-manager="piniaCommonVideoData.openvidu.screenPublisher"
					/>
				</div>
			</div>
		</div>

		<div
			class="col-7"
			style="
				font-family: 'Elice Digital Baeum', sans-serif;
				border: 1px solid red;
			"
		>
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
import UserVideo from 'src/components/lectures/UserVideo.vue';
import { onMounted, onUnmounted, ref } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
export default {
	components: {
		UserVideo,
	},
	setup() {
		const myChatInput = ref('');
		const piniaCommonVideoData = commonVideoData();
		const piniaCommonExamData = commonExamData();

		let timer;
		onMounted(async () => {
			await piniaCommonVideoData.joinSession();
			await piniaCommonVideoData.startScreenShare();
			timer = setInterval(() => {
				if (piniaCommonExamData.timeLimit > 0)
					piniaCommonExamData.timeLimit -= 1;
			}, 1000);
		});

		onUnmounted(async () => {
			await piniaCommonVideoData.stopScreenShare();
			if (timer) clearInterval(timer);
		});

		return {
			myChatInput,
			piniaCommonVideoData,
			piniaCommonExamData,
		};
	},
};
</script>

<style lang="scss" scoped></style>
