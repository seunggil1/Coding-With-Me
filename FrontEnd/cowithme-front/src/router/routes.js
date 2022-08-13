const routes = [
	{
		path: '/',
		name: 'intro',
		component: () => import('src/pages/IntroPage.vue'),
	},
	{
		path: '/home',
		name: 'home',
		component: () => import('layouts/MainLayout.vue'),
		// beforeEnter: (to, from, next) => {
		// 	if (localStorage.getItem('token')) {
		// 		next('/');
		// 	}
		// 	next('/login');
		// },
		children: [
			{
				path: '/home',
				component: () => import('pages/IndexPage.vue'),
				// redirect: '/',
			},
			{
				path: '/makeClass',
				component: () => import('pages/teacher/MakeClassPage.vue'),
			},
			{
				path: '/mypage',
				component: () => import('pages/account/MypagePage.vue'),
			},
			{
				path: '/addStudent',
				name: 'addStudent',
				props: true,
				component: () => import('pages/teacher/AddStudentPage.vue'),
			},
			{
				path: '/classDetail/:classId',
				name: 'classDetail',
				// props: true,
				component: () => import('pages/teacher/ClassDetailPage.vue'),
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
			{
				path: '/testDetail/:testId',
				name: 'testDetail',
				props: true,
				component: () => import('pages/teacher/TestDetailPage.vue'),
			},
		],
	},
	// 로그인/회원가입
	{
		path: '/login',
		name: 'login',
		component: () => import('pages/account/LoginPage.vue'),
		beforeEnter: function (to, from, next) {
			if (localStorage.getItem('token')) {
				next('/home');
			}
			next();
		},
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
		component: () => import('pages/account/SignupPage.vue'),
	},
	// Always leave this as last one,
	// but you can also remove it
	{
		path: '/teacherlecture',
		name: 'teacherlecture',
		component: () => import('pages/webRTC/TeacherLecturePage.vue'),
	},
	{
		path: '/studentlecture',
		component: () => import('pages/webRTC/StudentLecturePage.vue'),
	},
	{
		path: '/teacherexam',
		component: () => import('pages/webRTC/TeacherExamPage.vue'),
	},
	{
		path: '/studentexam',
		component: () => import('pages/webRTC/StudentExamPage.vue'),
	},
	{
		path: '/:catchAll(.*)*',
		component: () => import('pages/ErrorNotFound.vue'),
	},
];

export default routes;
