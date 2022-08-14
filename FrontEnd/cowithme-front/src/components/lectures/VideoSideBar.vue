<template>
	<div
		class="q-pa-md column justify-center"
		style="height: 95vh; font-family: 'Elice Digital Baeum', 'sans-serif'"
	>
		<q-scroll-area class="col-6" style="width: 100%">
			<span>
				<h2>참가자 목록 보일곳</h2>
			</span>
		</q-scroll-area>

		<q-scroll-area class="col-5" style="width: 100%">
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
				/>
				<q-chat-message
					v-if="chat.sender !== piniaCommonVideoData.userInfo.userName"
					:name="chat.sender"
					:text="[chat.message]"
					stamp="just second"
					bg-color="amber-7"
				/>
			</span>
		</q-scroll-area>

		<div class="q-pa-md justify-center col-1">
			<q-input
				bottom-slots
				v-model="myChatInput"
				label="메시지를 입력하세요"
				:dense="true"
				color="secondary"
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
</template>

<script>
import { ref, watch } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
export default {
	props: {},
	setup() {
		let myChatInput = ref('');
		const piniaCommonVideoData = commonVideoData();

		watch(
			() => piniaCommonVideoData.displayInfo.chatting,
			() => console.log(piniaCommonVideoData.displayInfo.chatting),
		);

		return {
			myChatInput,
			piniaCommonVideoData,
		};
	},
};
</script>

<style scoped>
@font-face {
	font-family: 'OTWelcomeBA' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2') !important;
	font-weight: normal !important;
	font-style: normal !important;
}
</style>
