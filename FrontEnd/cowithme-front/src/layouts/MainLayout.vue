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
					<div class="col">
						<!-- 반 API 들어올 곳 -->
						<ClassText
							style="font-size: 18px; font-family: 'OTWelcomeBA'"
						></ClassText>
					</div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col">
						<div class="row">
							<div
								class="col-3"
								style="font-size: 24px; font-family: 'OTWelcomeBA'"
							>
								{{ info.user.name }}
							</div>
							<div class="col-9">
								<WelcomeText
									style="font-size: 24px; font-family: 'OTWelcomeBA'"
								></WelcomeText>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<AtomSearchIconButton></AtomSearchIconButton>
				</div>
				<div class="row">
					<AtomScoreIconButton></AtomScoreIconButton>
				</div>
				<div class="row">
					<AtomMyPageButton></AtomMyPageButton>
				</div>
				<div class="row fixed-bottom">
					<AtomLogoutButton></AtomLogoutButton>
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
// import EssentialLink from 'components/EssentialLink.vue';
import TestComp from 'src/components/TestComp.vue';
import AtomLogo2 from 'src/components/atoms/AtomLogo2.vue';
import WelcomeText from 'src/components/molecules/home/WelcomeText.vue';
import ClassText from 'src/components/molecules/home/ClassText.vue';
import AtomSearchIconButton from 'src/components/atoms/AtomSearchIconButton.vue';
import AtomScoreIconButton from 'src/components/atoms/AtomScoreIconButton.vue';
import AtomMyPageButton from 'src/components/atoms/AtomMyPageButton.vue';
import AtomLogoutButton from 'src/components/atoms/AtomLogoutButton.vue';

import { storeToRefs } from 'pinia';
import { useAuthStore } from 'src/stores';
// import { useRouter } from 'vue-router';

export default defineComponent({
	name: 'MainLayout',
	components: {
		TestComp,
		AtomLogo2,
		WelcomeText,
		ClassText,
		AtomSearchIconButton,
		AtomScoreIconButton,
		AtomMyPageButton,
		AtomLogoutButton,
	},

	setup() {
		const authStore = useAuthStore();
		const { user, info } = storeToRefs(authStore);
		// const router = useRouter();
		// const authStore = useAuthStore();
		// if (authStore.user) {
		// 	router.push({ path: '/' });
		// }
		const leftDrawerOpen = ref(false);
		return {
			user,
			info,
			leftDrawerOpen,
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
