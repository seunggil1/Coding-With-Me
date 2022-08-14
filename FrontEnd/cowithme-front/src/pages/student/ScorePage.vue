<template>
	<div>
		<div
			v-for="record in records"
			:key="record.testRecordId"
			class="box q-ma-lg q-pa-md q-gutter-md hvr-grow"
			style="font-family: 'Elice Digital Baeum'"
		>
			<div class="">
				<p style="font-size: 40px; font-family: 'MICEGothic Bold'">
					{{ record.date }} 시험
				</p>
				<q-chip outline square color="secondary" text-color="white">
					시험 언어: {{ record.lang }}
				</q-chip>
				<q-chip outline square color="deep-orange" text-color="white">
					시험 점수: {{ record.correctCount }} /
					{{ record.sourceCode.answers.length }}
				</q-chip>
				<q-expansion-item
					expand-separator
					label="문제 별 제출 답안 확인하기"
					icon="code"
					style="width: 60%; font-size: 16px; margin-top: 5%"
				>
					<div
						class="q-mt-lg"
						v-for="code in record.sourceCode.answers"
						:key="code.qno"
					>
						<q-badge rounded color="orange" :label="`${code.qno}`" />
						<div class="q-my-md"></div>
						<textarea
							v-model="code.code"
							style="width: 80%; height: 200px"
							readonly
						></textarea>
					</div>
				</q-expansion-item>
			</div>
		</div>
	</div>
</template>
<script>
import { api } from 'src/boot/axios.js';
import { ref } from 'vue';

export default {
	name: 'ScorePage',
	props: {
		userId: {
			type: Number,
		},
	},
	setup(props) {
		const records = ref({});

		api.get(`/records/${props.userId}/tests`).then(res => {
			records.value = res.data.records;
			localStorage.setItem('records', JSON.stringify(res.data.records));
		});
		return { records };
	},
};
</script>

<style scoped>
@font-face {
	font-family: 'MICEGothic Bold' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2') !important;
	font-weight: 700 !important;
	font-style: normal !important;
}
.box {
	height: 100% !important;
	width: 60% !important;
	min-width: 700px !important;
	background-color: white !important;
	border-radius: 10px !important;
	box-shadow: 3px 3px 3px 2px rgba(0, 0, 0, 0.2) !important;
}
.hvr-grow {
	display: inline-block;
	vertical-align: middle;
	transform: translateZ(0);
	box-shadow: 0 0 1px rgba(0, 0, 0, 0);
	backface-visibility: hidden;
	-moz-osx-font-smoothing: grayscale;
	transition-duration: 0.3s;
	transition-property: transform;
}

.hvr-grow:hover,
.hvr-grow:focus,
.hvr-grow:active {
	transform: scale(1.01);
}
</style>
