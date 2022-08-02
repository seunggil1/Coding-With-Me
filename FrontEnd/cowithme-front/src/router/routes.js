const routes = [
	{
		path: '/',
		component: () => import('layouts/MainLayout.vue'),
		children: [
			{ path: '', component: () => import('pages/IndexPage.vue') },
			{
				path: '/makeClass',
				component: () => import('pages/teacher/MakeClassPage.vue'),
			},
		],
	},
	// 로그인/회원가입
	{ path: '/test', component: () => import('components/TestComp.vue') },
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
		component: () => import('pages/account/SignUpPage.vue'),
	},
	// home
	{
		path: '/home',
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
