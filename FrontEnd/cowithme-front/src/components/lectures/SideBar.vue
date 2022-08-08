<template>
	<div class="side-tab">
		<div class="side-tab-top">
			<ChatArea v-if="isChatBtnOn"></ChatArea>
			<ParticipantsArea v-else></ParticipantsArea>
		</div>
		<div class="side-tab-bottom">
			<q-btn
				class="participantsBtn"
				rounded
				outline
				push
				icon="groups"
				label="참가자"
				@click="clickPartiBtn"
			></q-btn>
			<q-btn
				class="chatBtn"
				rounded
				outline
				push
				icon="chat"
				label="채팅"
				@click="clickChatBtn"
			></q-btn>
		</div>
	</div>
</template>

<script>
import ChatArea from 'src/components/lectures/ChatArea.vue';
import ParticipantsArea from 'src/components/lectures/ParticipantsArea.vue';
import { ref } from 'vue';
export default {
	setup() {
		const isChatBtnOn = ref(true);
		const isParticipantsBtnOn = ref(false);

		const clickChatBtn = () => {
			if (isChatBtnOn.value) {
				return;
			}
			isChatBtnOn.value = !isChatBtnOn.value;
			isParticipantsBtnOn.value = !isParticipantsBtnOn.value;
		};

		const clickPartiBtn = () => {
			if (isParticipantsBtnOn.value) {
				return;
			}
			isChatBtnOn.value = !isChatBtnOn.value;
			isParticipantsBtnOn.value = !isParticipantsBtnOn.value;
		};

		return { isChatBtnOn, isParticipantsBtnOn, clickChatBtn, clickPartiBtn };
	},
	components: { ChatArea, ParticipantsArea },
};
</script>

<style>
.side-tab {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
.side-tab-top {
	display: flex;
	flex-direction: column;
	justify-content: flex-end;
	align-items: center;
	width: 100%;
	height: 90vh;
	/* border: 1px solid blue; */
}
.side-tab-bottom {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 10vh;
	/* border: 1px solid seagreen; */
}
.participantsBtn,
.chatBtn {
	color: #eeeeee;
	margin-left: 8px;
	margin-right: 8px;
}
</style>
