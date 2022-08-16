<template>
	<div
		class="q-pa-md column justify-center"
		style="height: 95vh; font-family: 'Elice Digital Baeum', 'sans-serif'"
	>
		<q-scroll-area class="col-6" style="width: 100%">
			<q-btn-toggle
				v-model="showOnline"
				push
				no-caps
				unelevated
				color="grey-5"
				toggle-color="teal"
				class="col q-pa-sm"
				:options="[
					{ label: '온라인', value: true },
					{ label: '오프라인', value: false },
				]"
			/>

			<q-scroll-area
				style="height: 30vh; border-bottom: 1px solid grey"
				class="q-pa-sm"
			>
				<div
					v-for="(student, index) in piniaCommonVideoData.displayInfo
						.studentList"
					:key="index"
				>
					<span
						v-if="
							piniaCommonVideoData.displayInfo.studentListIsActive[index] ===
							showOnline
						"
						style="font-size: 18px"
					>
						{{ student }}
					</span>
				</div>
			</q-scroll-area>
		</q-scroll-area>

		<q-scroll-area ref="chatting" class="col-5" style="width: 100%">
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
				/>
				<q-chat-message
					v-if="chat.sender !== piniaCommonVideoData.userInfo.userName"
					:name="chat.sender"
					:text="[chat.message]"
					bg-color="amber-9"
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
import { ref, watch, onUpdated, onMounted } from 'vue';
import { commonVideoData } from 'src/stores/Video/common.js';
export default {
	props: {},
	setup() {
		let myChatInput = ref('');
		const piniaCommonVideoData = commonVideoData();
		const showOnline = ref(true);

		const chatting = ref(undefined);
		watch(piniaCommonVideoData.displayInfo.chatting, () => {
			setTimeout(() => {
				chatting.value.setScrollPercentage("vertical", 1)
			}, 300);
		});		

		// onUpdated(() => {
		// 	
		// 	console.log("change Length");
		// });

		return {
			myChatInput,
			piniaCommonVideoData,
			showOnline,

			chatting,
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
