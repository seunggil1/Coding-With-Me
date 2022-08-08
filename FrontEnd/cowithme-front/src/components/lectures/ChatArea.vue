<template>
	<div class="chat-history"></div>
	<textarea class="chat-text-area" placeholder="채팅을 입력해주세요"></textarea>
</template>

<script>
export default {
	setup() {
		return {};
	},
};
</script>

<style>
.chat-history {
	width: 90%;
	height: 80%;
	border: 1px solid #eeeeee;
	margin-bottom: 5vh;
}
.chat-text-area {
	width: 90%;
	height: 10%;
	border: 1px solid #eeeeee;
	background-color: #303841;
	color: #eeeeee;
	resize: none;
}
</style>
