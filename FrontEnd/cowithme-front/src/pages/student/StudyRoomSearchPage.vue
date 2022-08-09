<template>
	<!-- 스터디 검색 화면 -->
	<div>
		<div
			class="box q-ma-lg q-pa-md q-gutter-md flex row justify-center"
			style="font-family: 'Elice Digital Baeum'"
		>
			<q-form @submit="onSubmit" class="q-gutter-md flex row justify-center">
				<q-input
					filled
					v-model="studyRoomName"
					label="스터디명"
					lazy-rules
				></q-input>
				<q-btn
					style="max-height: 55px"
					push
					class="flex"
					label="검색하기"
					type="submit"
					color="primary"
				></q-btn>
			</q-form>
			<AtomPlusButton style="float: left"></AtomPlusButton>
		</div>
	</div>
</template>

<script>
import AtomPlusButton from 'src/components/atoms/AtomPlusButton.vue';
export default {
	name: 'StudyRoomSearchPage',
	setup() {
		return {};
	},
	components: { AtomPlusButton },
};
</script>

<style scoped>
.box {
	height: 100% !important;
	width: 60% !important;
	min-width: 700px !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
</style>
