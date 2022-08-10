<template>
	<div class="row parti-chat-box">
		<div class="col-5" style="border: 1px solid red">
			<div class="column" style="border: 1px solid red; height: 100%">
				<div class="col-2" style="border: 1px solid red">
					<q-banner 
						dense 
						inline-actions 
						class="text-white bg-red"
					>
						{{ piniaData.formattedTime }}
						
						<template v-slot:action>
							<q-icon name="access_time" class="cursor-pointer"> </q-icon>
						</template>
					</q-banner>
				</div>
				<div class="col-5" style="border: 1px solid red">
					<p>오프라인</p>
					<p>온라인</p>
				</div>
				<div class="col-5" style="border: 1px solid red">
					<p>제출 완료</p>
					<p>제출 미완료</p>
				</div>
			</div>
		</div>

		<div class="col-7" style="border: 1px solid red">
			<div class="column" style="height: 100%">
				<div class="col-9" style="border: 1px solid red">
					<p>채팅 내역</p>
				</div>

				<div class="col-1" style="border: 1px solid red">
					<p>dm 선택</p>
				</div>

				<div class="col-2" style="border: 1px solid red">
					<p>textarea</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref, onMounted, computed, onUnmounted } from 'vue';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
export default {
	components: {},

	props: {},
	setup() {
		const piniaData = commonExamData();

		let timer;
		onMounted(() => {
			timer = setInterval(()=>{
				if(piniaData.timeLimit > 0)
					piniaData.timeLimit -= 1;
			},1000);
		});
		onUnmounted(() => {
			if(timer)
				clearInterval(timer);
		});

		return {
			piniaData
		};
	},
};
</script>

<style lang="scss" scoped></style>
