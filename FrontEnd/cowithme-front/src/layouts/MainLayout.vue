<template>
	<q-layout view="lHh Lpr lFf">
		<q-header elevated>
			<q-toolbar style="background-color: #00adb5">
				<TestComp @click="toggleLeftDrawer" flat></TestComp>
				<router-link
					:to="{ path: '/home' }"
					style="text-decoration: none; color: inherit"
				>
					<q-toolbar-title class="font-OT">
						코딩 수업을 더 쉽게, 코윗미</q-toolbar-title
					></router-link
				>
			</q-toolbar>
		</q-header>

		<q-drawer
			v-model="leftDrawerOpen"
			show-if-above
			bordered
			style="box-shadow: 3px 3px 3px 0px rgba(0, 0, 0, 0.2)"
		>
			<div class="navbar" style="background-color: #eeeeee; height: 100vh">
				<div class="row">
					<div class="col"></div>
					<div class="col-10">
						<router-link
							:to="{ path: '/home' }"
							style="text-decoration: none; color: inherit"
						>
							<AtomLogo2 class="test" style="width: 90%"></AtomLogo2>
						</router-link>
					</div>
					<div class="col"></div>
				</div>
				<div
					v-if="info2.role === '강사'"
					class="justify-center flex q-mb-lg pic"
				>
					<AtomProfileImg
						style="border-radius: 50%; width: 100px; height: 100px"
					></AtomProfileImg>
				</div>
				<div
					v-if="info2.role === '학생'"
					class="justify-center flex q-mb-lg pic"
				>
					<AtomStudentProfile
						style="border-radius: 50%; width: 100px; height: 100px"
					></AtomStudentProfile>
				</div>
				<div class="row">
					<div class="col-1"></div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col">
						<q-badge
							v-if="info2.role === '강사'"
							class="q-mb-sm brand"
							outline
							label="강사"
						/>

						<div class="row a">
							<div
								class="font-OT"
								style="font-size: 24px; font-family: 'OTWelcomeBA'"
							>
								{{ info2.name }}
							</div>
							<div>
								<WelcomeText
									class="font-OT"
									style="font-size: 24px; font-family: 'OTWelcomeBA'"
								></WelcomeText>
							</div>
						</div>
					</div>
				</div>
				<div v-if="info2.role == '학생'" class="row">
					<router-link
						:to="{ name: 'reportCard', params: { userId: info2.userId } }"
						style="text-decoration: none; color: inherit"
					>
						<AtomScoreIconButton class="font-OT"></AtomScoreIconButton>
					</router-link>
				</div>
				<div class="row" @click="goToMypage">
					<div class="q-py-xs q-gutter-sm">
						<q-btn
							size="23px"
							class="btn-size font-OT brand"
							align="left"
							flat
							label="마이페이지"
							icon="account_circle"
						></q-btn>
					</div>
				</div>
				<div class="row fixed-bottom">
					<div class="q-py-xs q-gutter-sm">
						<q-btn
							size="23px"
							class="btn-size font-OT"
							align="left"
							flat
							label="로그아웃"
							icon="logout"
							style="font-family: 'OTWelcomeBA'"
							@click="goLogout"
						>
						</q-btn>
					</div>
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
// import AtomSearchIconButton from 'src/components/atoms/AtomSearchIconButton.vue';
import AtomScoreIconButton from 'src/components/atoms/AtomScoreIconButton.vue';
// import AtomMyPageButton from 'src/components/atoms/AtomMyPageButton.vue';
// import AtomLogoutButton from 'src/components/atoms/AtomLogoutButton.vue';
import { useRouter } from 'vue-router';
// import { storeToRefs } from 'pinia';
import { useAuthStore } from 'src/stores';
import AtomProfileImg from 'src/components/atoms/AtomProfileImg.vue';
import AtomStudentProfile from 'src/components/atoms/AtomStudentProfile.vue';

export default defineComponent({
	name: 'MainLayout',
	components: {
		TestComp,
		AtomLogo2,
		WelcomeText,
		// AtomSearchIconButton,
		AtomScoreIconButton,
		AtomProfileImg,
		AtomStudentProfile,
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
			await router.push({ path: '/login' });
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
	font-family: 'MICEGothic Bold' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2')
		format('woff2') !important;
	font-weight: 700 !important;
	font-style: normal !important;
}
@font-face {
	font-family: 'OTWelcomeBA' !important;
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeBA.woff2')
		format('woff2') !important;
	font-weight: normal !important;
	font-style: normal !important;
}
.test {
	-webkit-filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
	filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.7)) !important;
}
.brand {
	color: #00adb5 !important;
}
.font-OT {
	font-family: 'MICEGothic Bold' !important;
	font-size: 24px;
}
.a {
	color: #18272f;
	position: relative;
	text-decoration: none;
}

.a::before {
	content: '';
	position: absolute;
	width: 100%;
	height: 4px;
	border-radius: 4px;
	background-color: #18272f;
	bottom: 0;
	left: 0;
	transform-origin: right;
	transform: scaleX(0);
	transition: transform 0.3s ease-in-out;
}

.a:hover::before {
	transform-origin: left;
	transform: scaleX(1);
}
/* .pic {
	box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
} */
</style>
