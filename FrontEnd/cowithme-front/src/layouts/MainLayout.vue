<template>
	<q-layout view="lHh Lpr lFf">
		<q-header elevated>
			<q-toolbar style="background-color: #00adb5">
				<TestComp @click="toggleLeftDrawer" flat></TestComp>
				<!-- <q-toolbar-title> CowithMe 코윗미~ </q-toolbar-title> -->
			</q-toolbar>
		</q-header>

		<q-drawer v-model="leftDrawerOpen" show-if-above bordered>
			<div class="navbar" style="background-color: #eeeeee; height: 100vh">
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
						<ClassText
							style="font-size: 18px; font-family: 'GmarketSansLight'"
						></ClassText>
					</div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col">
						<WelcomeText
							style="font-size: 24px; font-family: 'GmarketSansMedium'"
						></WelcomeText>
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

// const linksList = [
// 	{
// 		title: 'Docs',
// 		caption: 'quasar.dev',
// 		icon: 'school',
// 		link: 'https://quasar.dev',
// 	},
// 	{
// 		title: 'Github',
// 		caption: 'github.com/quasarframework',
// 		icon: 'code',
// 		link: 'https://github.com/quasarframework',
// 	},
// 	{
// 		title: 'Discord Chat Channel',
// 		caption: 'chat.quasar.dev',
// 		icon: 'chat',
// 		link: 'https://chat.quasar.dev',
// 	},
// 	{
// 		title: 'Forum',
// 		caption: 'forum.quasar.dev',
// 		icon: 'record_voice_over',
// 		link: 'https://forum.quasar.dev',
// 	},
// 	{
// 		title: 'Twitter',
// 		caption: '@quasarframework',
// 		icon: 'rss_feed',
// 		link: 'https://twitter.quasar.dev',
// 	},
// 	{
// 		title: 'Facebook',
// 		caption: '@QuasarFramework',
// 		icon: 'public',
// 		link: 'https://facebook.quasar.dev',
// 	},
// 	{
// 		title: 'Quasar Awesome',
// 		caption: 'Community Quasar projects',
// 		icon: 'favorite',
// 		link: 'https://awesome.quasar.dev',
// 	},
// ];

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
		const leftDrawerOpen = ref(false);

		return {
			// essentialLinks: linksList,/
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
	font-family: 'GmarketSansLight';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
@font-face {
	font-family: 'GmarketSansMedium';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>
