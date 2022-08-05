<template>
	<div>
		<div>선생님이 흠냐뤼 반을 만들 수 있다.</div>
		<div id="q-app" style="min-height: 100vh">
			<div class="q-pa-md" style="max-width: 400px">
				<q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
					<q-input
						filled
						v-model="className"
						label="반 이름"
						lazy-rules
						:rules="[
							val => (val && val.length > 0) || '반 이름은 필수값입니다.',
						]"
					></q-input>

					<q-input
						filled
						type="text"
						v-model="classDescription"
						label="반 설명"
						lazy-rules
						:rules="[
							val => (val && val.length > 0) || '반 설명은 필수값입니다.',
						]"
					></q-input>

					<!-- <q-toggle
						v-model="accept"
						label="I accept the license and terms"
					></q-toggle> -->

					<div>
						<q-btn label="Submit" type="submit" color="primary"></q-btn>
						<q-btn
							label="Reset"
							type="reset"
							color="primary"
							flat
							class="q-ml-sm"
						></q-btn>
					</div>
				</q-form>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref } from 'vue';
// import { useQuasar } from 'quasar';
import { useRouter } from 'vue-router';
import { useAlertStore, useClassStore } from 'src/stores';

// const $q = useQuasar();
const router = useRouter();

const className = ref(null);
const classDescription = ref(null);
const tutor = localStorage.getItem('info');
var tutorParsed = null;
if (typeof tutor !== 'undefined') {
	tutorParsed = JSON.parse(tutor);
}
// const accept = ref(false);
async function onSubmit() {
	let classInformation = {
		className: className.value,
		classDescription: classDescription.value,
		tutorId: tutorParsed.userId,
	};

	const classStore = useClassStore();
	const alertStore = useAlertStore();

	try {
		await classStore.makeClass(classInformation);
		await router.push({ path: '/' });
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

function onReset() {
	// name.value = null;
	className.value = null;
	classDescription.value = false;
}
</script>

<style scoped></style>
