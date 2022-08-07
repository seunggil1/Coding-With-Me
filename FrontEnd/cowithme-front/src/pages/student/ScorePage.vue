<template>
	<div>
		<div>{{ records.value }}</div>
		<div></div>
		<div
			v-for="record in records"
			:key="record.testRecordId"
			class="box q-ma-lg q-pa-md q-gutter-md"
			style="font-family: 'Elice Digital Baeum'"
		>
			<div>
				<p>시험 날짜: {{ record.date }}</p>
				<p>시험 번호: {{ record.testRecordId }}</p>
				<p>시험 언어: {{ record.lang }}</p>
				<q-expansion-item
					expand-separator
					label="문제 별 답안 확인하기"
					icon="code"
					style="width: 70%"
				>
					<div v-for="code in record.sourceCode.answers" :key="code.qno">
						<p>{{ code.qno }}</p>
						<textarea
							v-model="code.code"
							style="width: 400px; height: 200px"
							readonly
						></textarea>
					</div>
				</q-expansion-item>
			</div>
		</div>
	</div>
</template>
<script>
// import { fetchWrapper } from 'src/helpers';
// import { onBeforeMount } from 'vue';
import { api } from 'src/boot/axios.js';
import { ref } from 'vue';

export default {
	name: 'ScorePage',
	props: {
		userId: {
			type: Number,
			// required: true,
		},
	},
	setup(props) {
		const records = ref({});

		api.get(`/records/${props.userId}/tests`).then(res => {
			records.value = res.data.records;
			console.log(records.value);
			localStorage.setItem('records', JSON.stringify(res.data.records));
			// console.log(records.value);
		});
		console.log(records);
		console.log(records.value);

		return { records };
	},
};
</script>

<style scoped>
.box {
	height: 100%;
	width: 60%;
	min-width: 700px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2);
}
</style>
