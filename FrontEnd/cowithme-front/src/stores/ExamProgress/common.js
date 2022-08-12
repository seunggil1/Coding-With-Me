import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { api } from 'src/boot/axios.js';

export const commonExamData = defineStore('commonExamData', () => {
	const testID = ref(3);
	const testName = ref('백준 골드1~플래4 문제들');
	const testCase = ref([
		[
			{
				input: '5\n1 1 Y\n1 -1 Y\n0 0 N\n-1 -1 Y\n-1 1 Y',
				output: '4\n-1 -1\n1 -1\n1 1\n-1 1',
			},
		],
		[
			{
				input: '1 7\nf0.F..1',
				output: '7',
			},
			{
				input: '5 5\n....1\n#1###\n.1.#0\n....A\n.1.#.',
				output: '-1',
			},
			{
				input: '7 8\na#c#eF.1\n.#.#.#..',
				output: '55',
			},
		],
		[
			{
				input: '5\n 5 1 2 3 4\n3\n2 4 1\n6 6 6\n1 5 2',
				output: '2\n0\n3',
			},
		],
	]);

	const testQuCnt = computed(() => {
		return testCase.value.length;
	});

	// chatting은 studentVideo, teacherVideo 쓴다. 대신 들어가기전에 chatting 초기화 하자.

	// 시험 시간 관련 데이터
	const timeLimit = ref(3600 * 0 + 60 * 7 + 1 * 50);
	const setTimeLimit = (hour, min, sec) => {
		timeLimit.value = 3600 * hour + 60 * min + 1 * sec;
	};
	const formattedTime = computed(() => {
		let time = timeLimit.value;

		const hour = parseInt(time / 3600);
		time %= 3600;

		const minute = parseInt(time / 60);
		const second = time % 60;

		return `${hour < 10 ? '0' + hour : hour}:${
			minute < 10 ? '0' + minute : minute
		}:${second}`;
	});

	// 시험지 데이터 받아오기
	const getTestInfo = async (classID, testName) => {
		testCase.value = [];
		let res = (await api.get(`/tests/${classID}/${testName}`)).data.test;
		testID.value = res.testId;
		for (let item of res.testcase.testcaseList) {
			testCase.value.push(item.testcase);
		}
	};

	return {
		testID,
		testName,
		testCase,
		testQuCnt,

		timeLimit,
		setTimeLimit,
		formattedTime,

		getTestInfo,
	};
});
