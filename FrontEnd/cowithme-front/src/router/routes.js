const routes = [
	{
		path: '/',
		name: 'home',
		component: () => import('layouts/MainLayout.vue'),
		children: [
			{
				path: '/',
				name: 'home',
				component: () => import('pages/IndexPage.vue'),
				// redirect: '/',
			},
			{
				path: '/makeClass',
				component: () => import('pages/teacher/MakeClassPage.vue'),
			},
			{
				path: '/mypage',
				component: () => import('pages/account/MyPagePage.vue'),
			},
			{
				path: '/addStudent',
				name: 'addStudent',
				component: () => import('pages/teacher/AddStudentPage.vue'),
			},
			{
				path: '/classDetail/:classId',
				name: 'classDetail',
				props: true,
				component: () => import('pages/teacher/classDetailPage.vue'),
				// redirect: '/classDetail/:classId',
			},
			{
				path: '/makeExam/:classId',
				name: 'makeExam',
				props: true,
				component: () => import('pages/teacher/MakeExamPage.vue'),
			},
			{
				path: '/reportCard/:userId',
				name: 'reportCard',
				props: true,
				component: () => import('pages/student/ScorePage.vue'),
			},
			{
				path: '/studyRooms',
				name: 'studyRoom',
				component: () => import('pages/student/StudyRoomSearchPage.vue'),
			},
		],
	},
	// 로그인/회원가입
	// { path: '/test', component: () => import('components/TestComp.vue') },
	{
		path: '/login',
		component: () => import('pages/account/LoginPage.vue'),
	},
	{
		path: '/IDfind',
		component: () => import('pages/account/IDFindPage.vue'),
	},
	{
		path: '/PWfind',
		component: () => import('pages/account/PWFindPage.vue'),
	},
	{
		path: '/join',
		name: 'join',
		component: () => import('pages/account/SignUpPage.vue'),
	},
	// home
	{
		path: '/homepage',
		name: 'homepage',
		component: () => import('pages/StudentHomePage.vue'),
	},
	// 반
	// Always leave this as last one,
	// but you can also remove it
	{
		path: '/:catchAll(.*)*',
		component: () => import('pages/ErrorNotFound.vue'),
	},
];

export default routes;
