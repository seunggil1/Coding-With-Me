<template>
	<div>
		<div
			id="q-app"
			class="q-pa-lg"
			style="font-family: 'Elice Digital Baeum', sans-serif"
		>
			<div class="q-pa-md" style="max-width: 700px">
				<p
					style="
						font-size: 40px;
						margin-bottom: 10%;
						font-family: 'MICEGothic Bold';
					"
				>
					새로운 반 만들기
				</p>
				<q-form
					@submit="goMakeClass"
					class="q-gutter-md"
					style="max-width: 70%"
				>
					<q-input
						name="className"
						rounded
						outlined
						bg-color="white"
						v-model="className"
						label="반 이름"
						lazy-rules
						:rules="[
							val => (val && val.length > 0) || '반 이름은 필수값입니다.',
						]"
					></q-input>

					<q-input
						rounded
						outlined
						type="text"
						v-model="classDescription"
						label="반 설명"
						bg-color="white"
						lazy-rules
						:rules="[
							val => (val && val.length > 0) || '반 설명은 필수값입니다.',
						]"
					></q-input>
					<q-btn
						push
						label="반 만들기"
						type="submit"
						text-color="white"
						style="background: #00adb5"
					></q-btn>
				</q-form>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAlertStore, useClassStore } from 'src/stores';

const router = useRouter();

const className = ref(null);
const classDescription = ref(null);
const tutor = localStorage.getItem('info');
var tutorParsed = null;
if (typeof tutor !== 'undefined') {
	tutorParsed = JSON.parse(tutor);
}
async function goMakeClass() {
	let classInformation = {
		className: className.value,
		classDescription: classDescription.value,
		tutorId: tutorParsed.userId,
	};

	const classStore = useClassStore();
	const alertStore = useAlertStore();

	try {
		await classStore.makeClass(classInformation);
		await router.push({ path: '/home' });
	} catch (error) {
		alertStore.error(error);
	}
}

// function onSubmitAlert() {
// 	if (className.value !== true && classDescription.value !== true) {
// 		$q.notify({
// 			color: 'red-5',
// 			textColor: 'white',
// 			icon: 'warning',
// 			message: '반 이름과 반 정보를 입력해주세요.',
// 		});
// 	} else {
// 		$q.notify({
// 			color: 'green-4',
// 			textColor: 'white',
// 			icon: 'cloud_done',
// 			message: 'Submitted',
// 		});
// 	}
// }
</script>

<style scoped>
@font-face {
	font-family: 'MICEGothic Bold' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2') !important;
	font-weight: 700 !important;
	font-style: normal !important;
}
</style>
