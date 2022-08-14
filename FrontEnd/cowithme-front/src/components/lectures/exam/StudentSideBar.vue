<template>
    <div
		class="row parti-chat-box"
		style="height: 95vh; font-family: 'Elice Digital Baeum', sans-serif"
	>
		<div class="col-5" style="border: 1px solid #ff5722">
			<div class="column" style="border: 1px solid #ff5722; height: 100%">
				<div class="col-1">
					<q-banner
						dense
						inline-actions
						class="text-white q-pa-md"
						style="
							border: 1px solid #ff5722;
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
						class="q-pa-sm text-h6"
						style="font-family: 'Elice Digital Baeum', sans-serif"
					>
						{{ piniaCommonExamData.testName }}
					</div>
				</div>
				<div class="q-pa-sm col-5" style="border: 1px solid red">
					<user-video v-if="piniaCommonVideoData.openvidu.publisher" :stream-manager="piniaCommonVideoData.openvidu.publisher" />
				</div>
				<div class="q-pa-sm col-5" style="border: 1px solid red">
					<user-video v-if="piniaCommonVideoData.openvidu.screenPublisher" :stream-manager="piniaCommonVideoData.openvidu.screenPublisher" />
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
							stamp="just second"
							sent
							bg-color="amber-7"
							style="font-family: 'Elice Digital Baeum', sans-serif"
						/>
						<q-chat-message
							v-if="chat.sender !== piniaCommonVideoData.userInfo.userName"
							:name="chat.sender"
							style="font-family: 'Elice Digital Baeum', sans-serif"
							avatar="https://cdn.quasar.dev/img/avatar3.jpg"
							:text="[chat.message]"
							stamp="just second"
							bg-color="amber-7"
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
						<!-- <template v-slot:before>
							<q-avatar>
								<img src="https://cdn.quasar.dev/img/avatar5.jpg" />
							</q-avatar>
						</template> -->

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
import { ref, onMounted, onUnmounted } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
export default {
    components : {
        UserVideo
    },
    setup () {
        const piniaCommonVideoData = commonVideoData();
        const piniaCommonExamData = commonExamData();

        onMounted(async () => {
            await piniaCommonVideoData.joinSession();
            await piniaCommonVideoData.startScreenShare();
        });

        onUnmounted(async () => {
            await piniaCommonVideoData.stopScreenShare();
            await piniaCommonVideoData.leaveSession();
        });

        return {
            piniaCommonVideoData,
            piniaCommonExamData
        }
    }
}
</script>

<style lang="scss" scoped>

</style>