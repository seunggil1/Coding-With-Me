<template>
	<q-layout view="lHh Lpr lFf">
		<q-header elevated>
			<q-toolbar style="background-color: #00adb5">
				<TestComp @click="toggleLeftDrawer" flat></TestComp>
				<!-- <q-toolbar-title> CowithMe 코윗미~ </q-toolbar-title> -->
			</q-toolbar>
		</q-header>

		<q-drawer v-model="leftDrawerOpen" show-if-above bordered>
			<div class="navbar" style="background-color: #00adb5; height: 100vh">
				<div class="row">
					<div class="col"></div>
					<div class="col-10">
						<AtomLogo2 style="width: 90%"></AtomLogo2>
					</div>
					<div class="col"></div>
				</div>
				<div class="row">
					<div class="col-1"></div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col">
						<div class="row">
							<div
								class="col-3"
								style="font-size: 24px; font-family: 'OTWelcomeBA'"
							>
								{{ info2.name }}<span v-if="info2.role == '강사'">(강사)</span>
							</div>
							<div class="col-9">
								<WelcomeText
									style="font-size: 24px; font-family: 'OTWelcomeBA'"
								></WelcomeText>
							</div>
						</div>
					</div>
				</div>
				<div v-if="info2.role == 학생" class="row">
					<AtomSearchIconButton></AtomSearchIconButton>
				</div>
				<div v-if="info2.role == 학생" class="row">
					<AtomScoreIconButton></AtomScoreIconButton>
				</div>
				<div class="row" @click="goToMypage">
					<AtomMyPageButton></AtomMyPageButton>
				</div>
				<div class="row fixed-bottom">
					<AtomLogoutButton @click="goLogout"></AtomLogoutButton>
				</div>
			</div>
		</q-drawer>
		<q-page-container>
			<router-view />
		</q-page-container>
	</q-layout>
</template>

<script>
import { defineComponent, ref } from 'vue';
import TestComp from 'src/components/TestComp.vue';
import AtomLogo2 from 'src/components/atoms/AtomLogo2.vue';
import WelcomeText from 'src/components/molecules/home/WelcomeText.vue';
import AtomSearchIconButton from 'src/components/atoms/AtomSearchIconButton.vue';
import AtomScoreIconButton from 'src/components/atoms/AtomScoreIconButton.vue';
import AtomMyPageButton from 'src/components/atoms/AtomMyPageButton.vue';
import AtomLogoutButton from 'src/components/atoms/AtomLogoutButton.vue';
import { useRouter } from 'vue-router';
// import { storeToRefs } from 'pinia';
import { useAuthStore } from 'src/stores';

export default defineComponent({
	name: 'MainLayout',
	components: {
		TestComp,
		AtomLogo2,
		WelcomeText,
		AtomSearchIconButton,
		AtomScoreIconButton,
		AtomMyPageButton,
		AtomLogoutButton,
	},

	setup() {
		var user2 = null;
		var info2 = null;
		const user = localStorage.getItem('user');
		const info = localStorage.getItem('info');
		if (typeof user !== 'undefined') {
			user2 = JSON.parse(user);
		}
		if (typeof info !== 'undefined') {
			info2 = JSON.parse(info);
		}
		console.log(user2);
		console.log(info2);

		const leftDrawerOpen = ref(false);
		const router = useRouter();
		async function goToMypage() {
			await router.push({ path: '/mypage' });
		}

		async function goLogout() {
			const authStore = useAuthStore();
			await authStore.logout();
		}

		return {
			user2,
			info2,
			leftDrawerOpen,
			goToMypage,
			goLogout,
			toggleLeftDrawer() {
				leftDrawerOpen.value = !leftDrawerOpen.value;
			},
		};
	},
});
</script>

<style>
@font-face {
	font-family: 'OTWelcomeBA';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2');
	font-weight: normal;
	font-style: normal;
}
</style>
