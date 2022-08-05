<template>
	<div class="q-pa-md">
		<q-btn>개나리반 의 강의 생성</q-btn>
		<div class="box2">
			<p>강의 목록</p>
			<q-btn>강의 시작(WebRTC 토큰 설정되면)</q-btn>
		</div>
		<div class="row box">
			<div class="col-6">학생 목록 및 추가</div>
			<div class="col-6">
				시험 목록 및 생성
				<router-link :to="{ name: 'makeExam', params: { classId: classId } }">
					<q-btn>시험 생성</q-btn>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'ClassDetailPage',
	props: {
		classId: {
			type: String,
			// required: true,
		},
	},
	setup() {
		return {};
	},
};
</script>

<style scoped>
.box {
	height: 300px;
	width: 100%;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
.box2 {
	height: 300px;
	width: 100%;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
